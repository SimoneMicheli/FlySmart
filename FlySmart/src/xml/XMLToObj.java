package xml;

import model.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.*;

public class XMLToObj{
	
	public List<Passeggero> createPasseggeroList(String path){
		List<Passeggero> list = new ArrayList<Passeggero>();
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("passeggero");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, String> map= new HashMap<String, String>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					Field[] fields = Passeggero.class.getDeclaredFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						map.put(name, ((Node)firstName.item(0)).getNodeValue());
					}
					
					Passeggero toAdd = new Passeggero(Integer.parseInt(map.get("id")), Integer.parseInt(map.get("idGruppo")), map.get("nome"), map.get("cognome"), Integer.parseInt(map.get("eta")), (Character) map.get("sesso").charAt(0), Double.parseDouble(map.get("pesoBagagli")), Integer.parseInt(map.get("idVolo")), Integer.parseInt(map.get("posto")), Integer.parseInt(map.get("giorno")), Integer.parseInt(map.get("mese")), Integer.parseInt(map.get("anno")));
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
	
	public List<Aeroporto> createAeroportoList(String path){
		List<Aeroporto> list = new ArrayList<Aeroporto>();
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("aeroporto");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, String> map= new HashMap<String, String>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					Field[] fields = Aeroporto.class.getDeclaredFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						map.put(name, ((Node)firstName.item(0)).getNodeValue());
					}
					
					Aeroporto toAdd = new Aeroporto(Integer.parseInt(map.get("id")), map.get("nome"), Double.parseDouble(map.get("prezzoCarburante")), Double.parseDouble(map.get("tasse")));
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
		
	public List<Pallet> createPalletList(String path){
		List<Pallet> list = new ArrayList<Pallet>();
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("pallet");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Map<String, String> map= new HashMap<String, String>();
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{	
					Field[] fields = Pallet.class.getDeclaredFields();
					for(Field f:fields)
					{
						String name = f.getName();
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						Element firstNameElement = (Element)firstNameElementLst.item(0);
						NodeList firstName = firstNameElement.getChildNodes();
						map.put(name, ((Node)firstName.item(0)).getNodeValue());
					}
					
					Pallet toAdd = new Pallet( Integer.parseInt(map.get("id")), Double.parseDouble(map.get("peso")), map.get("targa"), Integer.parseInt(map.get("idVolo")));
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
					Field[] fields = Volo.class.getDeclaredFields();
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
