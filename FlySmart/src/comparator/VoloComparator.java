/**
 * 
 */
package comparator;

import java.util.Comparator;

import model.Volo;

/** Gestione ordinamento voli secondo i seguenti criteri:
 * DATA_ORDER,
 * POSTI_ORDER,
 * PALLET_ORDER
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public enum VoloComparator implements Comparator<Volo> {

	DATA_ORDER{
		@Override
		public int compare(Volo o1, Volo o2) {
			return o1.getDataOra().compareTo(o2.getDataOra());
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
