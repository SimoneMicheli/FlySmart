/**
 * 
 */
package checkin;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

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
	
	
	 * @return lista ordinate per peso dei gruppi */
	public static Gruppo[] sortGroup(List<Passeggero> lista){
		
		//creo mappa hash contentente id gruppo e gruppo, dimensione iniziale pari al numero di passeggeri
		//da analizzare (caso peggiore per migliorare le prestaioni)
		HashMap<ObjectId, Gruppo> map = new HashMap<ObjectId, Gruppo>(lista.size(), 1f);
		
		Iterator<Passeggero> i = lista.iterator();
		
		//inserisco passeggeri nei rispettivi gruppi con complessit�� n (simile ad ordinamento lineare)
		while (i.hasNext()) {
			Passeggero passeggero = (Passeggero) i.next();
			ObjectId groupId = passeggero.getIdGruppo();
			//controllo se il gruppo esiste già nella lista
			Gruppo g = map.get(groupId);
			//se non esiste creo un nuovo gruppo
			if(g == null){
				g = new Gruppo(groupId);
				map.put(groupId, g);
			}
			//inserisco il passeggero nella lista di quel gruppo
			g.add(passeggero);
		}
		
		//ordino gruppi per peso (merge sort)
		Gruppo[] gruppi = map.values().toArray(new Gruppo[0]);
		Arrays.sort(gruppi, Collections.reverseOrder());
		
		return gruppi;
	}
}
