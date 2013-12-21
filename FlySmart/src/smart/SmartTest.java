/**
 * 
 */
package smart;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;

import model.Pallet;
import model.Passeggero;
import model.Sesso;
import model.Volo;

import org.junit.Test;

import prenotazione.FlightNotFoundException;
import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;

import util.Options;
import db.DBSession;

/**
 *
 */
public class SmartTest {
	private static List<Pallet> elencoPallet = new LinkedList<Pallet>();
	private static List<Passeggero> elencoPasseggeri = new LinkedList<Passeggero>();
	private static double[] sbilanciamentoFinale = {-223.5,-409.5};

	@Test
	public void test() {
			
		try {
			Options.initOptions();
		} catch (InvalidPropertiesFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Volo v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",1)
				.filter("aeroportoDestinazione =", 2)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);
		
		Pallet palleta = new Pallet(1400, "a", v.getId());
		palleta.setFila(2);
		palleta.setColonna(0);
		elencoPallet.add(palleta);
		Pallet palletb = new Pallet(800, "b", v.getId());
		palletb.setFila(0);
		palletb.setColonna(0);
		elencoPallet.add(palletb);
		Pallet palletc = new Pallet(1300, "c", v.getId());
		palletc.setFila(1);
		palletc.setColonna(1);
		elencoPallet.add(palletc);
		Pallet palletd = new Pallet(800, "d", v.getId());
		palletd.setFila(3);
		palletd.setColonna(1);
		elencoPallet.add(palletd);
		Pallet pallete = new Pallet(1400, "e", v.getId());
		pallete.setFila(1);
		pallete.setColonna(0);
		elencoPallet.add(pallete);
		Pallet palletf = new Pallet(900, "f", v.getId());
		palletf.setFila(3);
		palletf.setColonna(0);
		elencoPallet.add(palletf);		
		Pallet palletg = new Pallet(1100, "g", v.getId());
		palletg.setFila(2);
		palletg.setColonna(1);
		elencoPallet.add(palletg);
		Pallet palleth = new Pallet(1000, "h", v.getId());
		palleth.setFila(0);
		palleth.setColonna(1);
		elencoPallet.add(palleth);
		
		Passeggero passa1 = new Passeggero("alberto", "rossi", 10, 3, 1960, Sesso.M);
		passa1.setFila(6);
		passa1.setColonna(3);
		elencoPasseggeri.add(passa1);
		Passeggero passb1 = new Passeggero("baldassarre", "rossi", 10, 3, 1960, Sesso.M); //80
		passb1.setFila(6);
		passb1.setColonna(2);
		elencoPasseggeri.add(passb1);
		Passeggero passc1 = new Passeggero("carlo", "rossi", 10, 3, 1960, Sesso.M); //80
		passc1.setFila(6);
		passc1.setColonna(1);
		elencoPasseggeri.add(passc1);
		Passeggero passd1 = new Passeggero("demetrio", "rossi", 10, 3, 1960, Sesso.M); //80
		passd1.setFila(6);
		passd1.setColonna(0);
		elencoPasseggeri.add(passd1);
		Passeggero passe1 = new Passeggero("ernesto", "rossi", 10, 3, 1960, Sesso.M); //80
		passe1.setFila(5);
		passe1.setColonna(0);
		elencoPasseggeri.add(passe1);
		Passeggero passf1 = new Passeggero("federico", "rossi", 10, 3, 1960, Sesso.M); //80
		passf1.setFila(5);
		passf1.setColonna(1);
		elencoPasseggeri.add(passf1);

		Passeggero passa2 = new Passeggero("andrea", "bianchi", 10, 3, 1960, Sesso.M); //80
		passa2.setFila(6);
		passa2.setColonna(4);
		elencoPasseggeri.add(passa2);
		Passeggero passb2 = new Passeggero("bartolomeo", "bianchi", 10, 3, 1960, Sesso.M); //80
		passb2.setFila(6);
		passb2.setColonna(5);
		elencoPasseggeri.add(passb2);
		Passeggero passc2 = new Passeggero("cristoforo", "bianchi", 4, 4, 1997, Sesso.M); //64
		passc2.setFila(5);
		passc2.setColonna(5);
		elencoPasseggeri.add(passc2);
		Passeggero passd2 = new Passeggero("debora", "bianchi", 4, 4, 1997, Sesso.F); //48
		passd2.setFila(5);
		passd2.setColonna(4);
		elencoPasseggeri.add(passd2);
		Passeggero passe2 = new Passeggero("elena", "bianchi", 10, 3, 1960, Sesso.F); //60
		passe2.setFila(5);
		passe2.setColonna(3);
		elencoPasseggeri.add(passe2);
		Passeggero passf2 = new Passeggero("francesca", "bianchi", 10, 3, 1960, Sesso.F); //60
		passf2.setFila(5);
		passf2.setColonna(2);
		elencoPasseggeri.add(passf2);
		
		prenotaPallet(v);
		prenotaPass(v);
		SmartCheckin c = null;
		try {
			c = new SmartCheckin(v.getId());
		} catch (FlightNotFoundException e) {
			e.printStackTrace();
		}

		CheckinReport s = c.calcolaCheckin();
		
		List<Pallet> listaPalletCheckata = s.getPallets();
		assertEquals(listaPalletCheckata.size(),elencoPallet.size());
		for(Pallet actual : listaPalletCheckata)
		{
			for(Pallet expected : elencoPallet)
			{
				if(actual.getTarga().equals(expected.getTarga()))
				{
					assertEquals(expected.getColonna(),actual.getColonna());
					assertEquals(expected.getFila(),actual.getFila());
				}

			}

		}
		
		List<Passeggero> listaPasseggeroCheckata = s.getPasseggeri();
		assertEquals(listaPasseggeroCheckata.size(),elencoPasseggeri.size());
		for(Passeggero actual: listaPasseggeroCheckata)
		{
			for(Passeggero expected : elencoPasseggeri)
			{
				if(actual.getCognome().equals(expected.getCognome()) && actual.getNome().equals(expected.getCognome()))
				{
					assertEquals(expected.getCognome(),actual.getColonna());
					assertEquals(expected.getFila(),actual.getFila());
				}
			}
		}

		assertEquals(s.getMom()[0],sbilanciamentoFinale[0],0.00001);
		assertEquals(s.getMom()[1],sbilanciamentoFinale[1],0.00001);
		
	}

	public static void prenotaPallet(Volo v){

		List<Pallet> l = new LinkedList<Pallet>();

		PrenotazionePallet prenotaz = new PrenotazionePallet();

		l.add(new Pallet(1400, "a", v.getId()));
		l.add(new Pallet(800, "b", v.getId()));
		l.add(new Pallet(1300, "c", v.getId()));

		//prenotazione 1
		prenotaz.prenota(l, v.getId());
		l.clear();


		l.add(new Pallet(800, "d", v.getId()));
		l.add(new Pallet(1200, "e", v.getId()));
		l.add(new Pallet(900, "f", v.getId()));

		//prenotazione 2
		prenotaz.prenota(l, v.getId());
		l.clear();

		l.add(new Pallet(1100, "g", v.getId()));
		l.add(new Pallet(1000, "h", v.getId()));
		//prenotazione 3
		prenotaz.prenota(l, v.getId());
		l.clear();


	}
	
	public static void prenotaPass(Volo v){

		List<Passeggero> l = new LinkedList<Passeggero>();

		PrenotazionePasseggero prenotaz = new PrenotazionePasseggero();
		

		l.add(new Passeggero("alberto", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("baldassarre", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("carlo", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("demetrio", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("ernesto", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("federico", "rossi", 10, 3, 1960, Sesso.M)); //80
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("andrea", "bianchi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("bartolomeo", "bianchi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("cristoforo", "bianchi", 4, 4, 1997, Sesso.M)); //64
		l.add(new Passeggero("debora", "bianchi", 4, 4, 1997, Sesso.F)); //48
		l.add(new Passeggero("elena", "bianchi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("francesca", "bianchi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

	}
}
