package smart;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import prenotazione.FlightNotFoundException;
import prenotazione.PrenotazionePasseggero;
import model.Passeggero;
import model.Sesso;
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
	
	public static void prenotaPass(Volo v){
		
		List<Passeggero> l = new LinkedList<Passeggero>();
		
		PrenotazionePasseggero prenotaz = new PrenotazionePasseggero();
		
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M));
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1980, Sesso.M));
		l.add(new Passeggero("valentina", "verdi", 15, 8, 1990, Sesso.F));
		
		//prenotazione 1
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		
		l.add(new Passeggero("ggg", "rossi", 10, 3, 2005, Sesso.M));
		l.add(new Passeggero("hhh", "bianchi", 4, 4, 2005, Sesso.M));
		l.add(new Passeggero("yyy", "verdi", 15, 8, 2005, Sesso.F));
		
		//prenotazione 2
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		l.add(new Passeggero("aaa", "rossi", 10, 3, 1960, Sesso.M));
		l.add(new Passeggero("bbb", "bianchi", 4, 4, 1980, Sesso.M));
		l.add(new Passeggero("ccc", "verdi", 15, 8, 1990, Sesso.M));
		//prenotazione 3
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		l.add(new Passeggero("xxx", "rossi", 10, 3, 1950, Sesso.F));
		l.add(new Passeggero("fff", "bianchi", 4, 4, 1950, Sesso.F));
		l.add(new Passeggero("zzz", "verdi", 15, 8, 1950, Sesso.F));
		//prenotazione 4
		prenotaz.prenota(l, v.getId());
		l.clear();
		
	}
	
	public static void testAlg(){

		Options.initOptions();
		
		Volo v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",1)
				.filter("aeroportoDestinazione =", 2)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);
		
		System.out.println(v);
		
		//prenotaPass(v);
		
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
