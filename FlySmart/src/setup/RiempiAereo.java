package setup;

import java.io.IOException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;

import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;
import model.Pallet;
import model.Passeggero;
import model.Sesso;
import model.Volo;

import db.DBSession;

import util.*;



/**
 */
public class RiempiAereo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {

		Options.initOptions();

		Volo v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",3)
				.filter("aeroportoDestinazione =", 12)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);

		System.out.println(v);

		prenotaPallet(v);
		prenotaPass(v);


	}
	

	public static void prenotaPallet(Volo v){

		List<Pallet> l = new LinkedList<Pallet>();
		PrenotazionePallet prenotaz = new PrenotazionePallet();

		
		
		l.add(new Pallet(1396, "AR432T", v.getId()));
		l.add(new Pallet(699, "TR606U", v.getId()));
		l.add(new Pallet(677, "RR870I", v.getId()));
		l.add(new Pallet(656, "KK123R", v.getId()));
		l.add(new Pallet(648, "TZ998A", v.getId()));
		prenotaz.prenota(l, v.getId());
		l.clear();



	}

	public static void prenotaPass(Volo v){

		List<Passeggero> l = new LinkedList<Passeggero>();
		PrenotazionePasseggero prenotaz = new PrenotazionePasseggero();
		

		
		
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();



	}

}
