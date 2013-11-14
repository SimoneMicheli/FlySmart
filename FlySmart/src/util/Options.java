package util;

/**
 * contine tutte le configurazioni statiche
 *
 */
public abstract class Options {

	/**percorso file aeroporti.xml */
	public static String aeroportiFileName;
	
	public static String mongoHost;
	
	public static int mongoPort;
	
	public static String mongoDBName;
	
	/**
	 * inizializza le opzioni con i valori di default se richiesto
	 */
	public static void LoadDefaultOptions(){
		Options.aeroportiFileName = "data/xml/aeroporti.xml";
		
		Options.mongoHost = "127.0.0.1";
		
		Options.mongoPort = 27017;
		
		Options.mongoDBName = "FlySmart";
	}

}
