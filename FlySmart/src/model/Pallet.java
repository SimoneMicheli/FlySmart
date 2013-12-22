package model;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
 
/**
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Pallet extends Posto{
	
	/** Il peso del pallet */
	@Indexed
	private Integer peso;
	
	/** La targa del pallet */
	private String targa;
	
	/** Id del volo su cui si trova il pallet */
	@Indexed
	private ObjectId idVolo;
	
	/**
	 * Istanzia un nuovo pallet (supercostruttore)
	 *
	
	 * @param peso Il peso del pallet
	 * @param targa La targa del pallet
	 * @param idVolo Id del volo su cui si trova il pallet
	 * @param fila
	 * @param colonna 
	 */
	public Pallet(Integer peso, String targa, ObjectId idVolo, Integer fila, Integer colonna) {
		this.peso = peso;
		this.targa = targa;
		this.idVolo = idVolo;
		this.fila = fila;
		this.colonna = colonna;
	}
	
	/**
	 * Istanzia un nuovo pallet
	 *
	 * @param peso Il peso del pallet
	 * @param targa la targa del pallet
	 * @param idVolo Id del volo su cui si trova il pallet
	 */
	public Pallet(Integer peso, String targa, ObjectId idVolo) {
		this(peso,  targa,  idVolo, null, null);
	}
	
	/**
	 * Istanzia un nuovo pallet (costruttore senza parametri)
	 */
	public Pallet(){
	}

	/**
	 * Ottiene peso
	 *
	
	 * @return Il peso del pallet */
	public Integer getPeso() {
		return peso;
	}
	
	/**
	 * Ottiene la targa del pallet
	 *
	
	 * @return La targa del pallet */
	public String getTarga() {
		return targa;
	}
	
	/**
	 * Ottiene l'id del volo su cui si trova il pallet
	 *
	
	 * @return L'id del volo su cui si trova il pallet */
	public ObjectId getIdVolo() {
		return idVolo;
	}
	
	/**
	 * Set id del volo su cui si trova il pallet
	 *
	 * @param idVolo Id del volo su cui si trova il pallet
	 */
	public void setIdVolo(ObjectId idVolo) {
		this.idVolo = idVolo;
	}
	
	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: "+id+ " peso: "+peso+" targa: "+targa+" idVolo: "+idVolo+" fila: "+fila+" colonna: "+colonna;
	}
	
	/**
	 * Method equals.
	 * @param obj Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Pallet))
			return false;
		Pallet p = (Pallet) obj;
		if(p.id.equals(this.id) && p.peso.equals(this.peso) && p.targa.equals(this.targa))
			return true;
		return false;
	}
}
