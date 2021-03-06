package xml;

import java.io.*;
import java.lang.reflect.*;
import javax.xml.parsers.*;   //XML parsers
import model.GetFields;
import org.w3c.dom.*;         //Interfacce di DOM 
import org.w3c.dom.ls.*;      //Interfacce Load&Save di DOM
import java.util.*;


/**
 * Crea un Document XML a partire da una lista di oggetti
 *
 * @author Demarinis - Micheli - Scarpellini
 * @param <E> Tipo dell'oggetto della lista
 */
public class XMLCreateImpl<E extends GetFields> implements XMLCreate<E> {
	

	/* (non-Javadoc)
	 * @see xml.XMLCreate#createFlySmartDocument(java.util.List)
	 */
	@Override
	public Document createFlySmartDocument(List<E> list){
		Document d = this.createDocument();
		return this.createFlySmartDocument(d, list);
	}
	
	/* (non-Javadoc)
	 * @see xml.XMLCreate#createFlySmartDocument(org.w3c.dom.Document, java.util.List)
	 */
	@Override
	public Document createFlySmartDocument(Document d, List<E> list)	
		{

		Element root = null; // senza DTD
		Element childToAdd = null;
		// Il documento deve includere un elemento di livello superiore, 
		//cioè l' elemento principale: tutti gli altri elementi 
		//devono essere nidificati al loro interno. 
		// A questo elemento è stato assegnato il nome di "flySmart"
		Element upperRoot = d.createElement("flySmart");
		//Element radice = d.getDocumentElement();  //versione con DTD
		
		for (E elem: list)
			{
				try
				{
					
					List<Field> fieldArray = elem.getFields(); // Recupero la lista dei Campi della Classe
					root = d.createElement("object"); //creo la radice, ovvero l'elemento con la tag uguale al nome della Classe
					for(Field f : fieldArray) // Visualizzo i dati di ciascun campo
					{
						String attributeName = f.getName();
						String attributeType = f.getType().toString();
						//String [] split = attributeType.split("\\.");
						//attributeType = split[split.length-1];
						attributeType=attributeType.split("\\.")[attributeType.split("\\.").length-1];
						String attributeNameMaiusc = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
						Method m = elem.getClass().getMethod("get"+attributeNameMaiusc); // Ottengo il Metodo 
						String s;
						//se l'elemento è null scrivo stringa vuota
						try{
							s = m.invoke(elem).toString();// Invoco il metodo
						}catch (NullPointerException e) {
							s = "";
						}
						childToAdd = d.createElement(attributeName); // Costruisco l'albero degli elementi, che successivamente inserirò del documento
						childToAdd.setTextContent(s);
						childToAdd.setAttribute("tipo", attributeType);
						root.appendChild(childToAdd);
					}
				}
				catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				
				upperRoot.appendChild(root); // aggiungo ciascun elemento dell'arrayList al nodo "superiore"
			}

		d.appendChild(upperRoot); // infine faccio l'appendChild del nodo "superiore" che racchiude tutti gli altri elementi dell'arrayList
		return d;
		}


	/**
	 * Crea un Document XML generico
	 *
	 * @return Il Document XML
	 */
	protected Document createDocument() {
		//Ottengo un document builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);     //Non uso il DTD, quindi nessuna validazione
		dbf.setNamespaceAware(false); //Ignoro l'uso di namespace
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();  //Ottengo un documentbuilder dalla factory
			Document d = db.newDocument();    //Ottengo un documento dal document buider      
			return d;
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
			System.exit(10);
		}
		return null;
	}
	

	/**
	 * Crea un Document con DTD
	 *
	 * @param radice Il nodo radice
	 * @param pubid Il PubID
	 * @param sysid Il SysID
	 * @return Il Document con DTD
	 */
	protected Document createDocumentDTD(String radice, String pubid, String sysid) {
		//Ottengo un document builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);     //Non uso il DTD
		dbf.setNamespaceAware(false); //Ignoro l'uso di namespace
		try {
			DocumentBuilder db = dbf.newDocumentBuilder(); //ottengo un document builder
			DOMImplementation dbi = db.getDOMImplementation(); //Ottengo un'implementazione DOM
			//Creo un doctype che user� per la creazione del documento
			DocumentType doctype = dbi.createDocumentType(radice,pubid,sysid);
			//Creo il document
			Document d = dbi.createDocument("",radice,doctype);
			return d;
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
			System.exit(10);
		}
		return null;
	}


	/**
	 * Salva il Document
	 *
	 * @param d Il Document che si vuole salvare
	 * @param w Il writer che si vuole utilizzare
	 * @return true, Se il salvataggio è avvenuto correttamente
	 */
	protected boolean saveDocument(Document d, Writer w) {

		//Ottengo un load&save object
		DOMImplementationLS ls = (DOMImplementationLS)d.getImplementation();
		//ottengo un oggetto per fare output
		LSOutput lso = ls.createLSOutput();
		
		//Creo un serializzatore
		LSSerializer lss = ls.createLSSerializer();
		try {
			lso.setCharacterStream(w); //Imposto il writer (parametro)
			lso.setEncoding("ISO-8859-1"); //imposto l'encoding
			//Formatta l'output aggiungendo spazi per produrre una stampa 
			//"graziosa" (pretty-print) e indentata
			lss.getDomConfig().setParameter("format-pretty-print",true);
			//Uso il serializzatore per scrivere passando il documento e l'oggetto output
			//che contiene le informazioni per la scrittura (quale writer e quale codifica)
			lss.write(d,lso); 
			
			return true;
		} catch (LSException lse)   {
			return false;
		}     
	}
	

	/* (non-Javadoc)
	 * @see xml.XMLCreate#printDocument(org.w3c.dom.Document, java.lang.String)
	 */
	@Override
	public boolean printDocument(Document d, String name_file) throws IOException
	{
		try {
			this.saveDocument(d, new FileWriter(name_file));
			return true;
			} catch (IOException ex) {
				ex.printStackTrace();	{
					return false;
				}
			}
		
		//Avremmo potuto salvare con un altro writer
	}
	
}
