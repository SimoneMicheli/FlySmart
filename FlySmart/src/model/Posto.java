/**
 * 
 */
package model;

/**
 * Rappresenta tutti gli oggetti
 * che possono avere un posto sull'aereo
 *
 */
@SuppressWarnings("serial")
public abstract class Posto extends Model{
	
	/** Fila del posto del passeggero */
	protected Integer fila;
	
	/** Colonna del posto del passeggero */
	protected Integer colonna;
	
	/**
	 * Ottiene il numero di fila sul volo prenotato dal passeggero
	 *
	 * @return Il numero di fila sul volo prenotato dal passeggero */
	public Integer getFila() {
		return fila;
	}
	
	/**
	 * Setta il numero di fila sul volo prenotato dal passeggero
	 *
	 * @param fila Il numero di fila sul volo prenotato dal passeggero
	 */
	public void setFila(Integer fila) {
		this.fila = fila;
	}


	/**
	 * Ottiene il numero di colonna sul volo prenotato dal passeggero
	 *
	 * @return Il numero di colonna sul volo prenotato dal passeggero */
	public Integer getColonna() {
		return colonna;
	}

	/**
	 * Setta il numero di colonna sul volo prenotato dal passeggero
	 *
	 * @param colonna Il numero di colonna sul volo prenotato dal passeggero
	 */
	public void setColonna(Integer colonna) {
		this.colonna = colonna;
	}
}
