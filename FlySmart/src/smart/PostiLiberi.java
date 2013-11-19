/**
 * 
 */
package smart;

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
	
	public PostiLiberi(Volo v){
		this.v = v;
		//creo matrici
		occupancyPallet = new boolean[v.getTipoAereo().getFilePallet()][v.getTipoAereo().getColonnePallet()];
		occupancyPasseggeri = new boolean[v.getTipoAereo().getFilePasseggeri()][v.getTipoAereo().getColonnePasseggeri()];
			
	}
	
	public int[] postoLiberoPasseggeri(double distX, double distY){
		return controllaPosto(distX, distY, occupancyPasseggeri);
	}
	
	/**
	 * restituisce il primo posto libero in cui posizionare il
	 * pallet secondo l'algoritmo greedy, le posizioni sono in valore assoluto
	 * @param distX il punto X dove lo sbilanciamento sulle X risulterebbe 0
	 * @param distY il punto Y dove lo sbilanciamento sulle Y risulterebbe 0
	 * @return un vettore in cui la prima posizione indica la colonna (X) dove posizionare il pallet, e la seconda posizione indica la riga (Y)
	 */
	public int[] postoLiberoPallet(double distX, double distY){
		return controllaPosto(distX, distY, occupancyPallet);
	}
	
	/**
	 * il metodo implementa l'algoritmo generico per la posizione dei pallet/passeggeri
	 * @param distX
	 * @param distY
	 * @param occupancy
	 * @return
	 */
	private int[] controllaPosto(double distX, double distY, boolean occupancy[][]){
		return null;
	}
	
	private void stampaOccupancyPasseggeri(int x){
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

	private void stampaOccupancyPallet(int x){
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
