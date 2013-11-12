package comparator;

import java.util.Comparator;

import model.Pallet;

public enum PalletComparator implements Comparator<Pallet> {

	PESO_ORDER{
		@Override
		public int compare(Pallet o1, Pallet o2) {
			return o1.getPeso().compareTo(o2.getPeso());
		}
	}
}
