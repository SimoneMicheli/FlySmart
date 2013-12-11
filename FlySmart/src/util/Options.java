package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import network.ServerLauncher;

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
	
	public static String keyStore;
	
	public static String keyStorePassword;
	
	private static Logger log;
	
	/**server options*/
	public static Properties opt;
	
	/**
	 * inizializza le opzioni ricevendo il percorso del file di configurazione
	 * @param name
	 */
	public static void initOptions(String name){
		log = LogManager.getLogger(ServerLauncher.class.getName());
		loadConfigurationFile(name);
	}
	
	/**
	 * inizializza le opzioni con il file di configurazione di default
	 */
	public static void initOptions(){
		initOptions("./src/config/appConfig.xml");
	}
	
	/**
	 * carica il file di configurazione "appConfig.xml" contenente
	 * le opzioni da settare
	 */
	protected static void loadConfigurationFile(String name){
		log.info("Loading app configuration file: "+name);
		opt = new Properties();
		
		try {
			InputStream file = new FileInputStream(name);
			opt.loadFromXML(file);
			Options.aeroportiFileName = opt.getProperty("aeroportiFileName");
			Options.mongoHost = opt.getProperty("mongoHost");
			Options.mongoPort = Integer.parseInt(opt.getProperty("mongoPort"));
			Options.mongoDBName = opt.getProperty("mongoDBName");
			Options.keyStore = opt.getProperty("keyStore");
			Options.keyStorePassword =  opt.getProperty("keyStorePassword");
			
		}catch (Exception e) {
			log.warn("Configuration file not found, Using default options");

			LoadDefaultOptions();
		}
		
	}
	
	/**
	 * inizializza le opzioni con i valori di default se richiesto
	 */
	protected static void LoadDefaultOptions(){
		Options.aeroportiFileName = "data/xml/aeroporti.xml";
		
		Options.mongoHost = "127.0.0.1";
		
		Options.mongoPort = 27017;
		
		Options.mongoDBName = "FlySmart";
	}

}
