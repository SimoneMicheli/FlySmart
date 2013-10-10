package model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Demarinis - Micheli - Scarpellini
 * l'interfaccia permette di ottenere tutti i campi della classe
 * e delle sue superclassi.
 */
public interface GetFields {
	
	/**
	 * Ottieni i campi della classe
	 *
	 * @return I campi della classe
	 */
	public List<Field> getFields();
}
