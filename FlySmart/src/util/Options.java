package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
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
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	public static void initOptions(String name) throws InvalidPropertiesFormatException, IOException{
		log = LogManager.getLogger(ServerLauncher.class.getName());
		
		InputStream file = new FileInputStream(name);
		
		loadConfigurationFile(file);
	}
	
	/**
	 * inizializza le opzioni con il file di configurazione di default
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	public static void initOptions() throws InvalidPropertiesFormatException, IOException{
		log = LogManager.getLogger(ServerLauncher.class.getName());
		log.warn("Configuration file not configured, Using default options");
		InputStream file = ServerLauncher.class.getClassLoader().getResourceAsStream("appConfig.xml");
		
		if(file == null)
			throw new FileNotFoundException("Default configuration file not found");
		
		loadConfigurationFile(file);
	}
	
	/**
	 * carica il file di configurazione "appConfig.xml" contenente
	 * le opzioni da settare
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	protected static void loadConfigurationFile(InputStream file) throws InvalidPropertiesFormatException, IOException{
		log.info("Caricamento file di configurazione");
		opt = new Properties();
		
		
			
			opt.loadFromXML(file);
			
			Options.aeroportiFileName = opt.getProperty("aeroportiFileName");
			Options.mongoHost = opt.getProperty("mongoHost");
			Options.mongoPort = Integer.parseInt(opt.getProperty("mongoPort"));
			Options.mongoDBName = opt.getProperty("mongoDBName");
			Options.keyStore = opt.getProperty("keyStore");
			Options.keyStorePassword =  opt.getProperty("keyStorePassword");
			
		
		
	}
	

}
