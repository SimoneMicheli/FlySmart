/**
 * 
 */
package checkin;

import java.io.Serializable;
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
public class CheckinReport implements Serializable{
	
	

	private List<Passeggero> passeggeri = new LinkedList<Passeggero>();
	private List<Pallet> pallets = new LinkedList<Pallet>();
	private double mom[] ;
	
	/**
	 * Constructor for CheckinReport.
	 * @param pass List<Passeggero> lista di passeggeri nel volo
	 * @param pall List<Pallet> lista di pallet nel volo
	 * @param m double[] sbilanciamento del volo
	 */
	public CheckinReport(List<Passeggero> pass, List<Pallet> pall, double[] m) {
		mom = m;
		passeggeri.addAll(pass);
		pallets.addAll(pall);
	}
/**
	
	 * @return l'elenco dei passeggeri */
	public List<Passeggero> getPasseggeri() {
		return passeggeri;
	}

	/**
	
	 * @return l'elenco dei pallet */
	public List<Pallet> getPallets() {
		return pallets;
	}

	/**
	
	 * @return lo sbilanciamento finale dell'aereo */
	public double[] getMom() {
		return mom;
	}
	

}
