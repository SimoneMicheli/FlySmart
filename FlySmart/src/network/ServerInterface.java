/**
 * 
 */
package network;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interfaccia server, definisce i metodi eseguibili dai client
 * @author 
 *
 */
public interface ServerInterface extends Remote {
	
	/**
	 * restituisce l'elenco areoporti al client
	 * @return
	 * @throws RemoteException
	 */
	public String getAirports() throws RemoteException;
}
