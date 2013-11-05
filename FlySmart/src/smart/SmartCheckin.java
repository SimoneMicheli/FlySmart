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
	
	/**
	 * crea l'oggetto che implementa l'algoritmo per il calcolo
	 * dell'assegnazione dei posti e dei pallet
	 * @param aeroportiLock
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
		posizionePallet(pallets, v);
		posizionePasseggeri(passeggeri, v);
		
		
		//salva risultato su file
		//passLocks.get(idVolo).releaseWriteLock();
		palletLocks.get(idVolo).releaseWriteLock();
	}
	
	private void posizionePallet(List<Pallet> pallets, Volo volo){
		
	}
	
	private void posizionePasseggeri(List<Passeggero> passeggeri, Volo volo){
		
	}
}
