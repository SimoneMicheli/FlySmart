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

	/**
	 * Constructor for PostiLiberi.
	 * @param v Volo
	 */
	public PostiLiberi(Volo v){
		this.v = v;
		//creo matrici
		occupancyPallet = new boolean[v.getTipoAereo().getFilePallet()][v.getTipoAereo().getColonnePallet()];
		occupancyPasseggeri = new boolean[v.getTipoAereo().getFilePasseggeri()][v.getTipoAereo().getColonnePasseggeri()];
	}
	
	/**
	 * restituisce il primo posto libero vicino a quello richiesto per i passeggeri
	 * @param ottimoX colonna posto richiesto
	 * @param ottimoY riga posto richiesto
	 * @return posto in cui posizionare il passeggero
	 */
	public Posto postoLiberoPasseggeri(double ottimoX, double ottimoY){
		Coordinata coord = new CoordinataPasseggero(v.getTipoAereo());
		int maxRighe = v.getTipoAereo().getFilePasseggeri();
		int maxColonne = v.getTipoAereo().getColonnePasseggeri();
		return postoLibero(ottimoX, ottimoY, occupancyPasseggeri, coord, maxRighe, maxColonne);
	}
	
	/**
	 * restituisce il primo posto libero in cui posizionare il
	 * pallet secondo l'algoritmo greedy, le posizioni sono in valore assoluto
	 * @param distX il punto X dove lo sbilanciamento sulle X risulterebbe 0
	 * @param distY il punto Y dove lo sbilanciamento sulle Y risulterebbe 0
	
	 * @return posto in cui posizionare il pallet 
	 */
	public Posto postoLiberoPallet(double distX, double distY){
		Coordinata coord = new CoordinataPallet(v.getTipoAereo());
		int maxRighe = v.getTipoAereo().getFilePallet();
		int maxColonne = v.getTipoAereo().getColonnePallet();
		return postoLibero(distX, distY, occupancyPallet, coord, maxRighe, maxColonne);
	}
	
	/**
	 * implementa l'agoritmo greedi in modo generico, l'algoritmo funziona sia per i pallet che per i passeggeri
	 * ed ha complessità N pari alla dimensione dell'aereo
	 * @param ottimoX colonna in cui si vuole posizionare l'oggetto
	 * @param ottimoY riga in cui si vuole posizionare l'oggetto
	 * @param occupancy matrice indicante i posti occupati
	 * @param coord oggetto di trasformazione coordinate
	 * @param maxRighe numero di file sull'aereo richiesto
	 * @param maxColonne numero di colonne sull'aereo richiesto
	 * @return
	 */
	protected Posto postoLibero(double ottimoX, double ottimoY,boolean[][] occupancy, Coordinata coord, int maxRighe, int maxColonne){
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
		
		LinkedList<Posto> posti = new LinkedList<Posto>();
		
		//preparo posti liberi
		for(int r=0; r<maxRighe; r++)
			for(int c=0; c<maxColonne; c++)
				if(occupancy[r][c] == false)
					posti.add(new Posto(c, r, postoX, postoY));

		//ordinamento posti
		Posto postiOrdinati = ordinaPostiLiberi(posti, maxRighe);
	
		//contrassegno posto come occupati
		occupancy[postiOrdinati.y][postiOrdinati.x] = true;
		
		return postiOrdinati;

	}
	
	/**
	 * ritorna il posto più vicino a quello richiesto
	 * eseguendo un ordinamento lineare tra i posti disponibili 
	 * @param posti disponibili
	 * @return posto più vicino
	 */
	protected Posto ordinaPostiLiberi(List<Posto> posti, int maxRighe){
			
		@SuppressWarnings("unchecked")
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
	
}
