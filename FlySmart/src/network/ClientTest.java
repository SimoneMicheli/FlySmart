/**
 * 
 */
package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;

import model.Aeroporto;
import model.Passeggero;
import model.Sesso;
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
		//System.setProperty("javax.net.ssl.keyStore", "src/network/client/clientkeystore.jks");	//imposto certificato del client da verificare sul server
		//System.setProperty("javax.net.ssl.keyStorePassword", "clientstore");
		System.setProperty("javax.net.ssl.trustStore", "src/network/client/cacert.jks");	//imposto certificati validi del server
		System.setProperty("javax.net.ssl.trustStorePassword", "clienttruststore");
		
		try {
			ServerInterface serv = (ServerInterface) Naming.lookup("rmi://localhost/FlySmartServer");
			
			java.util.List<Aeroporto> s = serv.getAeroporti();
			
			System.out.println(s);
			
			java.util.List<Volo> voli = serv.getVoli(1,2);
			
			System.out.println(voli);
			
			java.util.List<Passeggero> passToAdd = new LinkedList<Passeggero>();
			
			passToAdd.add(new Passeggero("mario", "rossi", 15, 10, 1990, Sesso.M));
			passToAdd.add(new Passeggero("andrea", "bianchi", 5, 2, 1960, Sesso.M));
			passToAdd.add(new Passeggero("valentina", "carrara", 22, 7, 1995, Sesso.F));
			
			int rx = serv.prenotaPasseggero(passToAdd, voli.get(0).getId());
			
			System.out.println(rx);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeatsSoldOutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
