/**
 * 
 */
package network;

import java.awt.List;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import model.Aeroporto;
import model.Passeggero;

/**
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public class ClientTest {
	public static final int NUM_OF_READERS = 3;
    public static final int NUM_OF_WRITERS = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.trustStore", "src/network/clientstore");
		System.setProperty("javax.net.ssl.trustStorePassword", "clientstorepassword");
		
		try {
			ServerInterface serv = (ServerInterface) Naming.lookup("rmi://localhost/FlySmartServer");
			
			/*java.util.List<Aeroporto> s = serv.getAirports();
			
			System.out.println(s);
			
			java.util.List<Passeggero> list = serv.getPassengers();
			
			System.out.println(list);
			System.out.println(s);
			
			//s = serv.getAirports();
			System.out.println(s);*/
			
			Thread[] readerArray = new Thread[NUM_OF_READERS];
		     Thread[] writerArray = new Thread[NUM_OF_WRITERS];

		      for (int i = 0; i < NUM_OF_READERS; i++) {
		         readerArray[i] = new Thread(new ReaderTest(serv, i));
		         readerArray[i].start();
		      }

		      for (int i = 0; i < NUM_OF_WRITERS; i++) {
		         writerArray[i] = new Thread(new WriterTest(serv,i));
		         writerArray[i].start();
		      }
			
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
