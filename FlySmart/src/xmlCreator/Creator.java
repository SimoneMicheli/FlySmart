package xmlCreator;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import xml.XMLCreate;
import model.*;

public class Creator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		System.out.println("Avvio generazione dati");
		try {
			creaAeroporti();
			creaVoli();
			System.out.println("Generazione dati terminata");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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

		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreate<Aeroporto>();
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		XMLAeroporti.printDocument(d,"aeroporti.xml");
	}

	public static void creaVoli() throws IOException{
		List<Volo> voli = new LinkedList<Volo>();
		Calendar c = Calendar.getInstance();
		int idIncr = 0;

		for(int i = 1; i<30; i++)
		{
			for(int j = 1; j<30; j++)
			{
				if(i!=j)
					for(int n = 0; n < (int) (Math.random()*(7-2)+2); n++)
					{
						int rMese = (int) (Math.random()*(12-0)+0);
						boolean repeat = true;
						int rGiorno = 1;
						while(repeat)
						{
							rGiorno = (int) (Math.random()*(32-1)+1);
							repeat= false;
							if(rMese == 1)
								if(rGiorno>28)
									repeat = true;
							if(rMese == 3 || rMese == 5 || rMese == 8 || rMese == 10 )
								if(rGiorno>31)
									repeat=true;
						}
						c.set(2014,(int) rMese, rGiorno, (int) (Math.random()*(23-0)+1), (int) (Math.random()*(59-0)+1));
						double prezzoPasseggeri =  (Math.random()*(110-40)+40)*100;
						prezzoPasseggeri = Math.round(prezzoPasseggeri);
						prezzoPasseggeri = prezzoPasseggeri/100;
						double prezzoPallet =  (Math.random()*(5-0.7)+0.7)*100;
						prezzoPallet = Math.round(prezzoPallet);
						prezzoPallet = prezzoPallet/100;
						voli.add(new Volo(idIncr, c.getTime(), i, j, (int) (Math.random()*(100-1)+1), prezzoPasseggeri, prezzoPallet, StatoVolo.OPEN, TipoAereo.M));
						idIncr++;
					}
			}
		}

		/*
		c.set(2013, 10, 20, 15, 0);
		voli.add(new Volo(1, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		c.set(2014, 4, 10, 4, 10);
		voli.add(new Volo(2, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		c.set(2013, 12, 25, 13, 0);
		voli.add(new Volo(3, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		c.set(2013, 11, 29, 2, 0);
		voli.add(new Volo(4, c.getTime() , 1, 2, 767, 100, 15, 2.0));
		 */
		XMLCreate<Volo> XMLVoli = new XMLCreate<Volo>();
		Document d = XMLVoli.createFlySmartDocument(voli);
		XMLVoli.printDocument(d,"voli.xml");
	}
}
