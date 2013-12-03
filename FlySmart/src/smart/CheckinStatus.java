/**
 * 
 */
package smart;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Pallet;
import model.Passeggero;

/**
 * la classe rappresenta la risposta del server alla richiesta di caclolo del checkin, 
 * contiene l'eleneco dei pallet e passeggeri e lo sbilanciamento finale
 *
 */
@SuppressWarnings("serial")
public class CheckinStatus implements Serializable{
	
	/**
	 * @return the passeggeri
	 */
	public List<Passeggero> getPasseggeri() {
		return passeggeri;
	}

	/**
	 * @return the pallets
	 */
	public List<Pallet> getPallets() {
		return pallets;
	}

	/**
	 * @return the mom
	 */
	public double[] getMom() {
		return mom;
	}

	private List<Passeggero> passeggeri = new LinkedList<Passeggero>();
	private List<Pallet> pallets = new LinkedList<Pallet>();
	private double mom[] ;
	
	public CheckinStatus(List<Passeggero> pass, List<Pallet> pall, double[] m) {
		mom = m;
		passeggeri.addAll(pass);
		pallets.addAll(pall);
	}
	
	

}
