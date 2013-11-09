package util;

/**
 * contine tutte le configurazioni statiche
 *
 */
public abstract class Options {
	
	/**percorso file voli.xml */
	public static String voliFileName;
	/**percorso file aeroporti.xml */
	public static String aeroportiFileName;
	/**percorso dei file contenenti i passeggeri dei voli*/
	public static String voloPassFileName;
	/**percorso dei file contenenti i pallet dei voli */
	public static String voloPalletFileName;
	
	/**
	 * inizializza le opzioni con i valori di default se richiesto
	 */
	public static void LoadDefaultOptions(){
		Options.voliFileName = "data/voli.xml";
		Options.aeroportiFileName = "data/aeroporti.xml";
		Options.voloPassFileName = "data/volo_%s_pass.xml";
		Options.voloPalletFileName ="data/volo_%s_pallet.xml";
	}

}
