package model;

import java.util.Comparator;


/**Implementa l'intefaccia comparator che permette di ordinare gli aeroporti secondo i seguenti criteri
 * ID_ORDER: ordinamento crescente per id
 * NAME_ORDER: ordinamento crescente per nome
 * 
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public enum AeroportoComparator implements Comparator<Aeroporto> {
	
	
	ID_ORDER{
		@Override
		public int compare(Aeroporto o1, Aeroporto o2) {
			return o1.getId() - o2.getId();
		}
	},
	
	NAME_ORDER{
		@Override
		public int compare(Aeroporto o1, Aeroporto o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	}
}
