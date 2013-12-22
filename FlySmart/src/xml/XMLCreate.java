package xml;

import java.io.IOException;
import java.util.List;

import model.GetFields;

import org.w3c.dom.Document;

public interface XMLCreate<E extends GetFields> {

	/**
	 * Crea un nuovo Document XML
	 *
	 * @param list Lista degli oggetti 
	 * @return Il Document XML
	 */
	public abstract Document createFlySmartDocument(List<E> list);

	/**
	 * Crea un Document XML a partire da un Document già esistente
	 *
	 * @param d Il Document
	 * @param list Lista degli oggetti
	 * @return Il Document XML
	 */
	public abstract Document createFlySmartDocument(Document d, List<E> list);

	/**
	 * Stampa il documento su file
	 *
	 * @param d Il Document che si vuole stampare su file
	 * @param name_file Nome del file in output
	 * @return true, Se scrittura avvenuta con successo
	 * @throws IOException Indica che è avvuta un accezione durante l'operazione di I/O
	 */
	public abstract boolean printDocument(Document d, String name_file)
			throws IOException;

}