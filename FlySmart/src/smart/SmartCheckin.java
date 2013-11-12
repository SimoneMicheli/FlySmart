package smart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

import comparator.PalletComparator;
import comparator.VoloComparator;

import model.Coordinata;
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
	private boolean[][] occupancyPasseggeri= {{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false},{false,false,false,false,false,false}};
	private boolean[][] occupancyPallet= {{false,false},{false,false},{false,false},{false,false}};

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
		XMLToObj<Volo> parserXML = new XMLToObj<Volo>(Volo.class);

		//blocca volo
		voliLock.acquireWriteLock();
		voli = parserXML.readObj(Options.voliFileName);

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
		XMLToObj<Pallet> parserXMLPallet = new XMLToObj<Pallet>(Pallet.class);
		pallets = parserXMLPallet.readObj(String.format(Options.voloPalletFileName, idVolo));


		//calcola disposizione


		//posizionePallet(pallets, v);
		//posizionePasseggeri(passeggeri, v);

		//salva risultato su file
		//passLocks.get(idVolo).releaseWriteLock();
		palletLocks.get(idVolo).releaseWriteLock();
	}

	public void posizionaPallet(List<Pallet> lista, Volo v){


		Collections.sort(lista, PalletComparator.PESO_ORDER);

		Iterator<Pallet> i = lista.iterator();

		//ottengo primo pallet
		Pallet p = i.next();

		//posizione pallet in pos default
		p.setFila(Coordinata.YAbs(0.5, v.getTipoAereo()));
		p.setColonna(0);

		double momX = p.getPeso() * -0.5;
		double momY = p.getPeso() * 0.5;

		//setto occupato il primo posto del pallet
		occupancyPallet[Coordinata.YAbs(0.5, v.getTipoAereo())][0] = true;
		System.out.println("momX: "+momX+" momY: "+momY+" peso: "+p.getPeso()+" x: "+p.getColonna()+" y: "+p.getFila());

		//calcolo posizione per i pallet successivi
		while(i.hasNext()){
			p = i.next();

			//posizione ideale per annullare il momento
			double distX = -momX / p.getPeso();
			double distY = -momY / p.getPeso();

			System.out.println("calc: x: "+distX +" y: "+distY);
			//casting posizione (intervallo 1 shift 0.5)
			if(distX <=0 && distX>=-0.5)
				distX = -0.5;
			else
				distX = 0.5 + (int)(distX - 0.5);
			if(distY <=0 && distY>=-0.5)
				distY =-0.5;
			else
				distY = 0.5 + (int)(distY - 0.5);

			System.out.println("ottimo: x: "+distX +" y: "+distY);

			//controllo interno all'aereo
			if(Coordinata.XAbs(distX ,v.getTipoAereo()) > v.getTipoAereo().getColonnePallet() )
				distX = Coordinata.XRel(v.getTipoAereo().getColonnePallet(), v.getTipoAereo());
			if(Coordinata.XAbs(distX ,v.getTipoAereo()) < 0 )
				distX = Coordinata.XRel(0, v.getTipoAereo());
			if(Coordinata.YAbs(distY ,v.getTipoAereo()) > v.getTipoAereo().getFilePallet() )
				distY = Coordinata.YRel(v.getTipoAereo().getFilePallet(), v.getTipoAereo());
			if(Coordinata.YAbs(distY ,v.getTipoAereo()) <0 )
				distY = Coordinata.YRel(0, v.getTipoAereo());
				
			System.out.println("cast: x: "+distX +" y: "+distY);
			System.out.println("abs: x: "+Coordinata.XAbs(distX ,v.getTipoAereo()) +" y: "+Coordinata.YAbs(distY ,v.getTipoAereo()));
			//aggiorno posto effettivo
			int[] pos = postoLiberoPallet(Coordinata.XAbs(distX ,v.getTipoAereo()), Coordinata.YAbs(distY ,v.getTipoAereo()));
			p.setFila(pos[1]);
			p.setColonna(pos[0]);

			//sbiglanciamento effettivo
			momX = momX + p.getPeso() * Coordinata.XRel(pos[0], v.getTipoAereo());
			momY = momY + p.getPeso() * Coordinata.YRel(pos[1], v.getTipoAereo());

			System.out.println("momX: "+momX+" momY: "+momY+" peso: "+p.getPeso()+" col: "+pos[0]+" riga: "+pos[1]);
		}

	} 


	public int[] postoLiberoPasseggeri(int colonnaScelta, int rigaScelta){

		int maxRighe =occupancyPasseggeri.length;
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;

		for(int i=0;i<=distanzaMassima;i++){
			// System.err.println("i:"+i);

			// System.err.println("++++++++++++++++++++++++++");
			int colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				// System.err.println("vicino:"+vicino);
				try{
					if(!occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta+i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta+i, colonnaCalcolata};
						// System.err.println("++++++++++trovato");
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+(vicino+1))%3+((int)colonnaScelta/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaScelta/3)*(-2)+1); //l'ultimo passo sar� il vicino 6 ma il for termina
				// System.err.println("+++++calcolata:"+colonnaCalcolata+" scelta: "+colonnaScelta);
			}


			// System.err.println("------------------------------------------");
			colonnaCalcolata = colonnaScelta;
			for(int vicino=0;vicino<6;vicino++){
				try{

					// System.err.println("matrice nel posto"+(rigaScelta-i)+" "+colonnaCalcolata+": "+occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]);
					if(!occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]){
						occupancyPasseggeri[rigaScelta-i][colonnaCalcolata]=true;
						int[] posto = {rigaScelta-i, colonnaCalcolata};
						// System.err.println("-------trovato"+(rigaScelta-i)+" "+colonnaCalcolata);
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
				}
				colonnaCalcolata=(colonnaScelta+(vicino+1))%3+((int)colonnaScelta/3)*3+((int)(vicino+1)/3)*3*(((int)colonnaScelta/3)*(-2)+1);
				// System.err.println("-----calcolata:"+colonnaCalcolata+" scelta: "+colonnaScelta);
			}
		}

		int[] posto = {-1, -1};
		System.out.println("Posto:"+posto[0]+" "+posto[1]);
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
				int[] posto = {colonnaScelta, rigaScelta};
				return posto;
			}
		}catch(ArrayIndexOutOfBoundsException e){
		}


		for(int i=0;i<=distanzaMassima;i++){

			try{
				//Salgo di i e cambio colonna
				if(!occupancyPallet[rigaScelta-i][1-colonnaScelta]){
					occupancyPallet[rigaScelta-i][1-colonnaScelta]=true;
					int[] posto = {1-colonnaScelta, rigaScelta-i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Salgo di i
				if(!occupancyPallet[rigaScelta+i][colonnaScelta]){
					occupancyPallet[rigaScelta+i][colonnaScelta]=true;
					int[] posto = {colonnaScelta, rigaScelta+i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i
				if(!occupancyPallet[rigaScelta-i][colonnaScelta]){
					occupancyPallet[rigaScelta-i][colonnaScelta]=true;
					int[] posto = {colonnaScelta, rigaScelta-i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}

			try{
				//Scendo di i e cambio colonna
				if(!occupancyPallet[rigaScelta+i][1-colonnaScelta]){
					occupancyPallet[rigaScelta+i][1-colonnaScelta]=true;
					int[] posto = {1-colonnaScelta, rigaScelta+i};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
			}
		}
		int[] posto = {-1, -1};
		return posto;
	}
}
