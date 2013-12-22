package cancellazione;

import org.bson.types.ObjectId;

/**
 * difnisce imetodi a disposizione per cancellare passeggeri/pallet 
 *
 */
public interface Cancella {

	/**
	 * cancella passeggero/pallet 
	 * @param id id passeggero/pallet da cacnellare
	 */
	public abstract void cancella(ObjectId id);
}
