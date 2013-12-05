/**
 * 
 */
package model;

import java.util.Collection;
import java.util.LinkedList;

import org.bson.types.ObjectId;

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
	private ObjectId id;
	
	/**
	 * costruttore di default
	 */
	public Gruppo(){
		peso = 0;
		id = null;
	}
	
	/**
	 * costruttore della classe, riceve l'id del gruppo
	 * @param id ObjectId
	 */
	public Gruppo(ObjectId id) {
		super();
		this.id = id;
	}

	/**
	 * costruttore, riceve l'id del gruppo e una collezione di passeggeri
	 * da aggiungere nel gruppo
	 * @param c
	 * @param id ObjectId
	 */
	public Gruppo(ObjectId id, Collection<? extends Passeggero> c) {
		super(c);
		this.id = id;
	}

	/**
	 * Method add.
	 * @param e Passeggero
	 * @return boolean
	 */
	@Override
	public boolean add(Passeggero e) {
		peso += e.getPeso();
		return super.add(e);
	}
	
	/**
	 * restituisce il peso totale del gruppo
	
	 * @return peso totale del gruppo */
	public int getPesoTotale(){
		return peso;
	}

	/**
	
	 * @return restituisce l'id del gruppo */
	public ObjectId getId() {
		return id;
	}

	/**
	 * Method compareTo.
	 * @param o Gruppo
	 * @return int
	 */
	@Override
	public int compareTo(Gruppo o) {
		if (peso == o.getPesoTotale())
			return 0;
		if (peso < o.getPesoTotale())
			return -1;
		return 1;
	}
	
	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "peso: "+peso+ super.toString();
	}
}
