package network;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import util.Options;

/**
 * @author Demarinis - Micheli - Scarpellini
 * Avvia il server RMI
 *
 */
public class ServerLauncher {
	
	/** server logger */
	private static Logger log;
	/**server options*/
	public static Properties opt;
	
	/**
	 * carica il file di configurazione "appConfig.xml" contenente
	 * le opzioni da settare
	 */
	private static void loadConfigurationFile(){
		log.debug("Loading app configuration file");
		opt = new Properties();
		
		InputStream file = ServerLauncher.class.getClassLoader().getResourceAsStream("appConfig.xml");
		
		try {
			opt.loadFromXML(file);
			
			Options.voliFileName = opt.getProperty("voliFileName");
			Options.aeroportiFileName = opt.getProperty("aeroportiFileName");
			Options.voloPassFileName = opt.getProperty("voloPassFileName");
			Options.voloPalletFileName = opt.getProperty("voloPalletFileName");
			
		}catch (Exception e) {
			log.catching(e);
			log.warn("Configuration file not found, Using default options");

			Options.LoadDefaultOptions();
		}
	}
	
	/**
	 * configura i parametri di SSL
	 */
	private static void configureSSL(){
		log.debug("Configurazione SSL");
		//System.setProperty("javax.net.debug", "SSL");
		
		System.setProperty("javax.net.ssl.keyStore", opt.getProperty("keyStore"));	//imposto certificato server
		System.setProperty("javax.net.ssl.keyStorePassword", opt.getProperty("keyStorePassword"));
		
		//System.setProperty("javax.net.ssl.trustStore", opt.getProperty("trustStore"));	//imposto certificati validi dei client
		//System.setProperty("javax.net.ssl.trustStorePassword", opt.getProperty("trustStorePassword"));
	}
	
	/**
	 * configura il logger del server
	 */
	private static void configureLogger(){
		log = LogManager.getLogger(ServerLauncher.class.getName());
	}

	/**
	 * avvia il server RMI
	 */
	private static void startServer(){
		log.debug("Creazione socket SSL");
		//crea i socket per SSL
		RMISSLClientSocketFactory clientfactory = new RMISSLClientSocketFactory();
		RMISSLServerSocketFactory serverfactory = new RMISSLServerSocketFactory();

		String url = "FlySmartServer";

		try {
			//crea l'oggetto server
			Server s = new Server(clientfactory, serverfactory);

			//crea il registro RMI sulla porta di default 1099
			Registry r = LocateRegistry.createRegistry(1099);

			//avvia il server
			r.rebind(url, s );

			log.info("Server RMI avviato");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.catching(e);
		}	
	}
	
	/**
	 * configura ed avvia il server RMI
	 * @param args
	 */
	public static void main(String[] args) {
		
		configureLogger();
		
		loadConfigurationFile();
		
		configureSSL();
		
		startServer();
		
	}
	
}
