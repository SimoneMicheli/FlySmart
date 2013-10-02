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
		

		
		List<Volo> elenco = new ArrayList<Volo>();
		Date dt = new Date(5483267067974L);
		elenco.add(new Volo(0,dt,1,2,3,4,5));
		XMLCreate<Volo> instance = new XMLCreate<Volo>();
		Document d = instance.createFlySmartDocument(elenco);
		instance.printDocument(d,"primo.xml");
		XMLToObj instance2 = new XMLToObj();
		List<Volo> elenco2 = new ArrayList<Volo>();
		elenco2 = instance2.createVoloList("primo.xml");
		XMLCreate<Volo> instance3 = new XMLCreate<Volo>();
		Document d2 = instance3.createFlySmartDocument(elenco2);
		instance3.printDocument(d2, "secondo.xml");
		
	}

}
