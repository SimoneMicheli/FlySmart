package xml;

import java.io.*;
import java.lang.reflect.*;

import javax.xml.parsers.*;   //XML parsers

import model.GetFields;

import org.w3c.dom.*;         //Interfacce di DOM 
import org.w3c.dom.ls.*;      //Interfacce Load&Save di DOM
import java.util.*;


public class XMLCreate<E extends GetFields> {
	

	public Document createFlySmartDocument(List<E> list){
		Document d = this.createDocument();
		return this.createFlySmartDocument(d, list);
	}
	
	public Document createFlySmartDocument(Document d, List<E> list)	
		{

		Element root = null; // senza DTD
		Element childToAdd = null;
		// Il documento deve includere un elemento di livello superiore, 
		//cioè l' elemento principale: tutti gli altri elementi 
		//devono essere nidificati al loro interno. 
		// A questo elemento è stato assegnato il nome di "FlySmart"
		Element upperRoot = d.createElement("flySmart");
		//Element radice = d.getDocumentElement();  //versione con DTD
		
		for (E elem: list)
			{
				try
				{
					
					List<Field> fieldArray = elem.getFields(); // Recupero la lista dei Campi della Classe
					@SuppressWarnings("rawtypes")
					Class myObjectClass = elem.getClass();   // Ottengo l'oggetto Class relativo all'elemento della lista
					String simpleClassName = myObjectClass.getSimpleName(); //ottengo il nome della classe e in minuscolo
					root = d.createElement(simpleClassName.substring(0, 1).toLowerCase() + simpleClassName.substring(1) ); //creo la radice, ovvero l'elemento con la tag uguale al nome della Classe
					for(Field f : fieldArray) // Visualizzo i dati di ciascun campo
					{
						String attributeName = f.getName();
						String attributeNameMaiusc = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
						Method m = elem.getClass().getMethod("get"+attributeNameMaiusc); // Ottengo il Metodo 
						String s = m.invoke(elem).toString();// Invoco il metodo
						childToAdd = d.createElement(attributeName); // Costruisco l'albero degli elementi, che successivamente inserirò del documento
						childToAdd.setTextContent(s);
						root.appendChild(childToAdd);
					}
				}
				catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				upperRoot.appendChild(root); // aggiungo ciascun elemento dell'arrayList al nodo "superiore"
			}

		d.appendChild(upperRoot); // infine faccio l'appendChild del nodo "superiore" che racchiude tutti gli altri elementi dell'arrayList
		return d;
		}


	public Document createDocument() {
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
	

	public Document createDocumentDTD(String radice, String pubid, String sysid) {
		//Ottengo un document builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);     //Non uso il DTD
		dbf.setNamespaceAware(false); //Ignoro l'uso di namespace
		try {
			DocumentBuilder db = dbf.newDocumentBuilder(); //ottengo un document builder
			DOMImplementation dbi = db.getDOMImplementation(); //Ottengo un'implementazione DOM
			//Creo un doctype che userò per la creazione del documento
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


	public boolean saveDocument(Document d, Writer w) {

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
