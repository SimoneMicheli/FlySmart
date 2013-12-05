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
	
	/**
	 * Constructor for CoordinataPallet.
	 * @param t TipoAereo
	 */
	public CoordinataPallet(TipoAereo t) {
		this.t = t;
	}
	
	/**
	 * Method XAbs.
	 * @param x double
	 * @return int
	 * @see util.Coordinata#XAbs(double)
	 */
	@Override
	public int XAbs(double x) {
		return (int) (x - 0.5 + t.getColonnePallet()/2);
	}

	/**
	 * Method YAbs.
	 * @param y double
	 * @return int
	 * @see util.Coordinata#YAbs(double)
	 */
	@Override
	public int YAbs(double y) {
		return (int) (y - 0.5 + (t.getFilePallet()/2));
	}

	/**
	 * Method XRel.
	 * @param x int
	 * @return double
	 * @see util.Coordinata#XRel(int)
	 */
	@Override
	public double XRel(int x) {
		return (double) (x + 0.5 - t.getColonnePallet()/2);
	}

	/**
	 * Method YRel.
	 * @param y int
	 * @return double
	 * @see util.Coordinata#YRel(int)
	 */
	@Override
	public double YRel(int y) {
		return (double) (y + 0.5 - t.getFilePallet()/2);
	}

}
