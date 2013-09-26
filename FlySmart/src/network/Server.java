/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import xml.XMLToObj;
import comparator.AeroportoComparator;
import model.Aeroporto;
import model.Passeggero;

/**
 * @author Demarinis - Micheli - Scarpellini
 * implementa i metodi definiti nell'interfaccia del server
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1112973689097758070L;
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
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getAirports()
	 */
	@Override
	public List<Aeroporto> getAirports() throws RemoteException {
		// TODO Auto-generated method stub
		XMLToObj instance = new XMLToObj();
		elencoAeroporti = new ArrayList<Aeroporto>();
		elencoAeroporti = instance.createAeroportoList("aeroporti.xml");

		Collections.sort(elencoAeroporti, AeroportoComparator.ID_ORDER);
		
		System.out.println("***** "+elencoAeroporti);
		return elencoAeroporti;
	}

	@Override
	public List<Passeggero> getPassengers() throws RemoteException {
		// TODO Auto-generated method stub
		return list;
	}

}
