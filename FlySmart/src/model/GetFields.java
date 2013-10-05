/**
 * 
 */
package model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * l'interfaccia permette di ottenere tutti i campi della classe
 * e delle sue superclassi
 *
 */
public interface GetFields {
	public List<Field> getFields();
}
