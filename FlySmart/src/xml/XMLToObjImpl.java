package xml;

import model.*;
import java.io.File;
import java.util.*;

import org.w3c.dom.*;

/**
 * Carica il Document XML e crea una lista di oggetti contenuti nel file xml
 * @author Demarinis - Micheli - Scarpellini
 */
public class XMLToObjImpl<T extends GetFields> implements XMLToObj<T>{
	
	private Class<T> cls;
	
	public XMLToObjImpl(Class<T> cls){
		this.cls = cls;
	}
	
	/* (non-Javadoc)
	 * @see xml.XMLToObj#readObj(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> readObj(String path){
		List<T> lista = new ArrayList<T>();
		//controlla se il file esiste, altrimenti ritorna lista vuota
		File file = new File(path);
		if (!file.exists()) return lista;
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path); // carica il Document specificato nel path
			NodeList nodeList = d.getElementsByTagName("object"); //crea una lista di nodi a partire dagli elementi con il tag specificato
			
			for (int i = 0; i < nodeList.getLength(); i++){ //per ciascun oggetto nella lista
				
				Map<String, Object> map= new HashMap<String, Object>(); // crea un Hashmap con <nome del campo da inserire nel costruttore; oggetto contenuto nell'xml>
				Node object = nodeList.item(i); // prende un nodo della lista di nodi caricati dall'xml	
				NodeList atributes = object.getChildNodes(); //attributi oggetto
				for (int c = 0; c< atributes.getLength(); c++){ //leggo attributi oggetto

					//Node attributo = atributes.item(c);
					updateMap(atributes.item(c),map);
/*
					if(attributo.getNodeType() == Node.ELEMENT_NODE){

						String nome = attributo.getNodeName();	//ottengo nome attributo
						Node cf = attributo.getFirstChild();
						String tipoAttributo = attributo.getAttributes().getNamedItem("tipo").toString();
						String [] split = tipoAttributo.split("\"");
						tipoAttributo = split[split.length-1];
						Object value = returnType(tipoAttributo, cf);
						map.put(nome, value);
					}
					*/
				}
				
				//determino oggetto
				if(cls.getCanonicalName().equalsIgnoreCase("model.Aeroporto")) {
					Aeroporto a = new Aeroporto((Integer) (map.get("id")),(String) map.get("nome"));
					lista.add((T) a);
					}		
					else{
					System.err.println("invalid class");
					}
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	private Object returnType(String tipoAttributo,Node cf){
		if(cf!=null)
		switch(tipoAttributo) {
		case "String":
			return cf.getNodeValue();
		case "Integer":
			return Integer.parseInt(cf.getNodeValue());
		}
		return null;
	}
	
	private void updateMap(Node attributo, Map<String, Object> map){
		if(attributo.getNodeType() == Node.ELEMENT_NODE){

			String nome = attributo.getNodeName();	//ottengo nome attributo
			Node cf = attributo.getFirstChild();
			String tipoAttributo = attributo.getAttributes().getNamedItem("tipo").toString();
			String [] split = tipoAttributo.split("\"");
			tipoAttributo = split[split.length-1];
			Object value = returnType(tipoAttributo, cf);
			map.put(nome, value);
		}
	}
}
