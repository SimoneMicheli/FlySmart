/**
 * 
 */
package prenotazione;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import db.DBSession;
import db.Lock;

import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;

import model.Pallet;
import model.Volo;

/**
 * la classe gestisce la prenotazione di un pallet
 *
 */
public class PrenotazionePallet implements Prenotazione<Pallet> {

	private Logger log;
	
	public PrenotazionePallet() {
		log = LogManager.getLogger(PrenotazionePallet.class.getCanonicalName().toString());
	}

	@Override
	public int prenota(List<Pallet> listToAdd, ObjectId idVolo)
			throws FlightNotFoundException, SeatsSoldOutException {
		
		try {
			//accesso esclusivo al volo
			Lock.getInstance().acquireLock(idVolo);
			
			Volo v = DBSession.getVoloDAO().get(idVolo);
			
			if(v == null){
				log.warn("Volo richiesto non trovato id: "+idVolo);
				throw new FlightNotFoundException(idVolo);
			}
			
			if (v.getPalletDisponibili() - listToAdd.size() < 0){
				log.warn("Posti insufficienti sul volo id: "+idVolo);
				throw new SeatsSoldOutException(idVolo); //posti insufficienti
			}
			
			v.setPalletDisponibili(v.getPalletDisponibili() - listToAdd.size());
			
			for(Pallet p : listToAdd){
				p.setIdVolo(idVolo);
				v.getPallet().add(p);
				DBSession.getPalletDAO().save(p);
			}
			
			DBSession.getVoloDAO().save(v);
			log.info("Prenotazione effettuata con successo per i pallet sul volo id: "+idVolo);
		} finally {
			//rilascio il lock
			Lock.getInstance().releaseLock(idVolo);
		}
	
		return 0;
	}
}
