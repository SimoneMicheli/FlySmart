package smart;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import prenotazione.FlightNotFoundException;
import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;
import model.Pallet;
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
		
Options.initOptions();
		
		Volo v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",1)
				.filter("aeroportoDestinazione =", 2)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);
		
		System.out.println(v);
		
		prenotaPallet(v);
		prenotaPass(v);
		
		SmartCheckin c = null;
		try {
			c = new SmartCheckin(v.getId());
		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.calcolaCheckin();
		
	}
	
	public static void prenotaPallet(Volo v){
		
		List<Pallet> l = new LinkedList<Pallet>();
		
		PrenotazionePallet prenotaz = new PrenotazionePallet();
		
		l.add(new Pallet(1000, "a", v.getId()));
		l.add(new Pallet(600, "b", v.getId()));
		l.add(new Pallet(1400, "c", v.getId()));
		
		//prenotazione 1
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		
		l.add(new Pallet(978, "d", v.getId()));
		l.add(new Pallet(1236, "e", v.getId()));
		l.add(new Pallet(1338, "f", v.getId()));
		
		//prenotazione 2
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		l.add(new Pallet(445, "g", v.getId()));
		l.add(new Pallet(778, "h", v.getId()));
		//prenotazione 3
		prenotaz.prenota(l, v.getId());
		l.clear();
		
		/*l.add(new Pallet(1000, "a", v.getId()));
		l.add(new Pallet(600, "b", v.getId()));
		l.add(new Pallet(1400, "c", v.getId()));
		//prenotazione 4
		prenotaz.prenota(l, v.getId());
		l.clear();*/
		
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

}
