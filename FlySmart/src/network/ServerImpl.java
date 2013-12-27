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

import checkin.CheckinReport;
import checkin.SmartCheckin;

import comparator.AeroportoComparator;

import cancellazione.CancellaPallet;
import cancellazione.CancellaPasseggero;
import cancellazione.DeleteException;

import prenotazione.FlightNotFoundException;
import prenotazione.Prenotazione;
import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;
import prenotazione.SeatsSoldOutException;

import util.Options;
import xml.XMLToObj;
import xml.XMLToObjImpl;
import db.DBSession;
import model.*;

/** Implementa i metodi definiti nell'interfaccia del server
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public class ServerImpl extends UnicastRemoteObject implements Server {
	private static final long serialVersionUID = -1112973689097758070L;
	
	/** logger*/
	Logger log;
	
	Prenotazione<Passeggero> prenotaPass = null;
	Prenotazione<Pallet> prenotaPall = null;
	CancellaPallet cancellaPallet = null;
	CancellaPasseggero cancellaPasseggeri = null;
	
	
	/**
	 * costruttore dell'oggetto server, crea i lock necessari a garantire l'accesso
	 * ai file xml
	 * @param clientFactory
	 * @param serverFactory
	 * @throws RemoteException
	 * @throws FileNotFoundException 
	 */
	protected ServerImpl(RMISSLClientSocketFactory clientFactory, RMISSLServerSocketFactory serverFactory) throws RemoteException {
		super(0, clientFactory, serverFactory);
		
		log = LogManager.getLogger(ServerImpl.class.getCanonicalName().toString());
		prenotaPass = new PrenotazionePasseggero();
		prenotaPall = new PrenotazionePallet();
		cancellaPasseggeri = new CancellaPasseggero();
		cancellaPallet = new CancellaPallet();
		
		log.info("Creazione oggetto Server");
	}
	
	/* (non-Javadoc)
	 * @see network.ServerInterface#getAeroporti()
	 */
	@Override
	public List<Aeroporto> getAeroporti() throws RemoteException {
		
		log.entry();
		List<Aeroporto> aeroporti = new LinkedList<Aeroporto>();
		XMLToObj<Aeroporto> parserXML = new XMLToObjImpl<Aeroporto>(Aeroporto.class);
	
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
		//TODO se ida = -1 allora ottenere tutti i voli da idp a qualsiasi destinazione (che siano pero open)
		log.entry();
		List<Volo> voli = null;
		
		if(ida == -1)
			voli = DBSession.getVoloDAO().getByPartenza(idp);
		else
			voli = DBSession.getVoloDAO().getByPartenzaDestinazione(idp, ida);
		
		log.exit();
		return voli;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPasseggero(java.util.List, int)
	 */
	@Override
	public ObjectId[] prenotaPasseggero(List<Passeggero> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		log.entry();
		ObjectId[] id = prenotaPass.prenota(listToAdd, idVolo);
		log.exit(id);

		return id;
	}

	/* (non-Javadoc)
	 * @see network.ServerInterface#prenotaPallet(java.util.List, int)
	 */
	@Override
	public ObjectId[] prenotaPallet(List<Pallet> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		log.entry();
		
		ObjectId[] id = prenotaPall.prenota(listToAdd, idVolo);
				
		log.exit(id);
		
		return id;
	}
	
	@Override
	public CheckinReport calcolaCheckin(ObjectId idVolo) throws FlightNotFoundException{
		log.entry();
		SmartCheckin checkin = null;
		
		checkin = new SmartCheckin(idVolo);
		
		CheckinReport rx = checkin.calcolaCheckin();
		log.exit();
		return rx;
	}

	@Override
	public List<Passeggero> getPasseggeriGruppo(ObjectId idGruppo)
			throws RemoteException {
		log.entry();
		log.info("Ottengo lista passeggeri gruppo: "+idGruppo);
		log.exit();
		return cancellaPasseggeri.getPasseggeriGruppo(idGruppo);
	}

	@Override
	public boolean cancellaPallet(ObjectId idPallet) throws RemoteException,
			DeleteException {
		log.entry();
		log.info("Cancello pallet: "+idPallet);
		cancellaPallet.cancella(idPallet);
		log.exit();
		return true;
	}

	@Override
	public boolean cancellaPasseggeri(List<Passeggero> list) throws RemoteException, DeleteException {
		log.entry();
		for(Passeggero p : list){
			log.info("Cancello passeggero: "+p.toString());
			cancellaPasseggeri.cancella(p.getId());
		}
		log.exit();
		return true;
	}

	@Override
	public Pallet getInfoPallet(ObjectId id) throws RemoteException {
		log.entry();
		log.exit();
		return cancellaPallet.getInfoPallet(id);
	}

	
}
