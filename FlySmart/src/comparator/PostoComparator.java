package comparator;

import java.util.Comparator;

import model.Posto;

/**
 * compara i posti assegnati
 *
 */
public enum PostoComparator implements Comparator<Posto> {
	/**
	 * ordinamento posti dalla testa dell''aereo alla coda e da sinistra verso destra
	 */
	POSTO_ORDER{
		@Override
		public int compare(Posto o1, Posto o2) {
			int cmp = o1.getFila().compareTo(o2.getFila());
			//i passeggeri si trovano su righe diverse
			if(cmp != 0)
				return cmp;
			//se i passeggeri sono sulla stessa riga
			return o1.getColonna().compareTo(o2.getColonna());
		}
	}
}
