package util;

import model.TipoAereo;

/**
 * gestisce la conversione delle coordinate del passegger oda assolute a relative
 * coordinate assolute Zero-based
 *
 */
public class CoordinataPasseggero implements Coordinata{

	TipoAereo t = null;
	
	public CoordinataPasseggero(TipoAereo t) {
		this.t = t;
	}
	
	@Override
	public int XAbs(double x) {
		return (int) (x + (t.getColonnePasseggeri() / 2) - 0.5);
	}

	@Override
	public int YAbs(double y) {
		return (int) ((t.getFilePasseggeri() / 2) - y - 0.5);
	}

	@Override
	public double XRel(int x) {
		return -(t.getColonnePasseggeri() / 2) + x + 0.5;
	}

	@Override
	public double YRel(int y) {
		return (t.getFilePasseggeri() / 2) - y - 0.5;
	}

}
