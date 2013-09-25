/**
 * 
 */
package network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Aeroporto;
import model.Passeggero;

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
	public List<Aeroporto> getAirports() throws RemoteException;
	
	public List<Passeggero> getPassengers() throws RemoteException;
}
