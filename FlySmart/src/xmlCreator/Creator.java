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
		elencoAeroporti.add(new Aeroporto(1, "Albenga"));
		elencoAeroporti.add(new Aeroporto(2, "Alghero"));
		elencoAeroporti.add(new Aeroporto(3, "Ancona"));
		elencoAeroporti.add(new Aeroporto(4, "Aosta"));
		elencoAeroporti.add(new Aeroporto(5, "Aviano"));
		elencoAeroporti.add(new Aeroporto(6, "Bari"));
		elencoAeroporti.add(new Aeroporto(7, "Belluno"));
		elencoAeroporti.add(new Aeroporto(8, "Bergamo"));
		elencoAeroporti.add(new Aeroporto(9, "Bologna"));
		elencoAeroporti.add(new Aeroporto(10, "Bolzano"));
		elencoAeroporti.add(new Aeroporto(11, "Brescia"));
		elencoAeroporti.add(new Aeroporto(12, "Brindisi"));
		elencoAeroporti.add(new Aeroporto(13, "Cagliari"));
		elencoAeroporti.add(new Aeroporto(14, "Capri"));
		elencoAeroporti.add(new Aeroporto(15, "Catania"));
		elencoAeroporti.add(new Aeroporto(16, "Catanzaro"));
		elencoAeroporti.add(new Aeroporto(17, "Crotone"));
		elencoAeroporti.add(new Aeroporto(18, "Cuneo"));
		elencoAeroporti.add(new Aeroporto(19, "Elba, Isola"));
		elencoAeroporti.add(new Aeroporto(20, "Fano"));
		elencoAeroporti.add(new Aeroporto(21, "Firenze"));
		elencoAeroporti.add(new Aeroporto(22, "Foggia"));
		elencoAeroporti.add(new Aeroporto(23, "Forlì"));
		elencoAeroporti.add(new Aeroporto(24, "Genova"));
		elencoAeroporti.add(new Aeroporto(25, "Grosseto"));
		elencoAeroporti.add(new Aeroporto(26, "Lamezia"));
		elencoAeroporti.add(new Aeroporto(27, "Lecce"));
		elencoAeroporti.add(new Aeroporto(28, "Milano Linate"));
		elencoAeroporti.add(new Aeroporto(29, "Milano Malpensa"));
		elencoAeroporti.add(new Aeroporto(30, "Napoli"));
		elencoAeroporti.add(new Aeroporto(31, "Olbia"));
		elencoAeroporti.add(new Aeroporto(32, "Padova"));
		elencoAeroporti.add(new Aeroporto(33, "Palermo"));
		elencoAeroporti.add(new Aeroporto(34, "Parma"));
		elencoAeroporti.add(new Aeroporto(35, "Perugia"));
		elencoAeroporti.add(new Aeroporto(36, "Pescara"));
		elencoAeroporti.add(new Aeroporto(37, "Pisa"));
		elencoAeroporti.add(new Aeroporto(38, "Ragusa"));
		elencoAeroporti.add(new Aeroporto(39, "Ravenna"));
		elencoAeroporti.add(new Aeroporto(40, "Reggio Calabria"));
		elencoAeroporti.add(new Aeroporto(41, "Reggio Emilia"));
		elencoAeroporti.add(new Aeroporto(42, "Roma Ciampino"));
		elencoAeroporti.add(new Aeroporto(43, "Roma Fiumicino"));
		elencoAeroporti.add(new Aeroporto(44, "Salerno"));
		elencoAeroporti.add(new Aeroporto(45, "Taranto"));
		elencoAeroporti.add(new Aeroporto(46, "Trento"));
		elencoAeroporti.add(new Aeroporto(47, "Treviso"));
		elencoAeroporti.add(new Aeroporto(48, "Trieste"));
		elencoAeroporti.add(new Aeroporto(49, "Torino"));
		elencoAeroporti.add(new Aeroporto(50, "Udine"));
		elencoAeroporti.add(new Aeroporto(51, "Venezia"));
		elencoAeroporti.add(new Aeroporto(52, "Verona"));
		elencoAeroporti.add(new Aeroporto(53, "Vicenza"));

		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreate<Aeroporto>();
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		XMLAeroporti.printDocument(d,"aeroporti.xml");
	}

	public static void creaVoli() throws IOException{
		List<Volo> voli = new LinkedList<Volo>();
		Calendar c = Calendar.getInstance();
		int idIncr = 0;

		for(int i = 1; i<54; i++)
		{
			for(int j = 1; j<54; j++)
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
						double result =  (Math.random()*(110-40)+40)*100;
						result = Math.round(result);
						result = result/100;
						voli.add(new Volo(idIncr, c.getTime(), i, j, (int) (Math.random()*(100-1)+1), result, StatoVolo.OPEN, TipoAereo.M));
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
