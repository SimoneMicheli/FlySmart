package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InvalidPropertiesFormatException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import util.Options;

/**
 * @author Demarinis - Micheli - Scarpellini
 * Avvia il server RMI
 *
 */
public final class ServerLauncher {
	
	/** server logger */
	private static Logger log;
	
	/**
	 * configura i parametri di SSL
	 */
	private static void configureSSL(){
		log.debug("Configurazione SSL");
		//System.setProperty("javax.net.debug", "SSL");
		
		System.setProperty("javax.net.ssl.keyStore", Options.keyStore);	//imposto certificato server
		System.setProperty("javax.net.ssl.keyStorePassword", Options.keyStorePassword);
		
		//System.setProperty("javax.net.ssl.trustStore", opt.getProperty("trustStore"));	//imposto certificati validi dei client
		//System.setProperty("javax.net.ssl.trustStorePassword", opt.getProperty("trustStorePassword"));
	}
	
	/**
	 * configura il logger del server
	 */
	private static void configureLogger(){
		log = LogManager.getLogger(ServerLauncher.class.getName());
		log.info("================Launching Server=============");
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
			ServerImpl s = new ServerImpl(clientfactory, serverfactory);

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
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		
		configureLogger();
		
		if(args.length > 0)
			Options.initOptions(args[0]);
		else
			Options.initOptions();
		
		configureSSL();
		
		startServer();
		
	}
	
}
