package smart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import comparator.VoloComparator;

import model.Pallet;
import model.Passeggero;
import model.Volo;
import util.Options;
import xml.XMLToObj;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testAlg();
		
	}
	
	public static void esempio(){
		/*Prova passeggeri*/
		SmartCheckin provaPasseggeri = new SmartCheckin(null, null, null);
		
		int[] resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		
// 		continuiamo da qua!!! 
		//		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(,);
		//      System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		
		/*Prova pallet
		SmartCheckin provaPallet = new SmartCheckin(null, null, null);
		int[] resultPallet = provaPallet.postoLiberoPallet(0,2);
		resultPallet = provaPallet.postoLiberoPallet(1,1);
		resultPallet = provaPallet.postoLiberoPallet(1,0);
		resultPallet = provaPallet.postoLiberoPallet(0,3);
		resultPallet = provaPallet.postoLiberoPallet(1,2);
		resultPallet = provaPallet.postoLiberoPallet(0,1);
		resultPallet = provaPallet.postoLiberoPallet(0,0);
		resultPallet = provaPallet.postoLiberoPallet(1,3);
		System.out.println("Posto: "+resultPallet[1]+" "+resultPallet[0]);
		 */
	}
	
	public static void testAlg(){
		List<Volo> voli = new LinkedList<Volo>();
		List<Pallet> pallets = new ArrayList<Pallet>();
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);
		
		int idVolo = 0;
		
		Options.LoadDefaultOptions();
		voli = parserXML.readObj(Options.voliFileName);
		Collections.sort(voli, VoloComparator.ID_ORDER);
		int pos = Collections.binarySearch(voli,new Integer(idVolo));
		
		Volo v = voli.get(pos);
		
		XMLToObj<Pallet> parserXMLPallet = new XMLToObj<Pallet>(Pallet.class);
		pallets = parserXMLPallet.readObj(String.format(Options.voloPalletFileName, idVolo));
		
		SmartCheckin c = new SmartCheckin(null, null, null);
		
		c.posizionaPallet(pallets, v);
	}

}
