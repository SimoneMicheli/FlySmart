/**
 * 
 */
package model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * superclasse astratta di modelli, ogni modello deve avere un id,
 * permette di recuperare i campi dichiarati nel modello
 * e permettedi eseguire una ricerca per id sui modelli.
 * Implementa l'interfaccia serializable per usare i modelli con RMI
 *
 */
public abstract class Model implements SortableList<Integer>, GetFields, Serializable{

	protected  Integer id;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * compara il modello con l'id fornito
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
	 * ritorna l'elenco dei campi (anche privati) dichiarati nell'oggetto
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
