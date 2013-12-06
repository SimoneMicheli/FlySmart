/**
 * 
 */
package prenotazione;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;

import db.DBSession;
import db.Lock;

/**
 * la classe gestisce la prenotazione di un passeggero
 *
 */
public class PrenotazionePasseggero implements Prenotazione<Passeggero> {
	
	
	private Logger log;
	
	public PrenotazionePasseggero() {
		log = LogManager.getLogger(PrenotazionePasseggero.class.getCanonicalName().toString());
	}

	@Override
	public ObjectId[] prenota(List<Passeggero> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException {
		ObjectId[] idGruppo = new ObjectId[1];
		
		try {
			//accesso esclusivo al volo
			Lock.getInstance().acquireLock(idVolo);
			
			//recupero volo da DB
			Volo v = DBSession.getVoloDAO().get(idVolo);
			
			//volo non trovato
			if(v == null){
				log.warn("Volo richiesto non trovato id: "+idVolo);
				throw new FlightNotFoundException(idVolo);
			}
			
			if(v.getStato() != StatoVolo.OPEN){
				log.warn("Il volo richiesto è già chiuso id: "+idVolo);
				throw new BookingException("Flight already closed! id: "+idVolo);
			}
			
			//posti insufficienti
			if (v.getPostiDisponibili() - listToAdd.size() < 0){
				log.warn("Posti insufficienti sul volo id: "+idVolo);
				throw new SeatsSoldOutException(idVolo); //posti insufficienti
			}
			
			v.setPostiDisponibili(v.getPostiDisponibili() - listToAdd.size());
			idGruppo[0] = v.getGroupNumber();
			
			//registro passeggeri su volo
			for(Passeggero p : listToAdd){
				//update pass and volo
				p.setIdVolo(idVolo);
				p.setIdGruppo(idGruppo[0]);
				v.getPasseggeri().add(p);
				
				//salva passeggero
				DBSession.getPasseggeroDAO().save(p);
				
			}
			//salvo volo
			DBSession.getVoloDAO().save(v);
			log.info("Prenotazione effettuata con successo per il gruppo: "+idGruppo[0]+" di: "+listToAdd.size()+" passeggeri sul volo id: "+idVolo);
		} finally  {
			//rilascio il lock
			Lock.getInstance().releaseLock(idVolo);
		}
		
		return idGruppo;
	}

}
