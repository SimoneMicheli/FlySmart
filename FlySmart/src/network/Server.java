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

import cancellazione.CancellaPallet;
import cancellazione.CancellaPasseggero;
import cancellazione.DeleteException;

import prenotazione.FlightNotFoundException;
import prenotazione.Prenotazione;
import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;
import prenotazione.SeatsSoldOutException;

import smart.SmartCheckin;
import util.Options;
import xml.XMLToObj;
import db.DBSession;
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
	
	Prenotazione<Passeggero> prenotaPass = null;
	Prenotazione<Pallet> prenotaPall = null;
	
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
		prenotaPass = new PrenotazionePasseggero();
		prenotaPall = new PrenotazionePallet();
		
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
	public void calcolaCheckin(ObjectId idVolo) throws FlightNotFoundException{
		log.entry();
		SmartCheckin checkin = null;
		
		checkin = new SmartCheckin(idVolo);
		
		checkin.calcolaCheckin();
		log.exit();
	}

	@Override
	public List<Passeggero> getPasseggeriGruppo(ObjectId idGruppo)
			throws RemoteException {
		log.entry();
		log.info("Ottengo lista passeggeri gruppo: "+idGruppo);
		CancellaPasseggero cp = new CancellaPasseggero();
		log.exit();
		return cp.getPasseggeriGruppo(idGruppo);
	}

	@Override
	public void cacnellaPallet(ObjectId idPallet) throws RemoteException,
			DeleteException {
		log.entry();
		CancellaPallet cp = new CancellaPallet();
		log.info("Cancello pallet: "+idPallet);
		cp.cancellaPallet(idPallet);
		log.exit();
	}

	@Override
	public void cancellaPasseggeri(List<Passeggero> list)
			throws RemoteException, DeleteException {
		log.entry();
		CancellaPasseggero cp = new CancellaPasseggero();
		
		for(Passeggero p : list){
			log.info("Cancello passeggero: "+p.toString());
			cp.cancellaPasseggero(p.getId());
		}
		log.exit();
	}

	@Override
	public Pallet getInfoPallet(ObjectId id) throws RemoteException {
		log.entry();
		CancellaPallet cp = new CancellaPallet();
		log.exit();
		return cp.getInfoPallet(id);
	}

	
}
