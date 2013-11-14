package xml;

import model.*;
import java.io.File;
import java.util.*;

import org.w3c.dom.*;

/**
 * Carica il Document XML e crea una lista di oggetti contenuti nel file xml
 * @author Demarinis - Micheli - Scarpellini
 */
public class XMLToObj<T extends GetFields>{
	
	private Class<T> cls;
	
	public XMLToObj(Class<T> cls){
		this.cls = cls;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> readObj(String path){
		List<T> lista = new ArrayList<T>();
		//controlla se il file esiste, altrimenti ritorna lista vuota
		File file = new File(path);
		if (!file.exists())
			return lista;
		
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path); // carica il Document specificato nel path
			NodeList nodeList = d.getElementsByTagName("object"); //crea una lista di nodi a partire dagli elementi con il tag specificato
			
			for (int i = 0; i < nodeList.getLength(); i++){ //per ciascun oggetto nella lista
				
				Map<String, Object> map= new HashMap<String, Object>(); // crea un Hashmap con <nome del campo da inserire nel costruttore; oggetto contenuto nell'xml>
				Node object = nodeList.item(i); // prende un nodo della lista di nodi caricati dall'xml	
				
				NodeList atributes = object.getChildNodes(); //attributi oggetto
				
				for (int c = 0; c< atributes.getLength(); c++){ //leggo attributi oggetto

					Node attributo = atributes.item(c);

					if(attributo.getNodeType() == Node.ELEMENT_NODE){

						String nome = attributo.getNodeName();	//ottengo nome attributo
						Node cf = attributo.getFirstChild();
						String tipoAttributo = attributo.getAttributes().getNamedItem("tipo").toString();
						String [] split = tipoAttributo.split("\"");
						tipoAttributo = split[split.length-1];
						Object value = null;
						switch(tipoAttributo) {
						case "String":
							value = (cf!=null) ? cf.getNodeValue() : null;
							break;
						case "Integer":
							value = (cf!=null) ? Integer.parseInt(cf.getNodeValue()) : null;
							break;
						case "TipoAereo":
							value = (cf!=null) ? TipoAereo.valueOf(cf.getNodeValue()) : null;
							break;
						case "Sesso":
							value = (cf!=null) ? Sesso.valueOf(cf.getNodeValue()) : null;
							break;
						case "StatoVolo":
							value = (cf!=null) ? StatoVolo.valueOf(cf.getNodeValue()) : null;
							break;
						case "Date":
							value = (cf!=null) ? cf.getNodeValue() : null;
							break;
						case "Double":
							value = (cf!=null) ? Double.parseDouble(cf.getNodeValue()) : null;
							break;
						case "long":
							value = (cf!=null) ? Long.parseLong(cf.getNodeValue()) : null;
							break;
				}
						map.put(nome, value);
					}
				}
				
				//determino oggetto
				switch (cls.getCanonicalName()) {
				

				case "model.Aeroporto":
					Aeroporto a = new Aeroporto((Integer) (map.get("id")),(String) map.get("nome"));
					lista.add((T) a);
					break;
					
				default:
					System.err.println("invalid class");
					break;
				}
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
}
