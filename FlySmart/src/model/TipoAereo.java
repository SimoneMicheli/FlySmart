/**
 * 
 */
package model;

/**
 * definisce il tipo di aereo ed il numero di posto
 *
 */
public enum TipoAereo {
	//numero di colonne pallet, numero di fila pallet, numero di colonne passeggeri, numero file passeggeri
	/**Aereo piccolo*/
	S (2,4,6,12), 
	/**Aereo medio*/
	M (2,8,6,24), 
	/**Aereo grande*/
	L (2,12,6,36);
	
	private final int filePallet;
	private final int colonnePallet;
	private final int filePasseggeri;
	private final int colonnePasseggeri;
	
	/**
	 * Constructor for TipoAereo.
	 * @param colonnePallet int
	 * @param filePallet int
	 * @param colonnePasseggeri int
	 * @param filePasseggeri int
	 */
	TipoAereo(int colonnePallet,int filePallet,int colonnePasseggeri,int filePasseggeri){
		this.filePallet = filePallet;
		this.colonnePallet = colonnePallet;
		this.filePasseggeri = filePasseggeri;
		this.colonnePasseggeri = colonnePasseggeri;
	}

	/**
	 * restituisce il numero di file disponibili per i pallet sull'aereo
	
	 * @return numero file pallet */
	public int getFilePallet() {
		return filePallet;
	}

	/**
	 * restituisce il numero di colonne disponibili per i pallet sull'aereo
	
	 * @return numero colonne pallet */
	public int getColonnePallet() {
		return colonnePallet;
	}

	/**
	 * restituisce il numero di file disponibili per i passeggeri sull'aereo
	
	 * @return numero file passeggeri */
	public int getFilePasseggeri() {
		return filePasseggeri;
	}

	/**
	 * restituisce il numero di colonne disponibili per i passeggeri sull'aereo
	
	 * @return numero di colonne passeggeri */
	public int getColonnePasseggeri() {
		return colonnePasseggeri;
	} 
	
	
}
