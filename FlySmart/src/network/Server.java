/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Demarinis - Micheli - Scarpellini
 * implementa i metodi definiti nell'interfaccia del server
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {

	/**
	 * costruttore dell'oggetto server
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 */
	protected Server(RMISSLClientSocketFactory clientFactory, RMISSLServerSocketFactory serverFactory) throws RemoteException {
		super(0, clientFactory, serverFactory);
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getAirports()
	 */
	@Override
	public String getAirports() throws RemoteException {
		// TODO Auto-generated method stub
		return "Malpensa";
	}

}
