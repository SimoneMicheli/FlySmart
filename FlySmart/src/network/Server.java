package network;

import java.io.FileNotFoundException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import util.Options;
import xml.XMLCreate;
import xml.XMLToObj;
import comparator.*;
import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;
import fileLock.*;
import model.*;

/** Implementa i metodi definiti nell'interfaccia del server
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
	private static final long serialVersionUID = -1112973689097758070L;
	
	/**lock assegnato al file contenente i voli */
	private FileLock voliLock;
	/**hash map contenente per goni volo (id) il lock assegnato passeggeri/pallet */
	private HashMap<Integer, FileLock> passLocks, palletLocks;
	/** logger*/
	Logger log;
	
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
		
		log = LogManager.getLogger(Server.class.getCanonicalName().toString());
		
		log.entry();
		//lock per accesso al file voli.xml
		voliLock = new FileLockImpl();
		
		//leggo elenco voli
		List<Volo> voli = new LinkedList<Volo>();
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);
		
		//lock in lettura su volo
		voliLock.acquireReadLock();
		
		voli = parserXML.readObj(Options.voliFileName);
		
		voliLock.releaseReadLock();
		
		//creo file lock
		int l = voli.size();
		//preparo hasmap di capacitˆ l
		passLocks = new HashMap<Integer, FileLock>(l);
		palletLocks = new HashMap<Integer, FileLock>(l);
		Iterator<Volo> i = voli.iterator();
		while (i.hasNext()) {
			Volo volo = (Volo) i.next();
			//creo lock per ogni volo
			passLocks.put(volo.getId(), new FileLockImpl());
			palletLocks.put(volo.getId(), new FileLockImpl());
		}
		log.exit();
	}
	
	
	
	/* (non-Javadoc)
	 * @see network.ServerInterface#getAeroporti()
	 */
	@Override
	public List<Aeroporto> getAeroporti() throws RemoteException {
		
		List<Aeroporto> aeroporti = new LinkedList<Aeroporto>();
		XMLToObj<Aeroporto> parserXML = new XMLToObj<Aeroporto>(Aeroporto.class);
	
		//parse xml data lock non richiesto perchè file usato in sola lettura
		aeroporti = parserXML.readObj(Options.aeroportiFileName);
		
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
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);
		
		//lock in lettura su volo
		voliLock.acquireReadLock();
		
		voli = parserXML.readObj(Options.voliFileName);
		
		voliLock.releaseReadLock();
		
		//estrai voli richiesti
		Iterator<Volo> i = voli.iterator();
		
		while (i.hasNext()) {
			Volo volo = (Volo) i.next();
			if (volo.getAeroportoPartenza() == idp && volo.getAeroportoDestinazione() == ida && volo.getDataOra().compareTo(new Date()) > 0 && volo.getPostiDisponibili()>0){
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
	public int prenotaPasseggero(List<Passeggero> listToAdd, int idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		
		List<Passeggero> passeggeri = new ArrayList<Passeggero>();
		ArrayList<Volo> voli = new ArrayList<Volo>();
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);
		
		//aggiorno volo
		voliLock.acquireWriteLock();
		voli = (ArrayList<Volo>) parserXML.readObj(Options.voliFileName);
				
		//ottengo riferimento volo
		Collections.sort(voli, VoloComparator.ID_ORDER);
		int pos = Collections.binarySearch(voli,new Integer(idVolo));
		if (pos < 0){
			voliLock.releaseWriteLock();
			throw new FlightNotFoundException(idVolo); //volo non trovato
		}
		
		Volo v = voli.get(pos);
		if (v.getPostiDisponibili() - listToAdd.size() < 0){
			voliLock.releaseWriteLock();
			throw new SeatsSoldOutException(idVolo); //posti insufficienti
		}
		
		v.setPostiDisponibili(v.getPostiDisponibili() - listToAdd.size());
		
		//ottengo lock su file richiesto
		FileLock lock = passLocks.get(idVolo);
		lock.acquireWriteLock();
		
		//ottengo lista passeggeri
		XMLToObj<Passeggero> parserXMLPass = new XMLToObj<Passeggero>(Passeggero.class);
		passeggeri = parserXMLPass.readObj( String.format(Options.voloPassFileName, idVolo));
		
		//aggiungo nuovi passeggeri alla lista
		int id = v.getNextID(listToAdd.size());
		int idGruppo = v.getNextGroupID();
		Iterator<Passeggero> i = listToAdd.iterator();
		
		while (i.hasNext()) {
			Passeggero passeggero = (Passeggero) i.next();
			//assegno id
			passeggero.setId(id);
			passeggero.setIdGruppo(idGruppo);
			id++;
			passeggeri.add(passeggero);
		}
		
		//salvo dati su xml
		XMLCreate<Passeggero> XMLPassWriter = new XMLCreate<Passeggero>();
		Document PassDocument = XMLPassWriter.createFlySmartDocument(passeggeri);
		XMLCreate<Volo> XMLVoloWriter = new XMLCreate<Volo>();
		Document VoliDocument = XMLVoloWriter.createFlySmartDocument(voli);
		
		try {
			XMLPassWriter.printDocument(PassDocument,String.format(Options.voloPassFileName, idVolo));
			XMLVoloWriter.printDocument(VoliDocument, Options.voliFileName);
		} catch (IOException e) {
			return -1;
		} finally{
			voliLock.releaseWriteLock();
			lock.releaseWriteLock();
		}
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPallet(java.util.List, int)
	 */
	@Override
	public int prenotaPallet(List<Pallet> listToAdd, int idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		List<Pallet> pallets = new ArrayList<Pallet>();
		ArrayList<Volo> voli = new ArrayList<Volo>();
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);
		
		//aggiorno volo
		voliLock.acquireWriteLock();
		voli = (ArrayList<Volo>) parserXML.readObj(Options.voliFileName);
				
		//ottengo riferimento volo
		Collections.sort(voli, VoloComparator.ID_ORDER);
		int pos = Collections.binarySearch(voli,new Integer(idVolo));
		if (pos < 0){
			voliLock.releaseWriteLock();
			throw new FlightNotFoundException(idVolo); //volo non trovato
		}
		
		Volo v = voli.get(pos);
		if (v.getPalletDisponibili() - listToAdd.size() < 0){
			voliLock.releaseWriteLock();
			throw new SeatsSoldOutException(idVolo); //posti insufficienti
		}
		
		v.setPalletDisponibili(v.getPalletDisponibili() - listToAdd.size());
		
		
		//ottengo lock su file richiesto
		FileLock lock = palletLocks.get(idVolo);
		lock.acquireWriteLock();
		
		//ottengo lista passeggeri
		XMLToObj<Pallet> parserXMLPallet = new XMLToObj<Pallet>(Pallet.class);
		pallets = parserXMLPallet.readObj(String.format(Options.voloPalletFileName, idVolo));
		
		//aggiungo nuovi passeggeri alla lista
		int id = v.getNextPalletID(listToAdd.size());
		Iterator<Pallet> i = listToAdd.iterator();
		
		while (i.hasNext()) {
			Pallet p = (Pallet) i.next();
			//assegno id
			p.setId(id);
			id++;
			pallets.add(p);
		}
		
		//salvo dati su xml 
		XMLCreate<Pallet> XMLPalletWriter = new XMLCreate<Pallet>();
		Document PalletDocument = XMLPalletWriter.createFlySmartDocument(pallets);
		XMLCreate<Volo> XMLVoloWriter = new XMLCreate<Volo>();
		Document VoliDocument = XMLVoloWriter.createFlySmartDocument(voli);
		
		try {
			XMLPalletWriter.printDocument(PalletDocument, String.format(Options.voloPalletFileName, idVolo));
			XMLVoloWriter.printDocument(VoliDocument, Options.voliFileName);
		} catch (IOException e) {
			return -1;
		} finally{
			voliLock.releaseWriteLock();
			lock.releaseWriteLock();
		}
		
		return 0;
	}

}
