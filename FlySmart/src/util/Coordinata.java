
package util;


/**
 * l'interfaccia gestisce le coordinate di pallet/passeggeri sull'aereo
 * coordinate assolute Zero-based
 */
public interface Coordinata {

	/**
	 * passa da coordinata relativa ad assoluta sulla X
	 * @param x coordinata relativa
	 * @return coordinata assoluta
	 */
	public int XAbs(double x);
	
	/**
	 * passa da coordinata relativa ad assoluta sulla Y
	 * @param Y coordinata relativa
	 * @return coordinata assoluta
	 */
	public int YAbs(double y);
	
	/**
	 * passa da coordinata assoluta a relativa sulla X
	 * @param x coordinata assoluta
	 * @return coordinata relativa
	 */
	public double XRel(int x);
	
	/**
	 * passa da coordinata assoluta a relativa sulla Y
	 * @param x coordinata assoluta
	 * @return coordinata relativa
	 */
	public double YRel(int y);
	
}
