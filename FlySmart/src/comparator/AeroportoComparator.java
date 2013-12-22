package comparator;

import java.util.Comparator;

import model.Aeroporto;


/**Implementa l'intefaccia comparator che permette di ordinare gli aeroporti
 *
 */
public enum AeroportoComparator implements Comparator<Aeroporto> {
	
	/**
	 * ordinamento crescente per id
	 */
	ID_ORDER{
		@Override
		public int compare(Aeroporto o1, Aeroporto o2) {
			return o1.getId() - o2.getId();
		}
	},
	
	/**
	 * ordinamento crescente per nome
	 */
	NAME_ORDER{
		@Override
		public int compare(Aeroporto o1, Aeroporto o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	}
}
