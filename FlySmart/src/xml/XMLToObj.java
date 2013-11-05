package xml;

import model.*;
import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.*;

/**
 * Carica il Document XML e crea una lista di oggetti contenuti nel file xml
 * @author Demarinis - Micheli - Scarpellini
 */
public class XMLToObj{
	
	/**
	 * Crea la lista di passeggeri
	 *
	 * @param path Il percorso del file xml
	 * @return La lista di oggetti nel file xml
	 */
	public List<Passeggero> createPasseggeroList(String path){
		List<Passeggero> list = new ArrayList<Passeggero>();
		
		//controlla se il file esiste, altrimenti ritorna una lista vuota
		File file = new File(path);
		if (!file.exists())
			return list;
		
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path); // carica il Document specificato nel path
			NodeList nodeList = d.getElementsByTagName("passeggero"); //crea una lista di nodi a partire dagli elementi con il tag specificato

			for (int i = 0; i < nodeList.getLength(); i++) //per ciascun nodo con il tag specificato 
			{
				Map<String, Object> map= new HashMap<String, Object>(); // crea un Hashmap con <nome del campo da inserire nel costruttore; oggetto contenuto nell'xml>
				Node firstNode = nodeList.item(i); // prende un nodo della lista di nodi caricati dall'xml

				if (firstNode.getNodeType() == Node.ELEMENT_NODE) // il nodo deve essere di tipo Element
				{	
					List<Field> fields = (new Passeggero()).getFields(); // ottengo i campi della classe passeggero
					for(Field f:fields) //per ciascun campo della classe passeggero
					{
						String name = f.getName(); //prendo il nome del campo
						Element firstElement = (Element)firstNode; //prendo il primo nodo
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name); // prendo la lista degli elementi figli 
						Element firstNameElement = (Element)firstNameElementLst.item(0); //prendo il primo (e unico) elemento figlio
						NodeList firstName = firstNameElement.getChildNodes(); //ottengo i nodi del primo elemento figlio, che contengono l'informazione utile
						
						try{
							String value = ((Node)firstName.item(0)).getNodeValue(); //ottengo la stringa contenuta nel tag 
							String type = f.getType().getName(); //vedo di che tipo � il corrispondente campo nella classe passeggero
							Object v = null;
							
							if(type.compareTo("java.lang.Integer") == 0){
								v = Integer.valueOf(value); // se il campo della classe passeggero � un Integer lo converto ad Integer
							}else if (type.compareTo("java.lang.Character") == 0){
								v = value.charAt(0);
							}else if (type.compareTo("java.lang.Double") == 0){
								v= Double.valueOf(value);
							}else{
								v = value;
							}
							
							map.put(name, v); // inserisco nella hasmap creata in precedenza
						}catch(NullPointerException e){
							map.put(name, null);
						}
					}
					//utilizzo il costruttore della classe passeggero
					Passeggero toAdd = new Passeggero((Integer) map.get("id"), (Integer) map.get("idGruppo"), (String) map.get("nome"), (String) map.get("cognome"), (Integer) map.get("eta"), Sesso.valueOf((String) map.get("sesso")), (Double) map.get("pesoBagagli"), (Integer) map.get("idVolo"), (Integer) map.get("fila"), (Integer) map.get("colonna"), (Integer) map.get("giorno"), (Integer) map.get("mese"), (Integer) map.get("anno"));
					list.add(toAdd);
					//aggiungo il passeggero alla lista da ritornare 

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return list; //ritorno la lista
	}
	
	/**
	 * Crea la lista di aeroporti
	 *
	 * @param path Il percorso del file xml
	 * @return La lista di oggetti nel file xml
	 */
	public List<Aeroporto> createAeroportoList(String path){
		List<Aeroporto> list = new ArrayList<Aeroporto>();
		
		//check if file exist otherwise return empty list
		File file = new File(path);
		if (!file.exists())
			return list;
		
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("aeroporto");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, Object> map= new HashMap<String, Object>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					List<Field> fields = (new Aeroporto()).getFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						try{
							String value = ((Node)firstName.item(0)).getNodeValue();
							String type = f.getType().getName();
							Object v = null;
							
							if(type.compareTo("java.lang.Integer") == 0){
								v = Integer.valueOf(value);
							}else if (type.compareTo("java.lang.Character") == 0){
								v = value.charAt(0);
							}else if (type.compareTo("java.lang.Double") == 0){
								v= Double.valueOf(value);
							}else{
								v = value;
							}
							
							map.put(name, v);
						}catch(NullPointerException e){
							map.put(name, null);
						}
					}
					
					Aeroporto toAdd = new Aeroporto((Integer) map.get("id"), (String) map.get("nome"));
					list.add(toAdd);

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	/**
	 * Crea la lista di pallet
	 *
	 * @param path Il percorso del file xml
	 * @return La lista di oggetti nel file xml
	 */
	public List<Pallet> createPalletList(String path){
		List<Pallet> list = new ArrayList<Pallet>();
		
		//check if file exist otherwise return empty list
		File file = new File(path);
		if (!file.exists())
			return list;
		
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("pallet");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, Object> map= new HashMap<String, Object>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					List<Field> fields = (new Pallet()).getFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						try{
							String value = ((Node)firstName.item(0)).getNodeValue();
							String type = f.getType().getName();
							Object v = null;
							
							if(type.compareTo("java.lang.Integer") == 0){
								v = Integer.valueOf(value);
							}else if (type.compareTo("java.lang.Character") == 0){
								v = value.charAt(0);
							}else if (type.compareTo("java.lang.Double") == 0){
								v= Double.valueOf(value);
							}else{
								v = value;
							}
							
							map.put(name, v);
						}catch(NullPointerException e){
							map.put(name, null);
						}
					}
					
					Pallet toAdd = new Pallet((Integer) map.get("id"), (Double) map.get("peso"), (String) map.get("targa"), (Integer) map.get("idVolo"), (Integer) map.get("fila"), (Integer) map.get("colonna") );
					list.add(toAdd);

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * Crea la lista di voli
	 *
	 * @param path Il percorso del file xml
	 * @return La lista di oggetti nel file xml
	 */
	public List<Volo> createVoloList(String path){
		List<Volo> list = new ArrayList<Volo>();
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("volo");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, String> map= new HashMap<String, String>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					List<Field> fields = (new Volo()).getFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						map.put(name, ((Node)firstName.item(0)).getNodeValue());
					}
					
					//sistemo il formato delle date
					DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ROOT);
					Date dt=new Date();
					try {
						//parsing della data
						dt=df.parse(map.get("dataOra"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Volo toAdd = new Volo( Integer.parseInt(map.get("id")), dt, Integer.parseInt(map.get("aeroportoPartenza")) , Integer.parseInt(map.get("aeroportoDestinazione")), Integer.parseInt(map.get("aereo")), Integer.parseInt(map.get("postiDisponibili")), Integer.parseInt(map.get("palletDisponibili")), Double.parseDouble(map.get("prezzo")), StatoVolo.valueOf(map.get("stato")), TipoAereo.valueOf(map.get("tipoAereo")), Integer.parseInt(map.get("lastID")), Integer.parseInt(map.get("lastPalletID")), Integer.parseInt(map.get("lastGroupID")));
					list.add(toAdd);

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
