/**
 * 
 */
package cancellazione;

import org.bson.types.ObjectId;

import db.DBSession;
import db.LockImpl;

import model.Pallet;
import model.Volo;

/**
 * la classe implementa i metodi che permettono di cancellare un pallet
 *
 */
public class CancellaPallet implements Cancella{

	/**
	 * ottene le informazioni sul pallet richiesto
	 * @param id del pallet
	
	 * @return pallet */
	public Pallet getInfoPallet(ObjectId id){
		return DBSession.getPalletDAO().get(id);
	}
	
	/**
	 * cancella il pallet richiesto
	 * @param id del pallet da cancellare
	 */
	public void cancella(ObjectId id){
		Pallet p = DBSession.getPalletDAO().get(id);
		if(p != null){
			
			try {
				//accesso esclusivo al volo
				LockImpl.getInstance().acquireLock(p.getIdVolo());
				
				Volo v = DBSession.getVoloDAO().get(p.getIdVolo());
				v.setPalletDisponibili(v.getPalletDisponibili() +1);
				//remove form list
				v.getPallet().remove(p);
				DBSession.getPalletDAO().deleteById(id);
				DBSession.getVoloDAO().save(v);
			} finally{
				LockImpl.getInstance().releaseLock(p.getIdVolo());
			}
		} else {
			throw new DeleteException("Can't find pallet with id: "+id);
		}
		
	}
}
