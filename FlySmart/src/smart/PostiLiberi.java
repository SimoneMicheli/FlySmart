/**
 * 
 */
package smart;

import java.util.LinkedList;
import java.util.List;

import util.Coordinata;
import util.CoordinataPallet;
import util.CoordinataPasseggero;
import model.Volo;

/**
 * la classe implementa la gestione dei posti libero
 * per pallet/passeggeri
 */
public class PostiLiberi {

	/**volo da analizzare**/
	Volo v = null;

	/**
	 * indica i posti liberi ed occupati sull'aereo per i passeggeri
	 */
	private boolean[][] occupancyPasseggeri= null;
	/**
	 * indica i posti liberi ed occupati sull'aereo per i pallet
	 */
	private boolean[][] occupancyPallet= null;
	
	private int maxRighe;
	private int maxColonne;

	/**
	 * Constructor for PostiLiberi.
	 * @param v Volo
	 */
	public PostiLiberi(Volo v){
		this.v = v;
		//creo matrici
		occupancyPallet = new boolean[v.getTipoAereo().getFilePallet()][v.getTipoAereo().getColonnePallet()];
		occupancyPasseggeri = new boolean[v.getTipoAereo().getFilePasseggeri()][v.getTipoAereo().getColonnePasseggeri()];
		maxRighe = v.getTipoAereo().getFilePasseggeri();
		maxColonne = v.getTipoAereo().getColonnePasseggeri();
	}

	/**
	 * Method postoLiberoPasseggeri.
	 * @param distX double
	 * @param distY double
	 * @return int[]
	 */
	public int[] postoLiberoPasseggeri(double distX, double distY){
		Coordinata coord = new CoordinataPasseggero(v.getTipoAereo());
		return controllaPosto(distX, distY, occupancyPasseggeri,coord);
	}
	
	
	
	public Posto postoPasseggeri(double ottimoX, double ottimoY){
		Coordinata coord = new CoordinataPasseggero(v.getTipoAereo());
		
		//discretizzo posizione (intervallo 1 shift 0.5) la x
		if(ottimoX <=0 && ottimoX>=-0.5)
			ottimoX = -0.5;
		else
			ottimoX = 0.5 + (int)(ottimoX - 0.5);
		//discretizzo posizione (intervallo 1 shift 0.5) la Y
		if(ottimoY <=0 && ottimoY>=-0.5)
			ottimoY =-0.5;
		else
			ottimoY = 0.5 + (int)(ottimoY - 0.5);


		//System.out.println("Discretizzato: x:"+ottimoX +" y:"+ottimoY);
		int postoX = coord.XAbs(ottimoX);
		int postoY = coord.YAbs(ottimoY);

		//controllo interno all'aereo colonna
		if(postoX > (maxColonne-1))
			postoX = maxColonne-1;
		if(postoX < 0 )
			postoX = 0;
		//controllo interno all'aereo riga
		if(postoY > (maxRighe-1) )
			postoY = maxRighe-1;
		if(postoY <0 )
			postoY = 0;

		//System.out.println("interno aereo: x:"+postoX +" y:"+postoY);
		//nuova sezione algoritmo
		
		LinkedList<Posto> posti = new LinkedList<Posto>();
		
		//preparo posti liberi
		for(int r=0; r<maxRighe; r++)
			for(int c=0; c<maxColonne; c++)
				if(occupancyPasseggeri[r][c] == false)
					posti.add(new Posto(c, r, postoX, postoY));

		//System.out.println("------------");
		//ordinamento posti
		Posto postiOrdinati = ordinaPostiLiberi(posti);
		//System.out.println("------------");
		//System.out.println(Arrays.deepToString(postiOrdinati));
		
		//contrassegno posto come occupati
		occupancyPasseggeri[postiOrdinati.y][postiOrdinati.x] = true;
		
		
		return postiOrdinati;

	}
	
	public Posto ordinaPostiLiberi(List<Posto> posti){
			
		List<Posto> ordinata[] =  new List[((4+maxRighe)*2)+1];
		
		//per ogni posto libero
		for(Posto p : posti){
			
			int dist = p.getDistanza();
			
			//verifico se esiste già
			List<Posto> l = ordinata[dist];
			if(l==null){
				//creo la lista non ancora inserita
				l = new LinkedList<Posto>();
				ordinata[dist] = l;
			}
			//inserisco il posto nella lista di posti a distanza dist
			l.add(p);
		}
		
		//ritorno solo il primo posto libero (quello a distanza minore)
		Posto postoLibero = null;

		for(int i=0; i< ((4+maxRighe)*2)+1; i++){
			List<Posto> l = ordinata[i];
			
			//nessun posto a distanza l
			if(l==null)
				//cerco a distanza l+1
				continue;
			
			//ottengo il primo posto in modo efficiente perchè non scansiono la lista
			postoLibero = l.get(0);
			//ottenuto il primo interrompo la ricerca
			break;
			
		}
		
		return postoLibero;
	}

	/**
	 * restituisce il primo posto libero in cui posizionare il
	 * pallet secondo l'algoritmo greedy, le posizioni sono in valore assoluto
	 * @param distX il punto X dove lo sbilanciamento sulle X risulterebbe 0
	 * @param distY il punto Y dove lo sbilanciamento sulle Y risulterebbe 0
	
	 * @return un vettore in cui la prima posizione indica la colonna (X) dove posizionare il pallet, e la seconda posizione indica la riga (Y) */
	public int[] postoLiberoPallet(double distX, double distY){
		Coordinata coord = new CoordinataPallet(v.getTipoAereo());
		return controllaPosto(distX, distY, occupancyPallet,coord);
	}

	/**
	 * il metodo implementa l'algoritmo generico per la posizione dei pallet/passeggeri
	 * @param distX
	 * @param distY
	 * @param occupancy
	
	 * @param coord Coordinata
	 * @return int[] posti assegnati x,y
	 */
	private int[] controllaPosto(double distX, double distY, boolean occupancy[][],Coordinata coord){

		
		//dimensioni dell'aereo
		int maxRighe = occupancy.length;
		int maxColonne =occupancy[0].length;

		System.out.println("maxRighe:"+occupancy.length +" maxColonne:"+occupancy[0].length);

		//copio le coordinate
		double rigaCellaOttimaRel=distY; //in coordinate relative
		double colonnaCellaOttimaRel=distX; //in coordinate relative


		//discretizzo posizione (intervallo 1 shift 0.5) la colonna
		if(colonnaCellaOttimaRel <=0 && colonnaCellaOttimaRel>=-0.5)
			colonnaCellaOttimaRel = -0.5;
		else
			colonnaCellaOttimaRel = 0.5 + (int)(colonnaCellaOttimaRel - 0.5);
		//discretizzo posizione (intervallo 1 shift 0.5) la riga
		if(rigaCellaOttimaRel <=0 && rigaCellaOttimaRel>=-0.5)
			rigaCellaOttimaRel =-0.5;
		else
			rigaCellaOttimaRel = 0.5 + (int)(rigaCellaOttimaRel - 0.5);


		System.out.println("Discretizzato: x:"+colonnaCellaOttimaRel +" y:"+rigaCellaOttimaRel);


		
		//controllo interno all'aereo riga
		if(coord.XAbs(colonnaCellaOttimaRel) > (maxColonne-1))
			colonnaCellaOttimaRel = coord.XRel(maxColonne-1);
		if(coord.XAbs(colonnaCellaOttimaRel ) < 0 )
			colonnaCellaOttimaRel = coord.XRel(0);
		//controllo interno all'aereo colonna
		if(coord.YAbs(rigaCellaOttimaRel) > (maxRighe-1) )
			rigaCellaOttimaRel = coord.YRel(maxRighe-1);
		if(coord.YAbs(rigaCellaOttimaRel) <0 )
			rigaCellaOttimaRel = coord.YRel(0);



		System.out.println("Interno all'aereo: x:"+colonnaCellaOttimaRel +" y:"+rigaCellaOttimaRel);


		//tengo le variabili anche assolute cosi non devo sempre fare il cambio di sistema di riferimento
		int colonnaCellaOttimaAss = coord.XAbs(colonnaCellaOttimaRel);
		int rigaCellaOttimaAss = coord.YAbs(rigaCellaOttimaRel);

		System.out.println("Interno all'aereo assoluto: x:"+colonnaCellaOttimaAss +" y:"+rigaCellaOttimaAss);



		//provo lo scelto
		System.out.println("provo lo scelto");
		if(!occupancy[rigaCellaOttimaAss][colonnaCellaOttimaAss]){
			occupancy[rigaCellaOttimaAss][colonnaCellaOttimaAss]=true;
			int[] posto = {colonnaCellaOttimaAss, rigaCellaOttimaAss}; //le ritorno in XY
			System.out.println("trovato scelto: [x:"+colonnaCellaOttimaAss+" y:"+rigaCellaOttimaAss+"]"); 
			return posto;
		}
		
		//se arrivo qui significa che la cella ottima è occupata
		System.out.println("è occupato");


		//calcolo la distanza massima da verificare
		int distanzaMassima = maxRighe-rigaCellaOttimaAss-1;
		if(rigaCellaOttimaAss>distanzaMassima) distanzaMassima=rigaCellaOttimaAss;
		//va fatto anche sulla colonna
		if(colonnaCellaOttimaAss>distanzaMassima) distanzaMassima=colonnaCellaOttimaAss;
		if(maxColonne-colonnaCellaOttimaAss-1>distanzaMassima) distanzaMassima=maxColonne-colonnaCellaOttimaAss-1;


		int rigaVertice = rigaCellaOttimaAss;
		int colonnaVertice = colonnaCellaOttimaAss;
		int raggioSpirale=1;
		boolean trovatoAlmenoUno=false;
		double pitagora; //mi dice la distanza di una cella dal punto dove l'avrei voluto mettere,
		double pitagoraMin=Integer.MAX_VALUE;
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
					if(!occupancy[rigaVertice][a]){ //se non è occupato calcolo la distanza
						pitagora = Math.pow(distX-coord.XRel(a), 2)+Math.pow(distY-coord.YRel(rigaVertice), 2);//calcolo la distanza tra due punti anche se e relativa va bene tanto distanza funziona sempre
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
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
					if(!occupancy[a][colonnaCellaOttimaAss+raggioSpirale]){
						pitagora = Math.pow(distX-coord.XRel(colonnaCellaOttimaAss+raggioSpirale), 2)+Math.pow(distY-coord.YRel(a), 2);//calcolo la distanza tra due punti
						if(a==rigaCellaOttimaAss) pitagora -=0.1; //premio per la stessa riga 
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
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
					if(!occupancy[rigaCellaOttimaAss+raggioSpirale][a]){
						pitagora = Math.pow(distX-coord.XRel(a), 2)+Math.pow(distY-coord.YRel(rigaCellaOttimaAss+raggioSpirale), 2);//calcolo la distanza tra due punti
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
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
					if(!occupancy[a][colonnaVertice]){
						pitagora = Math.pow(distX-coord.XRel(colonnaVertice), 2)+Math.pow(distY-coord.YRel(a), 2);//calcolo la distanza tra due punti
						if(a==rigaCellaOttimaAss) pitagora -=0.1; //premio per la stessa riga 
						System.out.println("pitagora:"+pitagora);
						if(pitagora<pitagoraMin){ 
							System.out.println("nuovo minimo");
							pitagoraMin=pitagora;
							trovatoAlmenoUno=true;
							posto[0] = colonnaVertice;
							posto[1] = a;
						}
					}else System.out.println("è occupato");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("sono fuori");
				}
			}

			if(trovatoAlmenoUno){
				System.out.println("ne ho trovato almeno uno, il migliore è: [x:"+posto[0]+" y:"+posto[1]+"]");
				occupancy[posto[1]][posto[0]]=true;
				return posto;
			}

			raggioSpirale++;
			System.out.println("aumento raggio");
		}

		int[] postoNullo = {-1, -1};
		System.out.println("Posto:"+postoNullo[1]+" "+postoNullo[0]);
		return postoNullo;





	}

	protected void stampaOccupancyPasseggeri(int x){
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

	protected void stampaOccupancyPallet(int x){
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
}
