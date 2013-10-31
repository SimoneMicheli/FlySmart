
package model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**Superclasse astratta di modelli, ogni modello deve avere un id,
 * permette di recuperare i campi dichiarati nel modello
 * e permettedi eseguire una ricerca per id sui modelli.
 * Implementa l'interfaccia serializable per usare i modelli con RMI
 * @author Demarinis - Micheli - Scarpellini
 *
 */
@SuppressWarnings("serial")
public abstract class Model implements Comparable<Integer>, GetFields, Serializable{

	/** Id dell'oggetto della classe */
	protected  Integer id;

	/**
	 * Ottiene l'id dell'oggetto
	 *
	 * @return Id dell'oggetto
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set Id dell'oggetto
	 *
	 * @param id Id dell'oggetto
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * compara il modello con l'id fornito.
	 *
	 * @param o Integer con cui vuole essere fatto il confronto
	 * @return Il risultato del confronto tra Integer
	 */
	@Override
	public int compareTo(Integer o) {
		/*if (id o)
			return -1;
		if (id == 0)
			return 0;
		return 1;*/
		return id.compareTo(o);
	}
	
	/**
	 * ritorna l'elenco dei campi (anche privati) dichiarati nell'oggetto.
	 *
	 * @return I campi della classe
	 */
	@Override
	public List<Field> getFields() {
		Field[] currentFields = Model.class.getDeclaredFields();
		List<Field> fields = new LinkedList<Field>();
		for (Field f : currentFields)
			fields.add(f);
		return fields;
	}
}
