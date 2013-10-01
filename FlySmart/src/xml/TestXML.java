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
		

		
		// ArrayList<Passeggero> elenco = new ArrayList<Passeggero>();
		// elenco.add(new Passeggero( 1 , "Gianluigi","Buffon", 42, 'm', 100.1 , 100 , 1000));		
		// elenco.add(new Passeggero( 12 , "Marco","Amelia", 29, 'm', 120.12 , 120 , 2000));	
		// XMLCreate<Passeggero> instance = new XMLCreate<Passeggero>();
		// Document d = instance.createFlySmartDocument(elenco);
		// instance.printDocument(d,"down");
		XMLToObj instance2 = new XMLToObj();
		ArrayList<Aeroporto> elenco2 = new ArrayList<Aeroporto>();
		elenco2 = instance2.createAeroportoList("aeroporti.xml");
		XMLCreate<Aeroporto> instance3 = new XMLCreate<Aeroporto>();
		Document d2 = instance3.createFlySmartDocument(elenco2);
		instance3.printDocument(d2, "provafinale");
		
	}

}
