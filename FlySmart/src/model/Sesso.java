package model;

/**
 * rappresenta il sesso di un passeggero ed il relativo peso
 * @author simone
 *
 */
public enum Sesso {
	M(80), F(60);
	
	private int peso;
	
	Sesso(int p){
		peso = p;
	}
	
	public int getPeso(){
		return peso;
	}
}
