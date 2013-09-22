/**
 * 
 */
package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
		
		System.setProperty("javax.net.ssl.keyStore", "src/network/mykey");
		System.setProperty("javax.net.ssl.keyStorePassword", "keypassword");
		
		RMISSLClientSocketFactory clientfactory = new RMISSLClientSocketFactory();
		RMISSLServerSocketFactory serverfactory = new RMISSLServerSocketFactory();
		
		String url = "rmi://localhost/FlySmartServer";
		
		try {
			Server s = new Server(clientfactory, serverfactory);
		
			LocateRegistry.createRegistry(1099);
	
			Naming.rebind(url, s );
		
			System.out.println("FlySmart RMI server STARTED");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
