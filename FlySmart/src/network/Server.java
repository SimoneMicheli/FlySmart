/**
 * 
 */
package network;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import xml.XMLToObj;
import comparator.*;
import fileLock.*;
import model.*;

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
	private FileLock aeroportiLock, voliLock;
	
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
		aeroportiLock = new FileLockImpl();
		voliLock = new FileLockImpl();
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getAeroporti()
	 */
	@Override
	public List<Aeroporto> getAeroporti() throws RemoteException {
		
		List<Aeroporto> aeroporti = new LinkedList<Aeroporto>();
		XMLToObj parserXML = new XMLToObj();
		//acquisisco lock in lettura
		aeroportiLock.acquireReadLock();
		
		//parse xml data
		aeroporti = parserXML.createAeroportoList("aeroporti.xml");

		//release lock
		aeroportiLock.releaseReadLock();
		
		//ordina eroport in base al nome
		Collections.sort(aeroporti, AeroportoComparator.NAME_ORDER);
		
		//restituisce elnco aeroporti al client
		return aeroporti;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getVoli(int, int)
	 */
	@Override
	public List<Volo> getVoli(int idp, int ida) throws RemoteException {
		List<Volo> voli = new LinkedList<Volo>();
		List<Volo> voliRichiesti = new LinkedList<Volo>();
		XMLToObj parserXML = new XMLToObj();
		
		//lock in lettura su volo
		voliLock.acquireReadLock();
		
		voli = parserXML.createVoloList("voli.xml");
		
		voliLock.releaseReadLock();
		
		//estrai voli richiesti
		Iterator<Volo> i = voli.iterator();
		
		while (i.hasNext()) {
			Volo volo = (Volo) i.next();
			if (volo.getAeroportoPartenza() == idp && volo.getAeroportoDestinazione() == ida && volo.getDataOra().compareTo(new Date()) > 0){
				voliRichiesti.add(volo);
			}
		}
		Collections.sort(voliRichiesti, VoloComparator.DATA_ORDER);
		return voliRichiesti;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPasseggero(java.util.List, int)
	 */
	@Override
	public int prenotaPasseggero(List<Passeggero> listPass, int idVolo) {
		
		
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPallet(java.util.List, int)
	 */
	@Override
	public int prenotaPallet(List<Pallet> listPallet, int idVolo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
