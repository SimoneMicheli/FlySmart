/**
 * 
 */
package model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author simone
 *
 */
@SuppressWarnings("serial")
public class Gruppo extends LinkedList<Passeggero> implements Comparable<Gruppo> {

	/** Peso totale del gruppo */
	private int peso;
	
	/** id del gruppo */
	private int id;
	
	/**
	 * 
	 */
	public Gruppo(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param c
	 */
	public Gruppo(int id, Collection<? extends Passeggero> c) {
		super(c);
		this.id = id;
	}

	@Override
	public boolean add(Passeggero e) {
		peso += e.getPeso();
		return super.add(e);
	}
	
	/**
	 * restituisce il peso totale del gruppo
	 * @return
	 */
	public int getPesoTotale(){
		return peso;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Gruppo o) {
		if (peso == o.getPesoTotale())
			return 0;
		if (peso < o.getPesoTotale())
			return -1;
		return 1;
	}
}
