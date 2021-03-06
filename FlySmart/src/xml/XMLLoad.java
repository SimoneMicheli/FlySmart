package xml;

import java.io.*;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.*; //JAXP

/**
 * Carica e restituisce un Document XML da file
 * @author Demarinis - Micheli - Scarpellini
 */
public class XMLLoad {

	/**
	 * Carica il Document XML indicato nel path
	 *
	 * @param path Il percorso del file
	 * @return Il Document XML
	 * 
	 */
	
	protected Document loadDocument(String path) {
		// Si crea un'istanza di DocumentBuilderFactory usando il metodo statico newInstance()
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		// Si impostano le caratteristiche desiderate per il DOM
		f.setValidating(false); // Senza DTD
		f.setNamespaceAware(false); // Senza DTD
		DocumentBuilder d;
		
		try {
			// Si crea un DocumentBuilder tramite la DocumentBuilderFactory
			d=f.newDocumentBuilder();
			// Si imposta un error handler per il parser (opzionale)
			ErrorHandler eh = null;
			d.setErrorHandler(eh);
			// Si carica il documento usando il metodo parse di DocumentBuilder, che accetta vari tipo di input (file, stram, stringhe, ecc...)
			Document doc=d.parse(new File(path));
			return doc;
		} catch (ParserConfigurationException pce) {}
		// E' necessario gestiore due nuove eccezioni generabili dal processo di parsing
		catch (IOException ioe) {}
		catch (SAXException sxe) {}
		
		return null;
		
	}

}
