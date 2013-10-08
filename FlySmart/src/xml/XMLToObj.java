package xml;

import model.*;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.w3c.dom.*;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLToObj.
 */
public class XMLToObj{
	
	/**
	 * Creates the passeggero list.
	 *
	 * @param path the path
	 * @return the list
	 */
	public List<Passeggero> createPasseggeroList(String path){
		List<Passeggero> list = new ArrayList<Passeggero>();
		
		//check if file exist otherwise return empty list
		File file = new File(path);
		if (!file.exists())
			return list;
		
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("passeggero");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, Object> map= new HashMap<String, Object>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					List<Field> fields = (new Passeggero()).getFields();
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
					
					Passeggero toAdd = new Passeggero((Integer) map.get("id"), (Integer) map.get("idGruppo"), (String) map.get("nome"), (String) map.get("cognome"), (Integer) map.get("eta"), (Character) map.get("sesso"), (Double) map.get("pesoBagagli"), (Integer) map.get("idVolo"), (Integer) map.get("posto"), (Integer) map.get("giorno"), (Integer) map.get("mese"), (Integer) map.get("anno"));
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
	 * Creates the aeroporto list.
	 *
	 * @param path the path
	 * @return the list
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
					
					Aeroporto toAdd = new Aeroporto((Integer) map.get("id"), (String) map.get("nome"), (Double) map.get("prezzoCarburante"), (Double) map.get("tasse"));
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
	 * Creates the pallet list.
	 *
	 * @param path the path
	 * @return the list
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
					
					Pallet toAdd = new Pallet((Integer) map.get("id"), (Double) map.get("peso"), (String) map.get("targa"), (Integer) map.get("idVolo"));
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
	 * Creates the volo list.
	 *
	 * @param path the path
	 * @return the list
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
					
					DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ROOT);
					Date dt=new Date();
					try {
						dt=df.parse(map.get("dataOra"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Volo toAdd = new Volo( Integer.parseInt(map.get("id")), dt, Integer.parseInt(map.get("aeroportoPartenza")) , Integer.parseInt(map.get("aeroportoDestinazione")), Integer.parseInt(map.get("aereo")), Integer.parseInt(map.get("postiDisponibili")), Integer.parseInt(map.get("palletDisponibili")), Double.parseDouble(map.get("prezzo")));
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
