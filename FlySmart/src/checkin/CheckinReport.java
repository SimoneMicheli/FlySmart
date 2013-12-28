package checkin;

import java.util.List;

import model.Pallet;
import model.Passeggero;

public interface CheckinReport {

	/**
	
	 * @return l'elenco dei passeggeri */
	public abstract List<Passeggero> getPasseggeri();

	/**
	
	 * @return l'elenco dei pallet */
	public abstract List<Pallet> getPallets();

	/**
	
	 * @return lo sbilanciamento finale dell'aereo */
	public abstract double[] getMom();

}