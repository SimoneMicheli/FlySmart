package db;

import java.net.UnknownHostException;
import java.util.Calendar;

import model.Pallet;
import model.Passeggero;
import model.Sesso;
import model.StatoVolo;
import model.TipoAereo;
import model.Volo;

import com.google.code.morphia.Datastore;

public class dbTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		
		//creo e salvo alcuni pallet
		Datastore ds = DBSession.getInstance();
		
		Pallet p = new Pallet(100, "1000", null);
		ds.save(p);
		
		p = new Pallet(200, "1001", null);
		ds.save(p);
		
		p = new Pallet(300, "1002", null);
		ds.save(p);
		
		Passeggero pass = new Passeggero("mario", "rossi", 23, 10, 1990, Sesso.M);
		ds.save(pass);
		
		pass = new Passeggero("maria", "bianchi", 15, 3, 1970, Sesso.M);
		ds.save(pass);
		
		pass = new Passeggero("giovanni", "allevi", 1, 4, 1980, Sesso.M);
		ds.save(pass);
		
		Calendar c = Calendar.getInstance();
		c.set(2014,10, 14, (int) (Math.random()*(23-0)+1), (int) (Math.random()*(59-0)+1));
		Volo v = new Volo(c.getTime(), 1, 5, 25.3, 140.3, StatoVolo.OPEN, TipoAereo.S);
		ds.save(v);
		
		c.set(2014,2, 2, (int) (Math.random()*(23-0)+1), (int) (Math.random()*(59-0)+1));
		v = new Volo(c.getTime(), 2, 10, 4.3, 140.3, StatoVolo.OPEN, TipoAereo.S);
		ds.save(v);
		
		c.set(2014,6, 6, (int) (Math.random()*(23-0)+1), (int) (Math.random()*(59-0)+1));
		v = new Volo(c.getTime(), 3, 11, 25.3, 140.3, StatoVolo.OPEN, TipoAereo.S);
		ds.save(v);
		
	}

}
