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
public class CheckinReportImpl implements Serializable, CheckinReport{
	
	

	private List<Passeggero> passeggeri = new LinkedList<Passeggero>();
	private List<Pallet> pallets = new LinkedList<Pallet>();
	private double mom[] ;
	
	/**
	 * Constructor for CheckinReport.
	 * @param pass List<Passeggero> lista di passeggeri nel volo
	 * @param pall List<Pallet> lista di pallet nel volo
	 * @param m double[] sbilanciamento del volo
	 */
	public CheckinReportImpl(List<Passeggero> pass, List<Pallet> pall, double[] m) {
		mom = m;
		passeggeri.addAll(pass);
		pallets.addAll(pall);
	}
/* (non-Javadoc)
 * @see checkin.CheckinReport#getPasseggeri()
 */
	@Override
	public List<Passeggero> getPasseggeri() {
		return passeggeri;
	}

	/* (non-Javadoc)
	 * @see checkin.CheckinReport#getPallets()
	 */
	@Override
	public List<Pallet> getPallets() {
		return pallets;
	}

	/* (non-Javadoc)
	 * @see checkin.CheckinReport#getMom()
	 */
	@Override
	public double[] getMom() {
		return mom;
	}
	

}
