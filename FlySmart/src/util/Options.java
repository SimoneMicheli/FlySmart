package util;

/**
 * contine tutte le configurazioni statiche
 *
 */
public abstract class Options {

	/**percorso file aeroporti.xml */
	public static String aeroportiFileName;
	
	/**
	 * inizializza le opzioni con i valori di default se richiesto
	 */
	public static void LoadDefaultOptions(){
		Options.aeroportiFileName = "data/xml/aeroporti.xml";
	}

}
