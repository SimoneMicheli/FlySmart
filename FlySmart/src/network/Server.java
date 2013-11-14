package network;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import util.Options;
import xml.XMLToObj;
import comparator.*;
import db.DBSession;
import db.Lock;
import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;
import model.*;

/** Implementa i metodi definiti nell'interfaccia del server
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
	private static final long serialVersionUID = -1112973689097758070L;
	
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
		
		log.info("Creazione oggetto Server");
	}
	
	
	
	/* (non-Javadoc)
	 * @see network.ServerInterface#getAeroporti()
	 */
	@Override
	public List<Aeroporto> getAeroporti() throws RemoteException {
		
		log.entry();
		List<Aeroporto> aeroporti = new LinkedList<Aeroporto>();
		XMLToObj<Aeroporto> parserXML = new XMLToObj<Aeroporto>(Aeroporto.class);
	
		//parse xml data lock non richiesto perchè file usato in sola lettura
		aeroporti = parserXML.readObj(Options.aeroportiFileName);
		//ordina eroport in base al nome
		Collections.sort(aeroporti, AeroportoComparator.NAME_ORDER);
		
		//restituisce elnco aeroporti al client
		log.exit();
		return aeroporti;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#getVoli(int, int)
	 */
	@Override
	public List<Volo> getVoli(int idp, int ida) throws RemoteException {
		log.entry();
		List<Volo> voli = null;
		
		voli = DBSession.getVoloDAO().getByPartenzaDestinazione(idp, ida);
		
		log.exit();
		return voli;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPasseggero(java.util.List, int)
	 */
	@Override
	public int prenotaPasseggero(List<Passeggero> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		
		try {
			//accesso esclusivo al volo
			Lock.getInstance().acquireLock(idVolo);
			
			//recupero volo da DB
			Volo v = DBSession.getVoloDAO().get(idVolo);
			
			//volo non trovato
			if(v == null){
				throw new FlightNotFoundException(idVolo);
			}
			
			//posti insufficienti
			if (v.getPostiDisponibili() - listToAdd.size() < 0){
				throw new SeatsSoldOutException(idVolo); //posti insufficienti
			}
			
			v.setPostiDisponibili(v.getPostiDisponibili() - listToAdd.size());
			int idGruppo = v.getNextGroupID();
			
			//registro passeggeri su volo
			for(Passeggero p : listToAdd){
				//update pass and volo
				p.setIdVolo(idVolo);
				p.setIdGruppo(idGruppo);
				v.getPasseggeri().add(p);
				
				//salva passeggero
				DBSession.getPasseggeroDAO().save(p);
				
			}
			//salvo volo
			DBSession.getVoloDAO().save(v);
		} finally  {
			//rilascio il lock
			Lock.getInstance().releaseLock(idVolo);
		}
		
		
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPallet(java.util.List, int)
	 */
	@Override
	public int prenotaPallet(List<Pallet> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		log.entry();
		
		Volo v = DBSession.getVoloDAO().get(idVolo);
		
		if(v == null)
			throw new FlightNotFoundException(idVolo);
		
		if (v.getPalletDisponibili() - listToAdd.size() < 0){
			throw new SeatsSoldOutException(idVolo); //posti insufficienti
		}
		
		v.setPalletDisponibili(v.getPalletDisponibili() - listToAdd.size());
		
		for(Pallet p : listToAdd){
			p.setIdVolo(idVolo);
			DBSession.getPalletDAO().save(p);
		}
		
		DBSession.getVoloDAO().save(v);
	
		return 0;
	}

}
