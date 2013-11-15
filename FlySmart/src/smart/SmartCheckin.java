package smart;


import java.util.Iterator;

import java.util.List;

import org.bson.types.ObjectId;


import db.DBSession;
import db.Lock;

import model.Coordinata;
import model.Pallet;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;

import exception.FlightNotFoundException;

/**
 *
 */
public class SmartCheckin {
	
	private boolean[][] occupancyPasseggeri= {{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false}};
	private boolean[][] occupancyPallet= {{false,false},{false,false},{false,false},{false,false}};
	
	private Volo v;

	public SmartCheckin(ObjectId idVolo) throws FlightNotFoundException{
		
		Lock.getInstance().acquireLock(idVolo);
		
		v = DBSession.getVoloDAO().get(idVolo);
		
		//volo non trovato
		if(v == null)
			throw new FlightNotFoundException(idVolo);
		
		//chiudo il volo
		v.setStato(StatoVolo.CLOSED);
		
		DBSession.getVoloDAO().save(v);
		
		Lock.getInstance().releaseLock(idVolo);
		
		//creo matrici
	}

	public void stampaOccupancyPasseggeri(int x){
		System.out.println("\n\nPasso "+x);
		for(int i=0; i<occupancyPasseggeri.length; i=i+1) {
			System.out.println("");
			for(int j=0; j<occupancyPasseggeri[0].length; j=j+1) {
				System.out.print(occupancyPasseggeri[i][j] + " ");
				if(occupancyPasseggeri[i][j]) System.out.print(" ");
			}
		}
	}

	public void stampaOccupancyPallet(int x){
		System.out.println("\n\nPasso "+x);
		for(int i=0; i<occupancyPallet.length; i=i+1) {
			System.out.println("");
			for(int j=0; j<occupancyPallet[0].length; j=j+1) {
				System.out.print(occupancyPallet[i][j] + " ");
				if(occupancyPallet[i][j]) System.out.print(" ");
			}
		}
	}


	public void calcolaCheckin() {
		//ottengo lista passeggeri e pallet
		List<Passeggero> passeggeri = DBSession.getPasseggeroDAO().getByIdVolo(v.getId()).order("-peso").asList();
		List<Pallet> pallets = DBSession.getPalletDAO().getByIdVolo(v.getId()).order("-peso").asList();
		

		//calcola disposizione
		posizionaPallet(pallets);
		//posizionePasseggeri(passeggeri, v);

		//salva dati aggiornati nel db
		DBSession.getPasseggeroDAO().saveList(passeggeri);
		DBSession.getPalletDAO().saveList(pallets);
		
	}

	private void posizionaPallet(List<Pallet> lista){
		Iterator<Pallet> i = lista.iterator();

		//ottengo primo pallet
		Pallet p = i.next();

		//posizione pallet in pos default
		p.setFila(Coordinata.YAbs(0.5, v.getTipoAereo()));
		p.setColonna(0);

		double momX = p.getPeso() * -0.5;
		double momY = p.getPeso() * 0.5;

		//setto occupato il primo posto del pallet
		occupancyPallet[Coordinata.YAbs(0.5, v.getTipoAereo())][0] = true;
		System.out.println("Nuovo peso: "+p.getPeso()+" [x:"+p.getColonna()+" y:"+p.getFila()+"] produce [momX:"+momX+" momY: "+momY+"]");

		//calcolo posizione per i pallet successivi
		while(i.hasNext()){
			p = i.next();

			//posizione ideale per annullare il momento
			double distX = -momX / p.getPeso();
			double distY = -momY / p.getPeso();
			System.out.println("Calcolato: x:"+distX +" y:"+distY);

			//casting posizione (intervallo 1 shift 0.5)
			if(distX <=0 && distX>=-0.5)
				distX = -0.5;
			else
				distX = 0.5 + (int)(distX - 0.5);
			if(distY <=0 && distY>=-0.5)
				distY =-0.5;
			else
				distY = 0.5 + (int)(distY - 0.5);

			System.out.println("Discretizzato: x:"+distX +" y:"+distY);

			//controllo interno all'aereo
			if(Coordinata.XAbs(distX ,v.getTipoAereo()) > v.getTipoAereo().getColonnePallet() )
				distX = Coordinata.XRel(v.getTipoAereo().getColonnePallet(), v.getTipoAereo());
			if(Coordinata.XAbs(distX ,v.getTipoAereo()) < 0 )
				distX = Coordinata.XRel(0, v.getTipoAereo());
			if(Coordinata.YAbs(distY ,v.getTipoAereo()) > v.getTipoAereo().getFilePallet() )
				distY = Coordinata.YRel(v.getTipoAereo().getFilePallet(), v.getTipoAereo());
			if(Coordinata.YAbs(distY ,v.getTipoAereo()) <0 )
				distY = Coordinata.YRel(0, v.getTipoAereo());

			System.out.println("Interno all'aereo: x:"+distX +" y:"+distY);
			System.out.println("Coordinate assolute: x:"+Coordinata.XAbs(distX ,v.getTipoAereo()) +" y:"+Coordinata.YAbs(distY ,v.getTipoAereo()));
			//aggiorno posto effettivo
			int[] pos = postoLiberoPallet(Coordinata.XAbs(distX ,v.getTipoAereo()), Coordinata.YAbs(distY ,v.getTipoAereo()));
			p.setFila(pos[1]);
			p.setColonna(pos[0]);

			//sbilanciamento effettivo
			momX = momX + p.getPeso() * Coordinata.XRel(pos[0], v.getTipoAereo());
			momY = momY + p.getPeso() * Coordinata.YRel(pos[1], v.getTipoAereo());
			System.out.println("Nuovo peso: "+p.getPeso()+" [x:"+p.getColonna()+" y:"+p.getFila()+"] produce [momX:"+momX+" momY: "+momY+"]");
		}

	} 


	private int[] postoLiberoPasseggeri(int colonnaScelta, int rigaScelta){

		int maxRighe =occupancyPasseggeri.length;
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;

		for(int i=0;i<=distanzaMassima;i++){
			// System.err.println("i:"+i);

			// System.err.println("++++++++++++++++++++++++++");
			int colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				// System.err.println("vicino:"+vicino);
				try{
					if(!occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta+i, colonnaCalcolata};
						// System.err.println("++++++++++trovato");
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+(vicino+1))%3+((int)colonnaScelta/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaScelta/3)*(-2)+1); //l'ultimo passo sarà il vicino 6 ma il for termina
				// System.err.println("+++++calcolata:"+colonnaCalcolata+" scelta: "+colonnaScelta);
			}


			// System.err.println("------------------------------------------");
			colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				try{

					// System.err.println("matrice nel posto"+(rigaScelta-i)+" "+colonnaCalcolata+": "+occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]);
					if(!occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta-i, colonnaCalcolata};
						// System.err.println("-------trovato"+(rigaScelta-i)+" "+colonnaCalcolata);
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+(vicino+1))%3+((int)colonnaScelta/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaScelta/3)*(-2)+1);
				// System.err.println("-----calcolata:"+colonnaCalcolata+" scelta: "+colonnaScelta);
			}
		}

		int[] posto = {-1, -1};
		System.out.println("Posto:"+posto[0]+" "+posto[1]);
		return posto;
	}

	private int[] postoLiberoPallet(int colonnaScelta, int rigaScelta){
		int maxRighe =occupancyPallet.length;
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;

		//provo lo scelto
		try{
			if(!occupancyPallet[rigaScelta][colonnaScelta]){
				occupancyPallet[rigaScelta][colonnaScelta]=true;
				int[] posto = {colonnaScelta, rigaScelta};
				return posto;
			}
		}catch(ArrayIndexOutOfBoundsException e){
		}


		for(int i=0;i<=distanzaMassima;i++){

			try{
				//Salgo di i e cambio colonna
				if(!occupancyPallet[rigaScelta-i][1-colonnaScelta]){
					occupancyPallet[rigaScelta-i][1-colonnaScelta]=true;
					int[] posto = {1-colonnaScelta, rigaScelta-i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Salgo di i
				if(!occupancyPallet[rigaScelta+i][colonnaScelta]){
					occupancyPallet[rigaScelta+i][colonnaScelta]=true;
					int[] posto = {colonnaScelta, rigaScelta+i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i
				if(!occupancyPallet[rigaScelta-i][colonnaScelta]){
					occupancyPallet[rigaScelta-i][colonnaScelta]=true;
					int[] posto = {colonnaScelta, rigaScelta-i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i e cambio colonna
				if(!occupancyPallet[rigaScelta+i][1-colonnaScelta]){
					occupancyPallet[rigaScelta+i][1-colonnaScelta]=true;
					int[] posto = {1-colonnaScelta, rigaScelta+i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}
		}
		int[] posto = {-1, -1};
		return posto;
	}
}
