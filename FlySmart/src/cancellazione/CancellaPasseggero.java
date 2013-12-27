/**
 * 
 */
package cancellazione;

import java.util.List;

import org.bson.types.ObjectId;

import db.DBSession;
import db.LockImpl;

import model.Passeggero;
import model.Volo;

/**
 * la classe implementa i metodi che permettono di cancellare un passeggero
 *
 */
public class CancellaPasseggero implements Cancella{

	/**
	 * fornendo l'id del gruppo restituisce l'elenco dei passeggeri nel gruppo
	 * @param idGruppo
	 * @return elenco passeggeri */
	public List<Passeggero> getPasseggeriGruppo(ObjectId idGruppo){
		return DBSession.getPasseggeroDAO().getByGroupId(idGruppo).asList();
	}
	
	/**
	 * cancella la prenotazione per il passeggero con un certo id
	 * @param id ObjectId
	 */
	public void cancella(ObjectId id){
		Passeggero p = DBSession.getPasseggeroDAO().get(id);
		
		if(p != null){
			//accesso esclusivo al volo
			LockImpl.getInstance().acquireLock(p.getIdVolo());
			
			try {
				Volo v = DBSession.getVoloDAO().get(p.getIdVolo());
				v.setPostiDisponibili(v.getPostiDisponibili() +1);
				v.getPasseggeri().remove(p);
				DBSession.getPasseggeroDAO().deleteById(id);
				DBSession.getVoloDAO().save(v);
			} finally {
				LockImpl.getInstance().releaseLock(p.getIdVolo());
			}
		} else {
			throw new DeleteException("Can't find passenger with id: "+id);
		}
		
	}
}
