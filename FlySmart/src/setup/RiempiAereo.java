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

		
		
		//l.add(new Pallet(1396, "AR432T", v.getId()));
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
		
		
		l.add(new Passeggero("Mario", "Rossi", 10, 3, 1960, Sesso.M)); 
		l.add(new Passeggero("Giuseppe", "Verdi", 1, 4, 1970, Sesso.M));
		l.add(new Passeggero("Marco", "Gialli", 22, 2, 1961, Sesso.M));
		l.add(new Passeggero("Guido", "Bianchi", 14, 7, 1962, Sesso.M));
		l.add(new Passeggero("Luca", "Rossini", 14, 10, 1960, Sesso.M)); 
		l.add(new Passeggero("Mario", "Berbenni", 15, 3, 1965, Sesso.M));
		l.add(new Passeggero("Guido", "Gialli", 14, 3, 1964, Sesso.M));
		l.add(new Passeggero("Luca", "Berbenni", 15, 6, 1961, Sesso.M));
		l.add(new Passeggero("Marco", "Berbenni", 10, 11, 1960, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();
		
		
		l.add(new Passeggero("Maria", "Rossi", 10, 4, 1980, Sesso.F));
		l.add(new Passeggero("Giovanna", "Verdi", 17, 4, 1978, Sesso.F));
		l.add(new Passeggero("Benedetta", "Gialli", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Simona", "Bianchi", 16, 3, 1970, Sesso.F));
		l.add(new Passeggero("Guido", "Rossini", 22, 3, 1971, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();
		
		
		l.add(new Passeggero("Giorgia", "Galli", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Giorgia", "Guidetti", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Simone", "Berbenni", 15, 3, 1965, Sesso.M));
		l.add(new Passeggero("Guido", "Galli", 22, 3, 1971, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();
		

		l.add(new Passeggero("Benedetta", "Bianchi", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Simone", "Rossi", 15, 3, 1965, Sesso.M));
		l.add(new Passeggero("Gianluca", "Verdi", 22, 9, 1971, Sesso.M));
		l.add(new Passeggero("Stefano", "Verdi", 20, 3, 1997, Sesso.M));
		l.add(new Passeggero("Guido", "Gritti", 22, 9, 2004, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();
		

		l.add(new Passeggero("Giorgia", "Verdi", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Guido", "Gritti", 22, 9, 2004, Sesso.M));
		l.add(new Passeggero("Mauro", "Galli", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Giorgio", "Berbenni", 15, 3, 1965, Sesso.M));
		prenotaz.prenota(l, v.getId()); l.clear();
		
		l.add(new Passeggero("Bernardo", "Agazzi", 15, 5, 1997, Sesso.M));
		l.add(new Passeggero("Federica", "Bianchi", 15, 5, 1970, Sesso.F));
		l.add(new Passeggero("Stefano", "Burini", 22, 9, 1990, Sesso.M));
		l.add(new Passeggero("Bernardo", "Bresciani", 22, 9, 1975, Sesso.M));
		l.add(new Passeggero("Salvatore", "Brognara", 22, 9, 1990, Sesso.M)); 
		l.add(new Passeggero("Luca", "Campanelli", 22, 9, 2005, Sesso.M)); 
		prenotaz.prenota(l, v.getId()); l.clear();
		
		l.add(new Passeggero("Bernardo", "Di Gennaro", 10, 4, 1980, Sesso.M)); 
		l.add(new Passeggero("Federica", "Camozzi", 17, 4, 1978, Sesso.F)); 
		l.add(new Passeggero("Luca", "De Rossi", 15, 5, 1970, Sesso.M)); 
		l.add(new Passeggero("Giovanna", "Sciascia", 16, 3, 1970, Sesso.F)); 
		l.add(new Passeggero("Benedetto", "Cornolti", 22, 3, 1971, Sesso.M)); 
		prenotaz.prenota(l, v.getId()); l.clear();
		
		l.add(new Passeggero("Bernardo", "Feliciani", 10, 4, 1980, Sesso.M)); 
		l.add(new Passeggero("Giovanna", "Camozzi", 17, 4, 1978, Sesso.F)); 
		l.add(new Passeggero("Lucia", "Castellano", 15, 5, 1970, Sesso.F)); 
		l.add(new Passeggero("Roberta", "Gavazzeni", 16, 3, 1970, Sesso.F)); 
		l.add(new Passeggero("Giacomo", "Checchi", 22, 3, 1971, Sesso.M)); 
		prenotaz.prenota(l, v.getId()); l.clear();
	}

}
