/**
 * 
 */
package model;

/**
 * la classe gestisce le coordinate di pallet/passeggeri sull'aereo
 *
 */
public final class Coordinata {

	public static int XAbs(double x ,TipoAereo t){
		return (int) (x - 0.5 + t.getColonnePallet()/2);
	}
	
	public static int YAbs(double y ,TipoAereo t){
		return (int) (y - 0.5 + t.getFilePallet()/2);
		
	}
	
	public static double XRel(int x ,TipoAereo t){
		return (double) (x + 0.5 - t.getColonnePallet()/2);
	}
	
	public static double YRel(int y ,TipoAereo t){
		return (double) (y + 0.5 - t.getFilePallet()/2);
	}
	
}
