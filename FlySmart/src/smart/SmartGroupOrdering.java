/**
 * 
 */
package smart;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.Gruppo;
import model.Passeggero;

/**
 * crea i gruppi di passeggeri e li ordina in modo efficiente per peso
 *
 */
public class SmartGroupOrdering {
	
	/**
	 * crea ed ordina i gruppi sulla base dei passeggeri
	 * @param lista passeggeri da ordinare
	 * @param numeroGruppi numero di gruppi da creare (dipende dal numero di prenotazioni)
	 * @return lista ordinate per peso dei gruppi
	 */
	public Gruppo[] sortGroup(List<Passeggero> lista, int numeroGruppi){
		
		Gruppo gruppi[] = new Gruppo[numeroGruppi];
		
		//creo gruppi
		for(int l=0; l<numeroGruppi; l++){
			gruppi[l] = new Gruppo(l);
 		}
		
		Iterator<Passeggero> i = lista.iterator();
		
		//inserisco passeggeri nei rispettivi gruppi con complessità n (simile ad ordinamento lineare)
		while (i.hasNext()) {
			Passeggero passeggero = (Passeggero) i.next();
			int groupId = passeggero.getIdGruppo();
			gruppi[groupId].add(passeggero);
		}
		
		//ordino gruppi per peso (merge sort)
		Arrays.sort(gruppi);
		
		return gruppi;
	}
}
