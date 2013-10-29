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
	S (2,4,6,10), 
	M (2,12,6,30), 
	L (2,24,6,70);
	
	private final int filePallet;
	private final int colonnePallet;
	private final int filePasseggeri;
	private final int colonnePasseggeri;
	
	TipoAereo(int filePallet,int colonnePallet,int filePasseggeri,int colonnePasseggeri){
		this.filePallet = filePallet;
		this.colonnePallet = colonnePallet;
		this.filePasseggeri = filePasseggeri;
		this.colonnePasseggeri = colonnePasseggeri;
	}

	public int getFilePallet() {
		return filePallet;
	}

	public int getColonnePallet() {
		return colonnePallet;
	}

	public int getFilePasseggeri() {
		return filePasseggeri;
	}

	public int getColonnePasseggeri() {
		return colonnePasseggeri;
	} 
	
	
}
