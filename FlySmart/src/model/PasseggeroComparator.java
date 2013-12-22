/**
 * 
 */
package model;

import java.util.Comparator;

/**
 * @author simone
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
	
	POSTO_ORDER{
		@Override
		public int compare(Passeggero o1, Passeggero o2) {
			int cmp = o1.getFila().compareTo(o2.getFila());
			//i passeggeri si trovano su righe diverse
			if(cmp != 0)
				return cmp;
			//se i passeggeri sono sulla stessa riga
			return o1.getColonna().compareTo(o2.getColonna());
		}
	}

}
