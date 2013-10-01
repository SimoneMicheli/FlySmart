package xml;

import model.*;
import java.lang.reflect.Field;
import java.util.*;
import org.w3c.dom.*;

public class XMLToObj{
	
	public ArrayList<Passeggero> createPasseggeroList(String path){
		ArrayList<Passeggero> list = new ArrayList<Passeggero>();
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
					
					Passeggero toAdd = new Passeggero(Integer.parseInt(map.get("id")), Integer.parseInt(map.get("idGruppo")), map.get("nome"), map.get("cognome"), Integer.parseInt(map.get("eta")), map.get("sesso").charAt(0), Double.parseDouble(map.get("pesoBadagli")), Integer.parseInt(map.get("idVolo")), Integer.parseInt(map.get("posto")), Integer.parseInt(map.get("giorno")), Integer.parseInt(map.get("mese")), Integer.parseInt(map.get("anno")));
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
	
	public ArrayList<Aeroporto> createAeroportoList(String path){
		ArrayList<Aeroporto> list = new ArrayList<Aeroporto>();
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
		
	public ArrayList<Pallet> createPalletList(String path){
		ArrayList<Pallet> list = new ArrayList<Pallet>();
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
	

	public ArrayList<Volo> createVoloList(String path){
		ArrayList<Volo> list = new ArrayList<Volo>();
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
					
					//Volo toAdd = new Volo( Integer.parseInt(map.get("id")), Date.parse(map.get("peso")), map.get("targa"), Integer.parseInt(map.get("idVolo")));
					//list.add(toAdd);

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
