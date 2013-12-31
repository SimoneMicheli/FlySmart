package setup;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;
import com.google.code.morphia.Datastore;
import db.DBSession;
import util.Options;
import xml.XMLCreate;
import xml.XMLCreateImpl;
import model.*;

/**
 */
public class CreaAeroportiVoli {

	/**
	 * @param args
	
	 * @throws IOException  */
	public static void main(String[] args)  {
		System.out.println("Avvio generazione dati");
		try {
			Options.initOptions();
			//create folder
			new File("data/xml/").mkdirs();
			creaAeroporti();
			creaVoli();
			System.out.println("Generazione dati terminata");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method creaAeroporti.
	 * @throws IOException
	 */
	public static void creaAeroporti() throws IOException{
		List<Aeroporto> elencoAeroporti;
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(1, "Ancona"));
		elencoAeroporti.add(new Aeroporto(2, "Bari"));
		elencoAeroporti.add(new Aeroporto(3, "Bergamo"));
		elencoAeroporti.add(new Aeroporto(4, "Bologna"));
		elencoAeroporti.add(new Aeroporto(5, "Bolzano"));
		elencoAeroporti.add(new Aeroporto(6, "Cagliari"));
		elencoAeroporti.add(new Aeroporto(7, "Catania"));
		elencoAeroporti.add(new Aeroporto(8, "Catanzaro"));
		elencoAeroporti.add(new Aeroporto(9, "Firenze"));
		elencoAeroporti.add(new Aeroporto(10, "Foggia"));
		elencoAeroporti.add(new Aeroporto(11, "Genova"));
		elencoAeroporti.add(new Aeroporto(12, "Lecce"));
		elencoAeroporti.add(new Aeroporto(13, "Milano Linate"));
		elencoAeroporti.add(new Aeroporto(14, "Milano Malpensa"));
		elencoAeroporti.add(new Aeroporto(15, "Napoli"));
		elencoAeroporti.add(new Aeroporto(16, "Palermo"));
		elencoAeroporti.add(new Aeroporto(17, "Parma"));
		elencoAeroporti.add(new Aeroporto(18, "Perugia"));
		elencoAeroporti.add(new Aeroporto(19, "Pescara"));
		elencoAeroporti.add(new Aeroporto(20, "Pisa"));
		elencoAeroporti.add(new Aeroporto(21, "Reggio Emilia"));
		elencoAeroporti.add(new Aeroporto(22, "Roma Ciampino"));
		elencoAeroporti.add(new Aeroporto(23, "Roma Fiumicino"));
		elencoAeroporti.add(new Aeroporto(24, "Trento"));
		elencoAeroporti.add(new Aeroporto(25, "Torino"));
		elencoAeroporti.add(new Aeroporto(26, "Udine"));
		elencoAeroporti.add(new Aeroporto(27, "Venezia"));
		elencoAeroporti.add(new Aeroporto(28, "Verona"));
		elencoAeroporti.add(new Aeroporto(29, "Vicenza"));

		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreateImpl<Aeroporto>();
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		XMLAeroporti.printDocument(d,Options.aeroportiFileName);
	}

	/**
	 * Method creaVoli.
	 * @throws IOException
	 */
	public static void creaVoli() throws IOException{
		List<Volo> voli = new LinkedList<Volo>();
		Calendar c = Calendar.getInstance();

		c.set(2014, 0, 20, 20, 45); 
		voli.add(new Volo(c.getTime() , 3, 12, 79.0, 2.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,0,22,22,45);
		voli.add(new Volo(c.getTime() , 3, 7, 89.0, 3.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,1,10,8,10);
		voli.add(new Volo(c.getTime() , 3, 22, 59.0, 1.0, StatoVolo.OPEN, TipoAereo.L));
		c.set(2014,1,15,20,25);
		voli.add(new Volo(c.getTime() , 3, 16, 129.0, 6.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,1,4,16,45);
		voli.add(new Volo(c.getTime() , 3, 6, 89.0, 2.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,1,21,3,15);
		voli.add(new Volo(c.getTime() , 1, 18, 99.0, 3.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,2,2,2,20);
		voli.add(new Volo(c.getTime() , 1, 19, 89.0, 4.0, StatoVolo.OPEN, TipoAereo.L));
		c.set(2014,2,18,15,35);
		voli.add(new Volo(c.getTime() , 1, 2, 79.0, 2.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,3,1,15,15);
		voli.add(new Volo(c.getTime() , 2, 22, 69.0, 3.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,3,13,20,5);
		voli.add(new Volo(c.getTime() , 2, 1, 69.0, 4.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,4,20,13,15);
		voli.add(new Volo(c.getTime() , 2, 25, 59.0, 2.0, StatoVolo.OPEN, TipoAereo.L));
		c.set(2014,5,13,12,25);
		voli.add(new Volo(c.getTime() , 4, 6, 79.0, 2.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,5,22,18,55);
		voli.add(new Volo(c.getTime() , 4, 23, 109.0, 3.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,6,25,19,55);
		voli.add(new Volo(c.getTime() , 4, 12, 99.0, 3.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,6,23,15,45);
		voli.add(new Volo(c.getTime() , 5, 1, 89.0, 2.0, StatoVolo.OPEN, TipoAereo.L));
		c.set(2014,7,6,20,45);
		voli.add(new Volo(c.getTime() , 5, 2, 49.0, 4.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,8,7,22,35);
		voli.add(new Volo(c.getTime() , 6, 3, 59.0, 4.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,9,1,23,25);
		voli.add(new Volo(c.getTime() , 6, 5, 69.0, 2.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,9,26,24,15);
		voli.add(new Volo(c.getTime() , 7, 6, 79.0, 3.0, StatoVolo.OPEN, TipoAereo.M));
		c.set(2014,10,29,25,5);
		voli.add(new Volo(c.getTime() , 7, 9, 79.0, 5.0, StatoVolo.OPEN, TipoAereo.L));
		c.set(2014,10,20,21,55);
		voli.add(new Volo(c.getTime() , 8, 11, 99.0, 2.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,11,13,12,20);
		voli.add(new Volo(c.getTime() , 8, 13, 79.0, 5.0, StatoVolo.OPEN, TipoAereo.S));
		c.set(2014,11,12,12,35);
		voli.add(new Volo(c.getTime() , 9, 20, 69.0, 7.0, StatoVolo.OPEN, TipoAereo.M));
		
		Datastore ds = DBSession.getInstance();
		ds.save(voli);
	}
}
