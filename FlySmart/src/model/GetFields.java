package model;

import java.lang.reflect.Field;
import java.util.List;

/**l'interfaccia permette di ottenere tutti i campi della classe e delle sue superclassi.
 * 
 * @author Demarinis - Micheli - Scarpellini
 */
public interface GetFields {
	
	/**
	 * Ottieni i campi della classe
	 *
	
	 * @return I campi della classe */
	public List<Field> getFields();
}
