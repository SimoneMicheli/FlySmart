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
public class Pallet extends Model {
	
	/** Il peso del pallet */
	@Indexed
	private Integer peso;
	
	/** La targa del pallet */
	private String targa;
	
	/** Id del volo su cui si trova il pallet */
	@Indexed
	private ObjectId idVolo;
	
	/** Indica la fila in cui posizionare il pallet*/
	private Integer fila;
	
	/** Indica la colonna in cui posizionare il pallet*/
	private Integer colonna;
	
	/**
	 * Istanzia un nuovo pallet (supercostruttore)
	 *
	 * @param id Id del pallet
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
	 * @return Il peso del pallet
	 */
	public Integer getPeso() {
		return peso;
	}
	
	/**
	 * Set del peso del pallet
	 *
	 * @param peso Il peso del pallet
	 */
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	/**
	 * Ottiene la targa del pallet
	 *
	 * @return La targa del pallet
	 */
	public String getTarga() {
		return targa;
	}
	
	/**
	 * Set la targa del pallet
	 *
	 * @param targa La targa del pallet
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	/**
	 * Ottiene l'id del volo su cui si trova il pallet
	 *
	 * @return L'id del volo su cui si trova il pallet
	 */
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

	
	/** Restituisce la fila in cui posizionare del pallet*/
	public Integer getFila() {
		return fila;
	}

	/** Setta la fila in cui posizionare del pallet*/
	public void setFila(Integer fila) {
		this.fila = fila;
	}

	/** Restituisce la colonna in cui posizionare il pallet*/
	public Integer getColonna() {
		return colonna;
	}

	/** Setta la colonna in cui posizionare il pallet*/
	public void setColonna(Integer colonna) {
		this.colonna = colonna;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: "+id+ " peso: "+peso+" targa: "+targa+" idVolo: "+idVolo;
	}
	
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
