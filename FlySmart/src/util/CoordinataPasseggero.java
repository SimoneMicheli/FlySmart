package util;

import model.TipoAereo;

/**
 * gestisce la conversione delle coordinate del passegger oda assolute a relative
 * coordinate assolute Zero-based
 *
 */
public class CoordinataPasseggero implements Coordinata{

	TipoAereo t = null;
	
	/**
	 * Constructor for CoordinataPasseggero.
	 * @param t TipoAereo
	 */
	public CoordinataPasseggero(TipoAereo t) {
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
		return (int) (x + (t.getColonnePasseggeri() / 2) - 0.5);
	}

	/**
	 * Method YAbs.
	 * @param y double
	 * @return int
	 * @see util.Coordinata#YAbs(double)
	 */
	@Override
	public int YAbs(double y) {
		return (int) ((t.getFilePasseggeri() / 2) + y - 0.5);
	}

	/**
	 * Method XRel.
	 * @param x int
	 * @return double
	 * @see util.Coordinata#XRel(int)
	 */
	@Override
	public double XRel(int x) {
		return -(t.getColonnePasseggeri() / 2) + x + 0.5;
	}

	/**
	 * Method YRel.
	 * @param y int
	 * @return double
	 * @see util.Coordinata#YRel(int)
	 */
	@Override
	public double YRel(int y) {
		return -(t.getFilePasseggeri() / 2) + y + 0.5;
	}

}
