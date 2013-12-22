
package util;


/**
 * l'interfaccia gestisce le coordinate di pallet/passeggeri sull'aereo
 * coordinate assolute Zero-based
 */
public interface Coordinata {

	/**
	 * passa da coordinata relativa ad assoluta sulla X
	 * @param x coordinata relativa
	
	 * @return coordinata assoluta */
	public abstract int XAbs(double x);
	
	/**
	 * passa da coordinata relativa ad assoluta sulla Y
	
	
	 * @param y coordinata relativa
	 * @return coordinata assoluta */
	public abstract int YAbs(double y);
	
	/**
	 * passa da coordinata assoluta a relativa sulla X
	 * @param x coordinata assoluta
	
	 * @return coordinata relativa */
	public abstract double XRel(int x);
	
	/**
	 * passa da coordinata assoluta a relativa sulla Y
	
	
	 * @param y int
	 * @return coordinata relativa */
	public abstract double YRel(int y);
	
}
