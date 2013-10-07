/**
 * 
 */
package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
		
		//imposta le proprietˆ di ssl
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
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		//=====================solo per eclipse======================
    	System.out.println("You're using Eclipse; click in this console and	" +
    						"press ENTER to call System.exit() and run the shutdown routine.");
    	try {
    		System.in.read();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	System.exit(0);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("finalize mathod");
		super.finalize();
	}
}
