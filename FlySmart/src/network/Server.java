/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import model.Aeroporto;
import model.Passeggero;

/**
 * @author Demarinis - Micheli - Scarpellini
 * implementa i metodi definiti nell'interfaccia del server
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
	public List<Passeggero> list;
	private List<Aeroporto> elencoAeroporti;
	
	/**
	 * costruttore dell'oggetto server
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 */
	protected Server(RMISSLClientSocketFactory clientFactory, RMISSLServerSocketFactory serverFactory) throws RemoteException {
		super(0, clientFactory, serverFactory);
		
		//carica elenco aeroporti
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(0, "Malpensa", 20, 20));
		elencoAeroporti.add(new Aeroporto(0, "Bergamo", 50, 10));
		elencoAeroporti.add(new Aeroporto(0, "Fiumicino", 100, 40));
		
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getAirports()
	 */
	@Override
	public List<Aeroporto> getAirports() throws RemoteException {
		// TODO Auto-generated method stub
		return elencoAeroporti;
	}

	@Override
	public List<Passeggero> getPassengers() throws RemoteException {
		// TODO Auto-generated method stub
		return list;
	}

}
