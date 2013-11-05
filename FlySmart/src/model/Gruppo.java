/**
 * 
 */
package model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * la classe implementa la gestione dei gruppi, in particolare permette di aggiungere i passeggeri
 * che si trovano nello stesso gruppo
 *
 */
@SuppressWarnings("serial")
public class Gruppo extends LinkedList<Passeggero> implements Comparable<Gruppo> {

	/** Peso totale del gruppo */
	private int peso;
	
	/** id del gruppo */
	private int id;
	
	/**
	 * costruttore della classe, riceve l'id del gruppo
	 */
	public Gruppo(int id) {
		super();
		this.id = id;
	}

	/**
	 * costruttore, riceve l'id del gruppo e una collezione di passeggeri
	 * da aggiungere nel gruppo
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
	 * @return peso totale del gruppo
	 */
	public int getPesoTotale(){
		return peso;
	}

	/**
	 * @return restituisce l'id del gruppo
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
