/**
 * 
 */
package comparator;

import java.util.Comparator;

import model.Passeggero;

/**Gestione ordinamento passeggeri
 * ID_ORDER: ordinamento crescente per id
 * NAME_ORDER: ordinamento crescente per nome
 * POSTO_ORDER: ordinamento per posto cresente
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public enum PasseggeroComparator implements Comparator<Passeggero> {
	NAME_ORDER{
		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			int cmp = o1.getCognome().compareTo(o2.getCognome());
			if (cmp == 0)
				cmp = o1.getNome().compareTo(o2.getNome());
			return cmp;
			
		}
	},
	ID_ORDER{
		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			return o1.getId() - o2.getId();
		}
	},
	POSTO_ORDER{
		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			return o1.getPosto() - o2.getPosto();
		}
	}
}
