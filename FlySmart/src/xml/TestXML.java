package xml;

import java.io.*;
import org.w3c.dom.*;         //Interfacce di DOM 
import model.*;
import java.util.*;


public class TestXML {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		

		
		ArrayList<Passeggero> elenco = new ArrayList<Passeggero>();
		elenco.add(new Passeggero(1, 2, "gianlu", "dema", 3, 'm', 4.0, 5, 6, 7, 8, 9));
		XMLCreate<Passeggero> instance = new XMLCreate<Passeggero>();
		Document d = instance.createFlySmartDocument(elenco);
		instance.printDocument(d,"down");
		/*
		XMLToObj instance2 = new XMLToObj();
		ArrayList<Passeggero> elenco2 = new ArrayList<Passeggero>();
		elenco2 = instance2.createPasseggeroList("paxxeggero.xml");
		XMLCreate<Passeggero> instance3 = new XMLCreate<Passeggero>();
		Document d2 = instance3.createFlySmartDocument(elenco2);
		instance3.printDocument(d2, "provafinale");
		*/
	}

}
