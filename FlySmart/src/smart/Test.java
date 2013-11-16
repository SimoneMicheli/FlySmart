package smart;

import prenotazione.FlightNotFoundException;
import model.Volo;

import db.DBSession;

import util.Options;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testAlg();
		
	}
	
	public static void esempio(){
		/*Prova passeggeri*/
		/*SmartCheckin provaPasseggeri = new SmartCheckin();
		
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
		Options.initOptions();
		
		Volo v = DBSession.getVoloDAO().getByPartenzaDestinazione(1, 2).get(0);
		
		System.out.println(v);
		
		SmartCheckin c = null;
		try {
			c = new SmartCheckin(v.getId());
		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.calcolaCheckin();
	}

}
