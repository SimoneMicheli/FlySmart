/**
 * 
 */
package comparator;

import java.util.Comparator;

import model.Passeggero;

/**Gestione ordinamento passeggeri
 * NAME_ORDER: ordinamento crescente per nome
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
	PESO_ORDER{

		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			int p1 = o1.getPeso();
			int p2 = o2.getPeso();
			if(p1<p2)
				return -1;
			if(p1==p2)
				return 0;
			return 1;
		}
		
	}
}
