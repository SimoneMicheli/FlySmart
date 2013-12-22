/**
 * 
 */
package comparator;

import java.util.Comparator;

import model.Passeggero;

/**
 * implementa il comparator tra due passeggeri
 *
 */
public enum PasseggeroComparator implements Comparator<Passeggero> {

	/**
	 * ordinamento alfabetico
	 */
	NAME_ORDER{
		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			//compara per cognome
			int cmp = o1.getCognome().compareTo(o2.getCognome());
			//compara per nome
			if (cmp == 0)
				cmp = o1.getNome().compareTo(o2.getNome());
			// se nome e congome uguali compara per posto
			if(cmp == 0)
				cmp = PostoComparator.POSTO_ORDER.compare(o1, o2);
			return cmp;
		}
	}
}
