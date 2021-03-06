/**
 * 
 */
package prenotazione;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import db.DBSession;
import db.LockImpl;


import model.Pallet;
import model.StatoVolo;
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
	public ObjectId[] prenota(List<Pallet> listToAdd, ObjectId idVolo)
			throws FlightNotFoundException, SeatsSoldOutException {
		ObjectId[] ids = new ObjectId[listToAdd.size()];
		
		try {
			//accesso esclusivo al volo
			LockImpl.getInstance().acquireLock(idVolo);
			
			Volo v = DBSession.getVoloDAO().get(idVolo);
			
			if(v == null){
				log.warn("Volo richiesto non trovato id: "+idVolo);
				throw new FlightNotFoundException(idVolo);
			}
			
			if(v.getStato() != StatoVolo.OPEN){
				log.warn("Il volo richiesto è già chiuso id: "+idVolo);
				throw new BookingException("Flight already closed! id: "+idVolo);
			}
			
			if (v.getPalletDisponibili() - listToAdd.size() < 0){
				log.warn("Posti insufficienti sul volo id: "+idVolo);
				throw new SeatsSoldOutException(idVolo); //posti insufficienti
			}
			
			v.setPalletDisponibili(v.getPalletDisponibili() - listToAdd.size());
			
			int i = 0;
			for(Pallet p : listToAdd){
				p.setIdVolo(idVolo);
				v.getPallet().add(p);
				//save pallet
				DBSession.getPalletDAO().save(p);
				ids[i] = p.getId();
				i++;
			}
			
			DBSession.getVoloDAO().save(v);
			log.info("Prenotazione effettuata con successo per i pallet sul volo id: "+idVolo);
		} finally {
			//rilascio il lock
			LockImpl.getInstance().releaseLock(idVolo);
		}
	
		return ids;
	}
}
