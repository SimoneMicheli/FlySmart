package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Demarinis - Micheli - Scarpellini
 * Avvia il server RMI
 *
 */
public class ServerLauncher {

	/**
	 * avvia il server RMI
	 * @param args
	 */
	public static void main(String[] args) {
		
		//imposta le proprietà di ssl
		//System.setProperty("javax.net.debug", "SSL");
		System.setProperty("javax.net.ssl.keyStore", "src/network/server/keystore.jks");	//imposto certificato server
		System.setProperty("javax.net.ssl.keyStorePassword", "serverstore");
		//System.setProperty("javax.net.ssl.trustStore", "src/network/server/servercacert.jks");	//imposto certificati validi dei client
		//System.setProperty("javax.net.ssl.trustStorePassword", "servertruststore");
		
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
		
			System.out.println("FlySmart RMI server STARTED");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
