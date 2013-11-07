package smart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import comparator.VoloComparator;

import model.Pallet;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;

import util.Options;
import xml.XMLCreate;
import xml.XMLToObj;

import exception.FlightNotFoundException;
import fileLock.FileLock;

/**
 *
 */
public class SmartCheckin {
	private FileLock voliLock;
	private HashMap<Integer, FileLock> passLocks, palletLocks;
	private boolean[][] occupancyPasseggeri= {{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false}};
	private boolean[][] occupancyPallet= {{false,false},{false,false},{false,false},{false,false},{false,false},{false,false},{false,false},{false,false}};

	/**
	 * crea l'oggetto che implementa l'algoritmo per il calcolo
	 * dell'assegnazione dei posti e dei pallet
	 * @param voliLock
	 * @param passLocks
	 * @param palletLocks
	 */
	public SmartCheckin(FileLock voliLock,
			HashMap<Integer, FileLock> passLocks,
			HashMap<Integer, FileLock> palletLocks) {
		this.voliLock = voliLock;
		this.passLocks = passLocks;
		this.palletLocks = palletLocks;
		
	}

	public void stampaOccupancyPasseggeri(int x){
		System.out.println("\n\nPasso "+x);
		for(int i=0; i<occupancyPasseggeri.length; i=i+1) {
			System.out.println("");
			for(int j=0; j<occupancyPasseggeri[0].length; j=j+1) {
				System.out.print(occupancyPasseggeri[i][j] + " ");
				if(occupancyPasseggeri[i][j]) System.out.print(" ");
			}
		}
	}
	
	public void stampaOccupancyPallet(int x){
		System.out.println("\n\nPasso "+x);
		for(int i=0; i<occupancyPallet.length; i=i+1) {
			System.out.println("");
			for(int j=0; j<occupancyPallet[0].length; j=j+1) {
				System.out.print(occupancyPallet[i][j] + " ");
				if(occupancyPallet[i][j]) System.out.print(" ");
			}
		}
	}
	
	
	public void calcolaCheckin(int idVolo) throws FlightNotFoundException, IOException{
		List<Volo> voli = new LinkedList<Volo>();
		List<Passeggero> passeggeri = new ArrayList<Passeggero>();
		List<Pallet> pallets = new ArrayList<Pallet>();
		XMLToObj parserXML = new XMLToObj();

		//blocca volo
		voliLock.acquireWriteLock();
		voli = parserXML.createVoloList(Options.voliFileName);

		Collections.sort(voli, VoloComparator.ID_ORDER);
		int pos = Collections.binarySearch(voli,new Integer(idVolo));
		if (pos < 0)
			throw new FlightNotFoundException(idVolo); //volo non trovato

		Volo v = voli.get(pos);

		v.setStato(StatoVolo.CLOSED);
		//salva volo aggiornato
		XMLCreate<Volo> XMLVoloWriter = new XMLCreate<Volo>();
		Document VoliDocument = XMLVoloWriter.createFlySmartDocument(voli);
		try {
			XMLVoloWriter.printDocument(VoliDocument, Options.voliFileName);
		} finally {
			voliLock.releaseWriteLock();
		}

		//ottieni elenco passeggeri e pallet
		//passLocks.get(idVolo).acquireWriteLock();
		palletLocks.get(idVolo).acquireWriteLock();

		//passeggeri = parserXML.createPasseggeroList( String.format(Options.voloPassFileName, idVolo));
		pallets = parserXML.createPalletList(String.format(Options.voloPalletFileName, idVolo));


		//calcola disposizione


		//posizionePallet(pallets, v);
		//posizionePasseggeri(passeggeri, v);
		
		//salva risultato su file
		//passLocks.get(idVolo).releaseWriteLock();
		palletLocks.get(idVolo).releaseWriteLock();
	}

	public int[] postoLiberoPasseggeri(int colonnaScelta, int rigaScelta){

		int maxRighe =occupancyPasseggeri.length;
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;

		for(int i=0;i<=distanzaMassima;i++){
			int colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				try{
					if(!occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta+i, colonnaCalcolata};
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+(vicino+1))%3+((int)colonnaScelta/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaScelta/3)*(-2)+1);
			}



			colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				try{
					if(!occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta-i, colonnaCalcolata};
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+vicino)%3+((int)colonnaScelta/3)*3+((int)vicino/3)*3*(((int)colonnaScelta/3)*(-2)+1); //formulazza
			}
		}

		int[] posto = {-1, -1};
		return posto;
	}

	public int[] postoLiberoPallet(int colonnaScelta, int rigaScelta){
		int maxRighe =occupancyPallet.length;
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;
		
		//provo lo scelto
		try{
			if(!occupancyPallet[rigaScelta][colonnaScelta]){
				occupancyPallet[rigaScelta][colonnaScelta]=true;
				int[] posto = {rigaScelta, colonnaScelta};
				return posto;
			}
		}catch(ArrayIndexOutOfBoundsException e){
		}
		
		
		for(int i=0;i<=distanzaMassima;i++){

			try{
				//Salgo di i e cambio colonna
				if(!occupancyPallet[rigaScelta-i][1-colonnaScelta]){
					occupancyPallet[rigaScelta-i][1-colonnaScelta]=true;
					int[] posto = {rigaScelta-i, 1-colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Salgo di i
				if(!occupancyPallet[rigaScelta+i][colonnaScelta]){
					occupancyPallet[rigaScelta+i][colonnaScelta]=true;
					int[] posto = {rigaScelta+i, colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i
				if(!occupancyPallet[rigaScelta-i][colonnaScelta]){
					occupancyPallet[rigaScelta-i][colonnaScelta]=true;
					int[] posto = {rigaScelta-i, colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i e cambio colonna
				if(!occupancyPallet[rigaScelta+i][1-colonnaScelta]){
					occupancyPallet[rigaScelta+i][1-colonnaScelta]=true;
					int[] posto = {rigaScelta+i, 1-colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}
		}
		int[] posto = {-1, -1};
		return posto;
	}
}
