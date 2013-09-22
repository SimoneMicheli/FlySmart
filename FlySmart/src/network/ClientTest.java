/**
 * 
 */
package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.trustStore", "src/network/clientstore");
		System.setProperty("javax.net.ssl.trustStorePassword", "clientstorepassword");
		
		try {
			ServerInterface serv = (ServerInterface) Naming.lookup("rmi://localhost/FlySmartServer");
			
			String s = serv.getAirports();
			
			System.out.println(s);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
