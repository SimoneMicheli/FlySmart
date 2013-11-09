package xml;

import java.io.*;
//import org.w3c.dom.*;         //Interfacce di DOM 

import util.Options;
import model.*;
import java.util.*;


/**
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class TestXML {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		
		/*List<Volo> elenco = new ArrayList<Volo>();
		Date dt = new Date(5483267067974L);
		elenco.add(new Volo(0,dt,1,2,3,9.0, 25.0,StatoVolo.OPEN, TipoAereo.S));
		XMLCreate<Volo> instance = new XMLCreate<Volo>();
		Document d = instance.createFlySmartDocument(elenco);
		instance.printDocument(d,"primo.xml");
		XMLToObj instance2 = new XMLToObj();
		List<Volo> elenco2 = new ArrayList<Volo>();
		elenco2 = instance2.createVoloList("primo.xml");
		XMLCreate<Volo> instance3 = new XMLCreate<Volo>();
		Document d2 = instance3.createFlySmartDocument(elenco2);
		instance3.printDocument(d2, "secondo.xml");
		
		
		
		List<Pallet> list = new ArrayList<Pallet>();
		list.add(new Pallet(1, 63.4, "alfa", 19, null, null));
		list.add(new Pallet(1, 49.4, "beta", 20, null, null));
		XMLCreate<Pallet> istanza = new XMLCreate<Pallet>();
		Document documento = istanza.createFlySmartDocument(list);
		istanza.printDocument(documento, "test1.xml");
		XMLToObj istanza2 = new XMLToObj();
		List<Pallet> list2 = new ArrayList<Pallet>();
		list2 = istanza2.createPalletList("test1.xml");
		XMLCreate<Pallet> istanza3 = new XMLCreate<Pallet>();
		Document documento2 = istanza3.createFlySmartDocument(list2);
		istanza3.printDocument(documento2, "test2.xml");
		
		
		List<Passeggero> listA = new ArrayList<Passeggero>();
		//listA.add(new Passeggero(1,1,"alan","scarpellini",22,'m',1.0,1,1,1,1,1));
		//listA.add(new Passeggero(1,1,"alan2","scarpellini2",22,'m',1.0,1,1,1,1,1));
		XMLCreate<Passeggero> istanza4 = new XMLCreate<Passeggero>();
		Document documento3 = istanza4.createFlySmartDocument(listA);
		istanza4.printDocument(documento3,  "test3.xml");
		XMLToObj istanza5 = new XMLToObj();
		List<Passeggero> listA2 = new ArrayList<Passeggero>();
		listA2 = istanza5.createPasseggeroList("test3.xml");
		XMLCreate<Passeggero> istanza6 = new XMLCreate<Passeggero>();
		Document documento4 = istanza6.createFlySmartDocument(listA2);
		istanza6.printDocument(documento4,  "test4.xml");
		
		List<Aeroporto> listAq = new ArrayList<Aeroporto>();
		listAq.add(new Aeroporto(1,"boeing"));
		listAq.add(new Aeroporto(2,"boeing"));
		XMLCreate<Aeroporto> istanza4q = new XMLCreate<Aeroporto>();
		Document documento3q = istanza4q.createFlySmartDocument(listAq);
		istanza4q.printDocument(documento3q,  "test3q.xml");
		XMLToObj istanza5q = new XMLToObj();
		List<Aeroporto> listA2q = new ArrayList<Aeroporto>();
		listA2q = istanza5q.createAeroportoList("test3q.xml");
		XMLCreate<Aeroporto> istanza6q = new XMLCreate<Aeroporto>();
		Document documento4q = istanza6q.createFlySmartDocument(listA2q);
		istanza6q.printDocument(documento4q,  "test4q.xml");*/
		
		XMLToObj<Aeroporto> ist = new XMLToObj<Aeroporto>(Aeroporto.class);
		
		List<Aeroporto> list = ist.readObj(Options.aeroportiFileName);
		
		System.out.println("lista eroporti");
		
		for(Aeroporto a : list){
			System.out.println(a.toString());
		}
	}

}
