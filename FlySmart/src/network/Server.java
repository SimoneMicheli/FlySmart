/**
 * 
 */
package network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Properties;

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
	private int lastID=0, lastPalletID=0, lastGroupID=0;
	private Properties config;
	
	private static final String configFileName = "config.xml";
	
	/**
	 * costruttore dell'oggetto server, crea i lock necessari a garantire l'accesso
	 * ai file xml
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 * @throws FileNotFoundException 
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
		//preparo hasmap di capacità l
		locks = new HashMap<Integer, FileLock>(l);
		Iterator<Volo> i = voli.iterator();
		while (i.hasNext()) {
			Volo volo = (Volo) i.next();
			//creo lock per ogni volo
			locks.put(volo.getId(), new FileLockImpl());
		}
		
		//load configuration file
		config = new Properties();
		try {
			FileInputStream configFile = new FileInputStream(configFileName);
			config.loadFromXML(configFile);
			lastID = Integer.parseInt(config.getProperty("lastID", "0"));
			lastPalletID = Integer.parseInt(config.getProperty("lastPalletID", "0"));
			lastGroupID = Integer.parseInt(config.getProperty("lastGropuID", "0"));
		} catch (FileNotFoundException e) {
			System.err.println("Configuration file not found: init with default options");
		} catch (IOException e) {
			System.err.println("Configuration file not found: init with default options");
		}
		
		//server shoutdown
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	System.out.println("shutdown RMI Server");
		    	
		    	//save configuration file
		    	try {
		    		System.out.println("saving configuration file");
					File f = new File(configFileName);
					FileOutputStream configFileOut = new FileOutputStream(f);
					config.storeToXML(configFileOut, "Flysmart Configuration File");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

		    }
		}));
	
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
		config.setProperty("lastID", Integer.toString(lastID));
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
		config.setProperty("lastPalletID", Integer.toString(lastPalletID));
		return oldID;
	}

	private synchronized int getNextPalletID(){
		return getNextPalletID(1);
	}
	
	private synchronized int getNextGroupID(){
		int oldID = lastGroupID;
		lastGroupID ++;
		config.setProperty("lastGroupID", Integer.toString(lastGroupID));
		return oldID;
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
	public int prenotaPasseggero(List<Passeggero> listToAdd, int idVolo) {
		
		List<Passeggero> passeggeri = new ArrayList<Passeggero>();
		ArrayList<Volo> voli = new ArrayList<Volo>();
		XMLToObj parserXML = new XMLToObj();
		
		//aggiorno volo
		voliLock.acquireWriteLock();
		voli = (ArrayList<Volo>) parserXML.createVoloList("voli.xml");
				
		//ottengo riferimento volo
		Collections.sort(voli, VoloComparator.ID_ORDER);
		int pos = Collections.binarySearch(voli,new Integer(2));
		if (pos < 0)
			return 3; //volo non trovato
		
		Volo v = voli.get(pos);
		if (v.getPostiDisponibili() - listToAdd.size() < 0)
			return 2; //posti insufficienti
		
		v.setPostiDisponibili(v.getPostiDisponibili() - listToAdd.size());
		
		//ottengo lock su file richiesto
		FileLock lock = locks.get(idVolo);
		lock.acquireWriteLock();
		
		//ottengo lista passeggeri
		passeggeri = parserXML.createPasseggeroList("volo_"+idVolo+".xml");
		
		//aggiungo nuovi passeggeri alla lista
		int id = getNextID(passeggeri.size());
		int idGruppo = getNextGroupID();
		Iterator<Passeggero> i = listToAdd.iterator();
		
		while (i.hasNext()) {
			Passeggero passeggero = (Passeggero) i.next();
			//assegno id
			passeggero.setId(id);
			passeggero.setIdGruppo(idGruppo);
			id++;
			passeggeri.add(passeggero);
		}
		
		//salvo dati su xml volo e passeggeri
		XMLCreate<Volo> XMLVoloWriter = new XMLCreate<Volo>();
		Document VoliDocument = XMLVoloWriter.createFlySmartDocument(voli);
		
		XMLCreate<Passeggero> XMLPassWriter = new XMLCreate<Passeggero>();
		Document PassDocument = XMLPassWriter.createFlySmartDocument(passeggeri);
		
		try {
			XMLPassWriter.printDocument(PassDocument,"volo_"+idVolo+".xml");
			XMLVoloWriter.printDocument(VoliDocument, "voli.xml");
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
