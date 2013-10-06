/**
 * 
 */
package network;

import java.awt.List;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Aeroporto;
import model.Passeggero;
import model.Volo;

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
			
			java.util.List<Aeroporto> s = serv.getAeroporti();
			
			System.out.println(s);
			
			java.util.List<Volo> v = serv.getVoli(1, 2);
			
			System.out.println(v);
			
			java.util.List<Passeggero> passToAdd = new LinkedList<Passeggero>();
			
			passToAdd.add(new Passeggero("mario", "rossi", 15, 10, 1990, 'm'));
			passToAdd.add(new Passeggero("andrea", "bianchi", 5, 2, 1960, 'm'));
			passToAdd.add(new Passeggero("valentina", "carrara", 22, 7, 1995, 'f'));
			
			int rx = serv.prenotaPasseggero(passToAdd, 2);
			
			System.out.println(rx);
			
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
