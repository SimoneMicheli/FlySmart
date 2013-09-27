/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.SliderUI;

import xml.XMLToObj;
import comparator.AeroportoComparator;
import fileLock.FileLock;
import fileLock.FileLockImpl;
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
	
	private List<String> strs = new ArrayList<String>();
	
	FileLock airportLock;
	
	/**
	 * costruttore dell'oggetto server, crea i lock necessari a garantire l'accesso
	 * ai file xml
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 */
	protected Server(RMISSLClientSocketFactory clientFactory, RMISSLServerSocketFactory serverFactory, FileLock ... locks) throws RemoteException {
		super(0, clientFactory, serverFactory);
		
		//lock per accesso al file aeroporti.xml
		airportLock = new FileLockImpl();
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getAirports()
	 */
	@Override
	public List<Aeroporto> getAirports() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("entro sezione critica");
		airportLock.acquireReadLock();
		System.out.println("nella sezione critica");
		XMLToObj instance = new XMLToObj();
		elencoAeroporti = new ArrayList<Aeroporto>();
		elencoAeroporti = instance.createAeroportoList("aeroporti.xml");

		Collections.sort(elencoAeroporti, AeroportoComparator.ID_ORDER);
		
		System.out.println("***** "+elencoAeroporti);
		System.out.println("esco sezione critica");
		airportLock.releaseReadLock();
		System.out.println("fuori sezione critica");
		return elencoAeroporti;
	}

	@Override
	public List<Passeggero> getPassengers() throws RemoteException {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<String> setString(String s) throws RemoteException {
		airportLock.acquireWriteLock();
		System.out.println("scrivo stringa: "+s);
		try { Thread.sleep(250); } catch (InterruptedException e) {}
		airportLock.releaseWriteLock();
		return null;
	}

	@Override
	public List<String> getString() throws RemoteException {
		airportLock.acquireReadLock();
		System.out.println("lettura");
		try { Thread.sleep(50); } catch (InterruptedException e) {}
		airportLock.releaseReadLock();
		return null;
	}

}
