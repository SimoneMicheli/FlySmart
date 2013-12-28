
package model;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**Superclasse astratta di modelli, ogni modello deve avere un id,
 * permette di recuperare i campi dichiarati nel modello
 * e permettedi eseguire una ricerca per id sui modelli.
 * Implementa l'interfaccia serializable per usare i modelli con RMI
 * @author Demarinis - Micheli - Scarpellini
 *
 */

@SuppressWarnings("serial")
@Entity
public abstract class ModelDB implements Model{

	/** Id dell'oggetto della classe */
	@Id
	protected  ObjectId id;

	/**
	 * Ottiene l'id dell'oggetto
	 *
	
	 * @return Id dell'oggetto */
	public ObjectId getId() {
		return id;
	}

	/**
	 * Set Id dell'oggetto
	 *
	 * @param id Id dell'oggetto
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

}
