/**
 * 
 */
package network;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import xml.XMLCreate;
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
	private HashMap<Integer, FileLock> locks;
	private int lastID, lastPalletID;
	
	/**
	 * costruttore dell'oggetto server, crea i lock necessari a garantire l'accesso
	 * ai file xml
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 */
	protected Server(RMISSLClientSocketFactory clientFactory, RMISSLServerSocketFactory serverFactory) throws RemoteException {
		super(0, clientFactory, serverFactory);
		
		//lock per accesso al file aeroporti.xml
		aeroportiLock = new FileLockImpl();
		voliLock = new FileLockImpl();
		
		//leggo elenco voli
		List<Volo> voli = new LinkedList<Volo>();
		XMLToObj parserXML = new XMLToObj();
		
		//lock in lettura su volo
		voliLock.acquireReadLock();
		
		voli = parserXML.createVoloList("voli.xml");
		
		voliLock.releaseReadLock();
		
		//creo file lock
		int l = voli.size();
		System.out.println(l);
		//preparo hasmap di capacità l
		locks = new HashMap<Integer, FileLock>(l);
		Iterator<Volo> i = voli.iterator();
		while (i.hasNext()) {
			Volo volo = (Volo) i.next();
			//creo lock per ogni volo
			locks.put(volo.getId(), new FileLockImpl());
		}
	}
	
	/**
	 * restituisce il primo id passeggero libero e blocca tutti i successivi length id
	 * metodo synchronized perchè condiviso tra tutti i server thread
	 * @param length numero id da bloccare
	 * @return primo id disponibile
	 */
	private synchronized int getNextID(int length){
		int oldID = lastID;
		lastID += length;
		return oldID;
	}
	
	private synchronized int getNextID(){
		return getNextID(1);
	}
	
	/**
	 * restituisce il primo id pallet libero e blocca tutti i successivi length id
	 * metodo synchronized perchè condiviso tra tutti i server thread
	 * @param length numero id da bloccare
	 * @return primo id disponibile
	 */
	private synchronized int getNextPalletID(int length){
		int oldID = lastPalletID;
		lastPalletID += length;
		return oldID;
	}

	private synchronized int getNextPalletID(){
		return getNextPalletID(1);
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
		
		List<Passeggero> passeggeri = new ArrayList<Passeggero>();
		ArrayList<Volo> voli = new ArrayList<Volo>();
		XMLToObj parserXML = new XMLToObj();
		
		//aggiorno volo
		voliLock.acquireWriteLock();
		voli = parserXML.createVoloList("voli.xml");
				
		//ottengo riferimento volo
		int pos = Collections.binarySearch(voli,new Integer(2));
		Volo v = voli.get(pos);
		if (v.getPostiDisponibili() - listPass.size() < 0)
			return 2; //posti insufficienti
		v.setPostiDisponibili(v.getPostiDisponibili() - listPass.size());
		
		//ottengo lock su file richiesto
		FileLock lock = locks.get(idVolo);
		lock.acquireWriteLock();
		
		//ottengo lista passeggeri
		passeggeri = parserXML.createPasseggeroList("volo_"+idVolo+".xml");
		
		//aggiungo nuovi passeggeri alla lista
		int id = getNextID(passeggeri.size());
		Iterator<Passeggero> i = listPass.iterator();
		
		while (i.hasNext()) {
			Passeggero passeggero = (Passeggero) i.next();
			//assegno id
			passeggero.setId(id);
			id++;
			passeggeri.add(passeggero);
		}
		
		//salvo dati su xml volo e passeggeri
		XMLCreate<Passeggero> XMLWriter = new XMLCreate<Passeggero>();
		Document d = XMLWriter.createFlySmartDocument(passeggeri);
		try {
			XMLWriter.printDocument(d,"volo_"+idVolo);
		} catch (IOException e) {
			e.printStackTrace();
			return 1; //can't save to file
		}finally{
			voliLock.releaseWriteLock();
			lock.releaseWriteLock();
		}
		
		
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
