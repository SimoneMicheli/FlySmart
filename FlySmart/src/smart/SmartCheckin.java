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
		System.out.println("\n");
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
		System.out.println("\n");
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

		System.out.println("Primo pallet");
		//ottengo primo pallet
		Pallet p = i.next();

		//posizione pallet in pos default
		p.setFila(Coordinata.YAbs(0.5, v.getTipoAereo()));
		p.setColonna(0);

		double momX = p.getPeso() * -0.5;
		double momY = p.getPeso() * 0.5;

		System.out.println("momX: "+momX);
		System.out.println("momY: "+momY);
		//setto occupato il primo posto del pallet
		occupancyPallet[Coordinata.YAbs(0.5, v.getTipoAereo())][0] = true;
		System.out.println("Questo peso: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] produce [momX:"+momX+" momY: "+momY+"]");
		int passo=1;
		//calcolo posizione per i pallet successivi
		while(i.hasNext()){
			stampaOccupancyPallet(passo);
			passo++;
			p = i.next();

			System.out.println("Prossimo peso di "+p.getPeso());
			//posizione ideale per annullare il momento
			double distX = -momX / p.getPeso();
			double distY = -momY / p.getPeso();
			System.out.println("Calcolato: x:"+distX +" y:"+distY);

			int[] pos = postoLiberoPallet(distX,distY); //ritorno in modo XY quindi prima posizione è la colonna

			p.setColonna(pos[0]); 
			p.setFila(pos[1]);

			//sbilanciamento effettivo
			System.out.println("Calcolo lo sbilanciamento effettivo, nuovo");
			momX = momX + p.getPeso() * Coordinata.XRel(pos[0], v.getTipoAereo()); //sbilanciamento sulle x è la colonna
			momY = momY + p.getPeso() * Coordinata.YRel(pos[1], v.getTipoAereo()); //sbilanciamento sulle y è la fila
			System.out.println("Questo peso: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] e lascia un mom di [momX:"+momX+" momY: "+momY+"]");
		}

		stampaOccupancyPallet(passo);
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
	 * @param distX il punto X dove lo sbilanciamento sulle X risulterebbe 0
	 * @param distY il punto Y dove lo sbilanciamento sulle Y risulterebbe 0
	 * @return un vettore in cui la prima posizione indica la colonna (X) dove posizionare il pallet, e la seconda posizione indica la riga (Y)
	 */
	private int[] postoLiberoPallet(double distX, double distY){


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
		System.out.println("Interno all'aereo: x:"+colonnaCellaOttimaRel +" y:"+rigaCellaOttimaRel);

		//tengo le variabili anche assolute cosi non devo sempre fare il cambio di sistema di riferimento
		int colonnaCellaOttimaAss = Coordinata.XAbs(colonnaCellaOttimaRel ,v.getTipoAereo());
		int rigaCellaOttimaAss = Coordinata.YAbs(rigaCellaOttimaRel ,v.getTipoAereo());

		System.out.println("Interno all'aereo assoluto: x:"+colonnaCellaOttimaAss +" y:"+rigaCellaOttimaAss);
		
		
		//provo lo scelto
		System.out.println("provo lo scelto");
		if(!occupancyPallet[rigaCellaOttimaAss][colonnaCellaOttimaAss]){
			occupancyPallet[rigaCellaOttimaAss][colonnaCellaOttimaAss]=true;
			int[] posto = {colonnaCellaOttimaAss, rigaCellaOttimaAss};
			System.out.println("trovato scelto: [x:"+colonnaCellaOttimaAss+" y:"+rigaCellaOttimaAss+"]"); 
			return posto;
		}else System.out.println("è occupato");

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
			System.out.println("raggioSpirale="+raggioSpirale);
			rigaVertice = rigaCellaOttimaAss-raggioSpirale;
			colonnaVertice = colonnaCellaOttimaAss-raggioSpirale; //mi posiziono nel vertice del quadrato di centro rigaCellaOttimaAss colonnaCellaOttimaAss

			//vado orizzontale
			System.out.println("vado orizzontale");
			for(int a=colonnaVertice;a<=colonnaCellaOttimaAss+raggioSpirale;a++){
				System.out.println("["+a+";"+rigaVertice+"]");
				try{
					if(!occupancyPallet[rigaVertice][a]){ //se non è occupato calcolo la distanza
						pitagora = Math.pow(distX-Coordinata.XRel(a, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(rigaVertice, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
							//invertiti
							posto[0] = a;
							posto[1] = rigaVertice;
						}
					}else System.out.println("è occupato");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("sono fuori");
				}
			}

			//scendo verticale
			System.out.println("scendo verticale");
			for(int a=rigaVertice+1;a<=rigaCellaOttimaAss+raggioSpirale;a++){

				System.out.println("["+(colonnaCellaOttimaAss+raggioSpirale)+";"+a+"]");

				try{
					if(!occupancyPallet[a][colonnaCellaOttimaAss+raggioSpirale]){
						pitagora = Math.pow(distX-Coordinata.XRel(colonnaCellaOttimaAss+raggioSpirale, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(a, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
							//invertiti
							posto[0] = colonnaCellaOttimaAss+raggioSpirale;
							posto[1] = a;
						}
					}else System.out.println("è occupato");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("sono fuori");
				}
			}

			//ritorno orizzontale
			System.out.println("ritorno orizzontale");
			for(int a=colonnaCellaOttimaAss+raggioSpirale-1;a>=colonnaVertice;a--){
				System.out.println("["+a+";"+(rigaCellaOttimaAss+raggioSpirale)+"]");

				try{
					if(!occupancyPallet[rigaCellaOttimaAss+raggioSpirale][a]){
						pitagora = Math.pow(distX-Coordinata.XRel(a, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(rigaCellaOttimaAss+raggioSpirale, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
							//invertiti
							posto[0] = a;
							posto[1] = rigaCellaOttimaAss+raggioSpirale;
						}
					}else System.out.println("è occupato");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("sono fuori");
				}
			}


			//salgo verticale
			System.out.println("salgo verticale");
			for(int a=rigaCellaOttimaAss+raggioSpirale-1;a>rigaVertice;a--){

				System.out.println("["+colonnaVertice+";"+a+"]");

				try{
					if(!occupancyPallet[a][colonnaVertice]){
						pitagora = Math.pow(distX-Coordinata.XRel(colonnaVertice, v.getTipoAereo()), 2)+Math.pow(distY-Coordinata.YRel(a, v.getTipoAereo()), 2);//calcolo la distanza tra due punti
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
							//invertiti
							posto[0] = colonnaVertice+raggioSpirale;
							posto[1] = a;
						}
					}else System.out.println("è occupato");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("sono fuori");
				}
			}

			if(trovatoAlmenoUno){
				System.out.println("ne ho trovato almeno uno, il migliore è: [x:"+posto[0]+" y:"+posto[1]+"]");
				occupancyPallet[posto[1]][posto[0]]=true;
				return posto;
			}

			raggioSpirale++;
			System.out.println("aumento raggio");
		}

		int[] postoNullo = {-1, -1};
		System.out.println("Posto:"+postoNullo[1]+" "+postoNullo[0]);
		return postoNullo;
	}


}
