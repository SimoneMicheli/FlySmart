package xmlCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import xml.XMLCreate;
import xml.XMLToObj;

import model.Aeroporto;
import model.Passeggero;

public class Creator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<Aeroporto> elencoAeroporti;



		//carica elenco aeroporti
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(1, "Malpensa", 20, 20));
		elencoAeroporti.add(new Aeroporto(2, "Bergamo", 50, 10));
		elencoAeroporti.add(new Aeroporto(3, "Fiumicino", 100, 40));
		
		
		
		XMLCreate<Aeroporto> instance = new XMLCreate<Aeroporto>();
		
		Document d = instance.createFlySmartDocument(elencoAeroporti);
		instance.printDocument(d,"aeroporti");
		
		
		/*
		XMLToObj instance2 = new XMLToObj();
		ArrayList<Passeggero> elenco2 = new ArrayList<Passeggero>();
		elenco2 = instance2.createPasseggeroList("down.xml");
		XMLCreate<Passeggero> instance3 = new XMLCreate<Passeggero>();
		Document d2 = instance3.createFlySmartDocument(elenco2);
		instance3.printDocument(d2, "down2");*/
	}

}
