/**
 * 
 */
package model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author simone
 *
 */
public abstract class Model implements SortableList<Integer>, GetFields, Serializable{

	protected  Integer id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(Integer o) {
		if (id < o)
			return -1;
		if (id == 0)
			return 0;
		return 1;
	}
	
	@Override
	public List<Field> getFields() {
		Field[] currentFields = Model.class.getDeclaredFields();
		List<Field> fields = new LinkedList<Field>();
		for (Field f : currentFields)
			fields.add(f);
		return fields;
	}
}
