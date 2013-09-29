package xml;

import model.*;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.*;
import org.w3c.dom.*;


public class XMLToObj{
	
	public ArrayList<Passeggero> createPasseggeroList(String path){
		ArrayList<Passeggero> list = new ArrayList<Passeggero>();
		try {
			XMLLoad instance = new XMLLoad();
			Document d = instance.loadDocument(path);
			NodeList nodeList = d.getElementsByTagName("Passeggero");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{
					String cognome, eta, id , idVolo, nome, pesoBagagli, posto, sesso;
					
					
					
					Element firstElement = (Element)firstNode;
					NodeList firstNameElementLst = firstElement.getElementsByTagName("Cognome");
					Element firstNameElement = (Element)firstNameElementLst.item(0);
					NodeList firstName = firstNameElement.getChildNodes();
					cognome = ((Node)firstName.item(0)).getNodeValue();
					
					NodeList firstNameElementLst2 = firstElement.getElementsByTagName("Eta");
					Element firstNameElement2 = (Element)firstNameElementLst2.item(0);
					NodeList firstName2 = firstNameElement2.getChildNodes();
					eta = ((Node)firstName2.item(0)).getNodeValue();
					
					NodeList firstNameElementLst3 = firstElement.getElementsByTagName("Id");
					Element firstNameElement3 = (Element)firstNameElementLst3.item(0);
					NodeList firstName3 = firstNameElement3.getChildNodes();
					id = ((Node)firstName3.item(0)).getNodeValue();
					
					NodeList firstNameElementLst4 = firstElement.getElementsByTagName("IdVolo");
					Element firstNameElement4 = (Element)firstNameElementLst4.item(0);
					NodeList firstName4 = firstNameElement4.getChildNodes();
					idVolo = ((Node)firstName4.item(0)).getNodeValue();
					
					NodeList firstNameElementLst5 = firstElement.getElementsByTagName("Nome");
					Element firstNameElement5 = (Element)firstNameElementLst5.item(0);
					NodeList firstName5 = firstNameElement5.getChildNodes();
					nome = ((Node)firstName5.item(0)).getNodeValue();
					
					NodeList firstNameElementLst6 = firstElement.getElementsByTagName("PesoBagagli");
					Element firstNameElement6 = (Element)firstNameElementLst6.item(0);
					NodeList firstName6 = firstNameElement6.getChildNodes();
					pesoBagagli = ((Node)firstName6.item(0)).getNodeValue();
					
					NodeList firstNameElementLst7 = firstElement.getElementsByTagName("Posto");
					Element firstNameElement7 = (Element)firstNameElementLst7.item(0);
					NodeList firstName7 = firstNameElement7.getChildNodes();
					posto = ((Node)firstName7.item(0)).getNodeValue();
					
					NodeList firstNameElementLst8 = firstElement.getElementsByTagName("Sesso");
					Element firstNameElement8 = (Element)firstNameElementLst8.item(0);
					NodeList firstName8 = firstNameElement8.getChildNodes();
					sesso = ((Node)firstName8.item(0)).getNodeValue();
					
					Passeggero toAdd = new Passeggero(Integer.parseInt(id), nome, cognome, Integer.parseInt(eta), sesso.charAt(0), Double.parseDouble(pesoBagagli), Integer.parseInt(idVolo), Integer.parseInt(posto));
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
			NodeList nodeList = d.getElementsByTagName("Aeroporto");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{
					String citta, id, prezzoCarburante, tasse;
					
					/*Field[] fields = Aeroporto.class.getDeclaredFields();
					for(Field f:fields){
						String name = f.getName();
						//f.getType()
						Element firstElement = (Element)firstNode;
						NodeList firstNameElementLst = firstElement.getElementsByTagName(name);
						new 
					}*/
					
					Element firstElement = (Element)firstNode;
					NodeList firstNameElementLst = firstElement.getElementsByTagName("Nome");
					Element firstNameElement = (Element)firstNameElementLst.item(0);
					NodeList firstName = firstNameElement.getChildNodes();
					citta = ((Node)firstName.item(0)).getNodeValue();
					
					NodeList firstNameElementLst2 = firstElement.getElementsByTagName("Id");
					Element firstNameElement2 = (Element)firstNameElementLst2.item(0);
					NodeList firstName2 = firstNameElement2.getChildNodes();
					id = ((Node)firstName2.item(0)).getNodeValue();
					
					NodeList firstNameElementLst3 = firstElement.getElementsByTagName("PrezzoCarburante");
					Element firstNameElement3 = (Element)firstNameElementLst3.item(0);
					NodeList firstName3 = firstNameElement3.getChildNodes();
					prezzoCarburante = ((Node)firstName3.item(0)).getNodeValue();
					
					NodeList firstNameElementLst4 = firstElement.getElementsByTagName("Tasse");
					Element firstNameElement4 = (Element)firstNameElementLst4.item(0);
					NodeList firstName4 = firstNameElement4.getChildNodes();
					tasse = ((Node)firstName4.item(0)).getNodeValue();
					
					Aeroporto toAdd = new Aeroporto(Integer.parseInt(id), citta, Double.parseDouble(prezzoCarburante), Double.parseDouble(tasse));
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
			NodeList nodeList = d.getElementsByTagName("Pallet");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{
					String id, idVolo, peso, targa;
					
					Element firstElement = (Element)firstNode;
					NodeList firstNameElementLst = firstElement.getElementsByTagName("Id");
					Element firstNameElement = (Element)firstNameElementLst.item(0);
					NodeList firstName = firstNameElement.getChildNodes();
					id = ((Node)firstName.item(0)).getNodeValue();
					
					NodeList firstNameElementLst2 = firstElement.getElementsByTagName("IdVolo");
					Element firstNameElement2 = (Element)firstNameElementLst2.item(0);
					NodeList firstName2 = firstNameElement2.getChildNodes();
					idVolo = ((Node)firstName2.item(0)).getNodeValue();
					
					NodeList firstNameElementLst3 = firstElement.getElementsByTagName("Peso");
					Element firstNameElement3 = (Element)firstNameElementLst3.item(0);
					NodeList firstName3 = firstNameElement3.getChildNodes();
					peso = ((Node)firstName3.item(0)).getNodeValue();
					
					NodeList firstNameElementLst4 = firstElement.getElementsByTagName("Targa");
					Element firstNameElement4 = (Element)firstNameElementLst4.item(0);
					NodeList firstName4 = firstNameElement4.getChildNodes();
					targa = ((Node)firstName4.item(0)).getNodeValue();
					
					Pallet toAdd = new Pallet(Integer.parseInt(id),Double.parseDouble(peso), targa, Integer.parseInt(idVolo));
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
			NodeList nodeList = d.getElementsByTagName("Volo");

			for (int i = 0; i < nodeList.getLength(); i++)
			{
				Node firstNode = nodeList.item(i);

				if (firstNode.getNodeType() == Node.ELEMENT_NODE)
				{
					String aereo, aeroportoDestinazione, aeroportoPartenza, dataOra, id, palletDisponibili, postiDisponibili;
					
					Element firstElement = (Element)firstNode;
					NodeList firstNameElementLst = firstElement.getElementsByTagName("Aereo");
					Element firstNameElement = (Element)firstNameElementLst.item(0);
					NodeList firstName = firstNameElement.getChildNodes();
					aereo = ((Node)firstName.item(0)).getNodeValue();
					
					NodeList firstNameElementLst2 = firstElement.getElementsByTagName("AeroportoDestinazione");
					Element firstNameElement2 = (Element)firstNameElementLst2.item(0);
					NodeList firstName2 = firstNameElement2.getChildNodes();
					aeroportoDestinazione = ((Node)firstName2.item(0)).getNodeValue();
					
					NodeList firstNameElementLst3 = firstElement.getElementsByTagName("AeroportoPartenza");
					Element firstNameElement3 = (Element)firstNameElementLst3.item(0);
					NodeList firstName3 = firstNameElement3.getChildNodes();
					aeroportoPartenza = ((Node)firstName3.item(0)).getNodeValue();
					
					NodeList firstNameElementLst4 = firstElement.getElementsByTagName("DataOra");
					Element firstNameElement4 = (Element)firstNameElementLst4.item(0);
					NodeList firstName4 = firstNameElement4.getChildNodes();
					dataOra = ((Node)firstName4.item(0)).getNodeValue();
					
					NodeList firstNameElementLst5 = firstElement.getElementsByTagName("Id");
					Element firstNameElement5 = (Element)firstNameElementLst5.item(0);
					NodeList firstName5 = firstNameElement5.getChildNodes();
					id = ((Node)firstName5.item(0)).getNodeValue();
					
					NodeList firstNameElementLst6 = firstElement.getElementsByTagName("PalletDisponibili");
					Element firstNameElement6 = (Element)firstNameElementLst6.item(0);
					NodeList firstName6 = firstNameElement6.getChildNodes();
					palletDisponibili = ((Node)firstName6.item(0)).getNodeValue();
					
					NodeList firstNameElementLst7 = firstElement.getElementsByTagName("PostiDisponibili");
					Element firstNameElement7 = (Element)firstNameElementLst7.item(0);
					NodeList firstName7 = firstNameElement7.getChildNodes();
					postiDisponibili = ((Node)firstName7.item(0)).getNodeValue();
					
					Volo toAdd = new Volo( Integer.parseInt(id), Date.valueOf(dataOra), Integer.parseInt(aeroportoPartenza), Integer.parseInt(aeroportoDestinazione) , Integer.parseInt(aereo), Integer.parseInt(postiDisponibili), Integer.parseInt(palletDisponibili));
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
