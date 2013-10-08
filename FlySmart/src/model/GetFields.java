/**
 * 
 */
package model;

import java.lang.reflect.Field;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * @author Demarinis - Micheli - Scarpellini
 * l'interfaccia permette di ottenere tutti i campi della classe
 * e delle sue superclassi.
 */
public interface GetFields {
	
	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public List<Field> getFields();
}
