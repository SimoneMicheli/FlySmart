package model;

/**
 * rappresenta il sesso di un passeggero ed il relativo peso
 * @author simone
 *
 */
public enum Sesso {
	M(80), F(60);
	
	private int peso;
	
	/**
	 * Constructor for Sesso.
	 * @param p int
	 */
	Sesso(int p){
		peso = p;
	}
	
	/**
	 * restituisce il peso standard in base al sesso.
	 * @return int
	 */
	public int getPeso(){
		return peso;
	}
}
