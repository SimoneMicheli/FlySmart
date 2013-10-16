package xmlCreator;

import java.io.File;
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
		try {
			cancellaConfigFile();
			creaAeroporti();
			creaVoli();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void cancellaConfigFile(){
		File f = new File("config.xml");
		if (!f.exists()){
			System.out.println("config file not exist");
			return;
		}
		Boolean rx = f.delete();

		if(rx)
			System.out.println("config file deleted");
		else
			System.err.println("can't delete config file");
	}

	public static void creaAeroporti() throws IOException{
		List<Aeroporto> elencoAeroporti;
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(1, "Albenga", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(2, "Alghero", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(3, "Ancona", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(4, "Aosta", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(5, "Aviano", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(6, "Bari", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(7, "Belluno", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(8, "Bergamo", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(9, "Bologna", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(10, "Bolzano", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(11, "Brescia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(12, "Brindisi", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(13, "Cagliari", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(14, "Capri", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(15, "Catania", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(16, "Catanzaro", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(17, "Crotone", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(18, "Cuneo", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(19, "Elba, Isola", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(20, "Fano", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(21, "Firenze", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(22, "Foggia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(23, "Forlì", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(24, "Genova", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(25, "Grosseto", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(26, "Lamezia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(27, "Lecce", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(28, "Milano Linate", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(29, "Milano Malpensa", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(30, "Napoli", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(31, "Olbia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(32, "Padova", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(33, "Palermo", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(34, "Parma", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(35, "Perugia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(36, "Pescara", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(37, "Pisa", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(38, "Ragusa", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(39, "Ravenna", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(40, "Reggio Calabria", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(41, "Reggio Emilia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(42, "Roma Ciampino", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(43, "Roma Fiumicino", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(44, "Salerno", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(45, "Taranto", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(46, "Trento", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(47, "Treviso", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(48, "Trieste", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(49, "Torino", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(50, "Udine", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(51, "Venezia", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(52, "Verona", 0.0, 0.0));
		elencoAeroporti.add(new Aeroporto(53, "Vicenza", 0.0, 0.0));

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
						voli.add(new Volo(idIncr, c.getTime(), i, j, (int) (Math.random()*(100-1)+1), 300, 50, result));
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
