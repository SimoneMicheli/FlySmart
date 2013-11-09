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
public class XMLToObj<T extends Model>{
	
	private Class<T> cls;
	
	public XMLToObj(Class<T> cls){
		this.cls = cls;
	}
	
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
				
				Map<String, String> map= new HashMap<String, String>(); // crea un Hashmap con <nome del campo da inserire nel costruttore; oggetto contenuto nell'xml>
				Node object = nodeList.item(i); // prende un nodo della lista di nodi caricati dall'xml	
				
				NodeList atributes = object.getChildNodes(); //attributi oggetto
				
				for (int c = 0; c< atributes.getLength(); c++){ //leggo attributi oggetto

					Node attributo = atributes.item(c);

					if(attributo.getNodeType() == Node.ELEMENT_NODE){

						String nome = attributo.getNodeName();	//ottengo nome attributo
						Node cf = attributo.getFirstChild();
						String value = cf.getNodeValue();		//ottengo valore

						map.put(nome, value);
					}
				}
				
				//determino oggetto
				switch (cls.getCanonicalName()) {
				case "model.Passeggero":
					Passeggero p = new Passeggero(Integer.parseInt(map.get("id")), Integer.parseInt(map.get("idGruppo")), map.get("nome"), map.get("cognome"), Integer.parseInt(map.get("eta")), Sesso.valueOf(map.get("sesso")), Double.parseDouble(map.get("pesoBagagli")), Integer.parseInt(map.get("idVolo")), Integer.parseInt(map.get("fila")), Integer.parseInt(map.get("colonna")), Integer.parseInt(map.get("giorno")), Integer.parseInt(map.get("mese")), Integer.parseInt(map.get("anno")));
					lista.add((T) p);
					break;

				case "model.Aeroporto":
					Aeroporto a = new Aeroporto(Integer.parseInt(map.get("id")), map.get("nome"));
					lista.add((T) a);
					break;
					
				case "model.Pallet":
					Pallet pall = new Pallet(Integer.parseInt(map.get("id")), Double.parseDouble(map.get("peso")), map.get("targa"), Integer.parseInt(map.get("idVolo")), Integer.parseInt(map.get("fila")), Integer.parseInt(map.get("colonna")) );
					lista.add((T) pall);
					break;
					
				case "model.Volo":
					//sistemo il formato delle date
					DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ROOT);
					Date dt=new Date();
					try {
						//parsing della data
						dt=df.parse(map.get("dataOra"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Volo v = new Volo( Integer.parseInt(map.get("id")), dt, Integer.parseInt(map.get("aeroportoPartenza")) , Integer.parseInt(map.get("aeroportoDestinazione")), Integer.parseInt(map.get("aereo")), Integer.parseInt(map.get("postiDisponibili")), Integer.parseInt(map.get("palletDisponibili")), Double.parseDouble(map.get("prezzoPasseggero")), Double.parseDouble(map.get("prezzoPallet")), StatoVolo.valueOf(map.get("stato")), TipoAereo.valueOf(map.get("tipoAereo")), Integer.parseInt(map.get("lastID")), Integer.parseInt(map.get("lastPalletID")), Integer.parseInt(map.get("lastGroupID")));
					lista.add((T) v);
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
