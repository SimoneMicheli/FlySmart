package smart;

import java.util.HashMap;

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
	
	public void calcolaCheckin(int idVolo){
		//blocca volo
		
		//ottieni elenco passeggeri e pallet
		
		//calcola disposizione
		
		//salva risultato su file
	}
}
