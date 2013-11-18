/**
 * 
 */
package util;

import model.TipoAereo;

/**
 * gestisce la converisione delle coordinate per i pallet
 * coordinate assolute Zero-based
 *
 */
public class CoordinataPallet implements Coordinata{
	
	TipoAereo t = null;
	
	public CoordinataPallet(TipoAereo t) {
		this.t = t;
	}
	
	@Override
	public int XAbs(double x) {
		return (int) (x - 0.5 + t.getColonnePallet()/2);
	}

	@Override
	public int YAbs(double y) {
		return (int) (-y - 0.5 + (t.getFilePallet()/2));
	}

	@Override
	public double XRel(int x) {
		return (double) (x + 0.5 - t.getColonnePallet()/2);
	}

	@Override
	public double YRel(int y) {
		return (double) (-y - 0.5 + t.getFilePallet()/2);
	}

}
