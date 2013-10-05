package xmlCreator;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import xml.XMLCreate;
import model.*;

public class Creator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		try {
			creaAeroporti();
			creaVoli();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void creaAeroporti() throws IOException{
		List<Aeroporto> elencoAeroporti;
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(1, "Malpensa", 20.0, 20.0));
		elencoAeroporti.add(new Aeroporto(2, "Bergamo", 50.0, 10.0));
		elencoAeroporti.add(new Aeroporto(3, "Fiumicino", 100.0, 40.0));

		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreate<Aeroporto>();
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		XMLAeroporti.printDocument(d,"aeroporti.xml");
	}
	
	public static void creaVoli() throws IOException{
		List<Volo> voli = new LinkedList<Volo>();
		Calendar c = Calendar.getInstance();
		
		c.set(2013, 10, 20, 15, 0);
		voli.add(new Volo(1, c.getTime() , 1, 2, 767, 100, 15,2.0));
		c.set(2014, 4, 10, 4, 10);
		voli.add(new Volo(2, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		c.set(2013, 12, 25, 13, 0);
		voli.add(new Volo(3, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		c.set(2013, 11, 29, 2, 0);
		voli.add(new Volo(4, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		
		XMLCreate<Volo> XMLVoli = new XMLCreate<Volo>();
		Document d = XMLVoli.createFlySmartDocument(voli);
		XMLVoli.printDocument(d,"voli.xml");
	}
}
