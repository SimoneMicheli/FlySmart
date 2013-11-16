package smart;


import java.util.Iterator;

import java.util.List;


import org.bson.types.ObjectId;

import prenotazione.FlightNotFoundException;
import util.Coordinata;


import db.DBSession;
import db.Lock;

import model.Pallet;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;


/**
 *
 */
public class SmartCheckin implements SmartAlgorithm{

	/**
	 * indica i posti liberi ed occupati sull'aereo per i passeggeri
	 */
	private boolean[][] occupancyPasseggeri= {{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false}};
	/**
	 * indica i posti liberi ed occupati sull'aereo per i pallet
	 */
	private boolean[][] occupancyPallet= {{false,false},{false,false},{false,false},{false,false}};

	/**
	 * volo su cui calcolare i dati
	 */
	private Volo v;

	public SmartCheckin(ObjectId idVolo) throws FlightNotFoundException{

		try{
			Lock.getInstance().acquireLock(idVolo);

			v = DBSession.getVoloDAO().get(idVolo);

			//volo non trovato
			if(v == null)
				throw new FlightNotFoundException(idVolo);

			//chiudo il volo
			v.setStato(StatoVolo.CLOSED);

			DBSession.getVoloDAO().save(v);
		}finally{

			Lock.getInstance().releaseLock(idVolo);

		}

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


	@Override
	public void calcolaCheckin() {
		//ottengo lista passeggeri e pallet
		List<Passeggero> passeggeri = DBSession.getPasseggeroDAO().getByIdVolo(v.getId()).order("-peso").asList();
		List<Pallet> pallets = DBSession.getPalletDAO().getByIdVolo(v.getId()).order("-peso").asList();


		for(Pallet p : pallets)
			System.out.println(p);
		
		//calcola disposizione
		posizionaPallet(pallets);
		//posizionePasseggeri(passeggeri, v);

		//salva dati aggiornati nel db
		DBSession.getPasseggeroDAO().saveList(passeggeri);
		DBSession.getPalletDAO().saveList(pallets);

	}

	/**
	 * calcola dove posizionare i pallet sul volo
	 * mediante algoritmo greedy
	 * @param lista lista di pallet da posizionare
	 */
	protected void posizionaPallet(List<Pallet> lista){
		//lista vuota (uso lazy evaluation)
		if(lista == null || lista.size() == 0)
			return;
		
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


	protected int[] postoLiberoPasseggeri(int colonnaCellaOttimaAss, int rigaCellaOttimaAss){

		int maxRighe =occupancyPasseggeri.length;
		int distanzaMassima = maxRighe-rigaCellaOttimaAss-1;
		if(rigaCellaOttimaAss>distanzaMassima) distanzaMassima=rigaCellaOttimaAss;

		for(int i=0;i<=distanzaMassima;i++){
			// System.err.println("i:"+i);

			// System.err.println("++++++++++++++++++++++++++");
			int colonnaCalcolata = colonnaCellaOttimaAss;
			for(int vicino=0;vicino<6;vicino++){
				// System.err.println("vicino:"+vicino);
				try{
					if(!occupancyPasseggeri[rigaCellaOttimaAss+i][colonnaCalcolata]){
						occupancyPasseggeri[rigaCellaOttimaAss+i][colonnaCalcolata]=true;
						int[] posto = {rigaCellaOttimaAss+i, colonnaCalcolata};
						// System.err.println("++++++++++trovato");
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaCellaOttimaAss+(vicino+1))%3+((int)colonnaCellaOttimaAss/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaCellaOttimaAss/3)*(-2)+1); //l'ultimo passo sarà il vicino 6 ma il for termina
				// System.err.println("+++++calcolata:"+colonnaCalcolata+" scelta: "+colonnaCellaOttimaAss);
			}


			// System.err.println("------------------------------------------");
			colonnaCalcolata = colonnaCellaOttimaAss;
			for(int vicino=0;vicino<6;vicino++){
				try{

					// System.err.println("matrice nel posto"+(rigaCellaOttimaAss-i)+" "+colonnaCalcolata+": "+occupancyPasseggeri[rigaCellaOttimaAss-i][colonnaCalcolata]);
					if(!occupancyPasseggeri[rigaCellaOttimaAss-i][colonnaCalcolata]){
						occupancyPasseggeri[rigaCellaOttimaAss-i][colonnaCalcolata]=true;
						int[] posto = {rigaCellaOttimaAss-i, colonnaCalcolata};
						// System.err.println("-------trovato"+(rigaCellaOttimaAss-i)+" "+colonnaCalcolata);
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaCellaOttimaAss+(vicino+1))%3+((int)colonnaCellaOttimaAss/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaCellaOttimaAss/3)*(-2)+1);
				// System.err.println("-----calcolata:"+colonnaCalcolata+" scelta: "+colonnaCellaOttimaAss);
			}
		}

		int[] posto = {-1, -1};
		System.out.println("Posto:"+posto[0]+" "+posto[1]);
		return posto;
	}

	/**
	 * restituisce il primo posto libero in cui posizionare il
	 * pallet secondo l'algoritmo greedy, le posizioni sono in valore assoluto
	 * @param distX posizione ideale sulle colonne 
	 * @param distY posizione idale sulle righe
	 * @return posto da assegnare (x,y)
	 */
	protected int[] postoLiberoPallet(double distX, double distY){

		double rigaCellaOttimaRel=distY; //in coordinate relative
		double colonnaCellaOttimaRel=distX; //in coordinate relative


		//casting posizione (intervallo 1 shift 0.5)
		if(colonnaCellaOttimaRel <=0 && colonnaCellaOttimaRel>=-0.5)
			colonnaCellaOttimaRel = -0.5;
		else
			colonnaCellaOttimaRel = 0.5 + (int)(colonnaCellaOttimaRel - 0.5);
		if(rigaCellaOttimaRel <=0 && rigaCellaOttimaRel>=-0.5)
			rigaCellaOttimaRel =-0.5;
		else
			rigaCellaOttimaRel = 0.5 + (int)(rigaCellaOttimaRel - 0.5);

		System.out.println("Discretizzato: x:"+colonnaCellaOttimaRel +" y:"+rigaCellaOttimaRel);




		//controllo interno all'aereo
		if(Coordinata.XAbs(colonnaCellaOttimaRel ,v.getTipoAereo()) > v.getTipoAereo().getColonnePallet() )
			colonnaCellaOttimaRel = Coordinata.XRel(v.getTipoAereo().getColonnePallet(), v.getTipoAereo());
		if(Coordinata.XAbs(colonnaCellaOttimaRel ,v.getTipoAereo()) < 0 )
			colonnaCellaOttimaRel = Coordinata.XRel(0, v.getTipoAereo());
		if(Coordinata.YAbs(rigaCellaOttimaRel ,v.getTipoAereo()) > v.getTipoAereo().getFilePallet() )
			rigaCellaOttimaRel = Coordinata.YRel(v.getTipoAereo().getFilePallet(), v.getTipoAereo());
		if(Coordinata.YAbs(rigaCellaOttimaRel ,v.getTipoAereo()) <0 )
			rigaCellaOttimaRel = Coordinata.YRel(0, v.getTipoAereo());

		//tengo le variabili anche assolute cosi non devo sempre fare il cambio di sistema di riferimento
		int colonnaCellaOttimaAss = Coordinata.XAbs(colonnaCellaOttimaRel ,v.getTipoAereo());
		int rigaCellaOttimaAss = Coordinata.YAbs(rigaCellaOttimaRel ,v.getTipoAereo());

		//provo lo scelto
		System.out.println("provo lo scelto");
		if(!occupancyPallet[rigaCellaOttimaAss][colonnaCellaOttimaAss]){
			occupancyPallet[rigaCellaOttimaAss][colonnaCellaOttimaAss]=true;
			int[] posto = {colonnaCellaOttimaAss, rigaCellaOttimaAss};
			System.out.println("trovato scelto");
			return posto;
		}

		//se arrivo qui significa che la cella ottima è occupata



		int maxRighe =occupancyPallet.length;
		int maxColonne = 2;



		//calcolo la distanza massima da verificare (controllare)
		int distanzaMassima = maxRighe-rigaCellaOttimaAss-1;
		if(rigaCellaOttimaAss>distanzaMassima) distanzaMassima=rigaCellaOttimaAss;
		//va fatto anche sulla colonna
		if(colonnaCellaOttimaAss>distanzaMassima) distanzaMassima=colonnaCellaOttimaAss;
		if(maxColonne-colonnaCellaOttimaAss-1>distanzaMassima) distanzaMassima=maxColonne-colonnaCellaOttimaAss-1;






		int rigaVertice = rigaCellaOttimaAss;
		int colonnaVertice = colonnaCellaOttimaAss;
		int raggioSpirale=1;
		double pitagora; //mi dice la distanza di una cella dal punto dove l'avrei voluto mettere,
		double pitagoraMin=2147483647;
		boolean trovatoAlmenoUno=false;
		int[] posto = {-1, -1};
		while(raggioSpirale<=distanzaMassima){
			trovatoAlmenoUno=false;
			System.out.println("dist="+raggioSpirale);
			rigaVertice = rigaCellaOttimaAss-raggioSpirale;
			colonnaVertice = colonnaCellaOttimaAss-raggioSpirale; //mi posiziono nel vertice del quadrato di centro rigaCellaOttimaAss colonnaCellaOttimaAss

			//vado orizzontale
			for(int a=colonnaVertice;a<=colonnaCellaOttimaAss+raggioSpirale;a++){

				System.out.println("["+rigaVertice+";"+a+"]");
				if(!occupancyPallet[rigaVertice][a]){ //se non è occupato calcolo la distanza
					pitagora = Math.pow(distX-Coordinata.XRel(a, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(rigaVertice, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
					if(pitagora<pitagoraMin){ 
						pitagoraMin=pitagora;
						trovatoAlmenoUno=true;
						//invertiti
						posto[0] = a;
						posto[1] = rigaVertice;
					}
				}
			}

			//scendo verticale
			for(int b=rigaVertice+1;b<=rigaCellaOttimaAss+raggioSpirale;b++){

				System.out.println("["+b+";"+(colonnaCellaOttimaAss+raggioSpirale+"]"));
				if(!occupancyPallet[b][colonnaCellaOttimaAss+raggioSpirale]){
					pitagora = Math.pow(distX-Coordinata.XRel(colonnaCellaOttimaAss+raggioSpirale, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(b, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
					if(pitagora<pitagoraMin){ 
						pitagoraMin=pitagora;
						trovatoAlmenoUno=true;
						//invertiti
						posto[0] = colonnaCellaOttimaAss+raggioSpirale;
						posto[1] = b;
					}
				}
			}

			//ritorno orizzontale
			for(int c=colonnaCellaOttimaAss+raggioSpirale-1;c>=colonnaVertice;c--){
				System.out.println("["+(rigaCellaOttimaAss+raggioSpirale)+";"+c+"]");
				if(!occupancyPallet[rigaCellaOttimaAss+raggioSpirale][c]){
					pitagora = Math.pow(distX-Coordinata.XRel(c, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(rigaCellaOttimaAss+raggioSpirale, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
					if(pitagora<pitagoraMin){ 
						pitagoraMin=pitagora;
						trovatoAlmenoUno=true;
						//invertiti
						posto[0] = rigaCellaOttimaAss+raggioSpirale;
						posto[1] = c;
					}
				}
			}


			//salgo verticale
			for(int d=rigaCellaOttimaAss+raggioSpirale-1;d>rigaVertice;d--){

				System.out.println("["+d+";"+colonnaVertice+"]");
				if(!occupancyPallet[d][colonnaVertice]){
					pitagora = Math.pow(distX-Coordinata.XRel(colonnaVertice, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(d, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
					if(pitagora<pitagoraMin){ 
						pitagoraMin=pitagora;
						trovatoAlmenoUno=true;
						//invertiti
						posto[0] = colonnaVertice+raggioSpirale;
						posto[1] = d;
					}
				}
			}

			if(trovatoAlmenoUno) return posto;

			raggioSpirale++;
			System.out.println("aumento raggio");
		}

		int[] postoNullo = {-1, -1};
		System.out.println("Posto:"+postoNullo[1]+" "+postoNullo[0]);
		return postoNullo;
	}


}
