/**
 * 
 */
package comparator;

import java.util.Comparator;

import model.Volo;

/**
 * gestione ordinamento voli secondo i seguenti criteri
 * ID_ORDER
 * DATA_ORDER
 * POST_ORDER
 * PALLET_ORDER
 *
 */
public enum VoloComparator implements Comparator<Volo> {

	ID_ORDER{
		@Override
		public int compare(Volo o1, Volo o2) {
			return o1.getId() - o2.getId();
		}
	},
	DATA_ORDER{
		@Override
		public int compare(Volo o1, Volo o2) {
			return o1.getDataora().compareTo(o2.getDataora());
		}
	},
	POSTI_ORDER{
		@Override
		public int compare(Volo o1, Volo o2) {
			return o1.getPostiDisponibili() - o1.getPostiDisponibili();
		}
	},
	PALLET_ORDER{
		@Override
		public int compare(Volo o1, Volo o2) {
			return o1.getPalletDisponibili() - o1.getPalletDisponibili();
		}
	}
}
