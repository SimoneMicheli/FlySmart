/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import model.Passeggero;

/**
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public class ServerLauncher {

	/**
	 * avvia il server RMI
	 * @param args
	 */
	public static void main(String[] args) {
		
		//imposta le propriet� di ssl
		System.setProperty("javax.net.ssl.keyStore", "src/network/mykey");
		System.setProperty("javax.net.ssl.keyStorePassword", "keypassword");
		
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
			
			//*************only for testing*********************
			java.util.List<Passeggero> pass = new ArrayList<Passeggero>();
		
			pass.add(new Passeggero("mario", "rossi", "2","3","1990", 'M'));
			pass.add(new Passeggero("dsf", "bianchiu", "25","36","19890", 'M'));
			pass.add(new Passeggero("gh", "asd", "2","33","19970", 'M'));
			
			s.list = pass;
			
			System.out.println(pass);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
