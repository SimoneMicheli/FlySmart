package model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Demarinis - Micheli - Scarpellini
 * La classe pallet
 */
public class Pallet extends Model {
	
	/** La costante serialVersionUID. */
	private static final long serialVersionUID = -5555020451242189950L;
	
	/** Il peso del pallet */
	private Double peso;
	
	/** La targa del pallet */
	private String targa;
	
	/** Id del volo su cui si trova il pallet */
	private Integer idVolo;
	
	/**
	 * Istanzia un nuovo pallet (supercostruttore)
	 *
	 * @param id Id del pallet
	 * @param peso Il peso del pallet
	 * @param targa La targa del pallet
	 * @param idVolo Id del volo su cui si trova il pallet
	 */
	public Pallet(Integer id, Double peso, String targa, Integer idVolo) {
		this.id = id;
		this.peso = peso;
		this.targa = targa;
		this.idVolo = idVolo;
	}
	
	/**
	 * Istanzia un nuovo pallet
	 *
	 * @param peso Il peso del pallet
	 * @param targa la targa del pallet
	 * @param idVolo Id del volo su cui si trova il pallet
	 */
	public Pallet(Double peso, String targa, Integer idVolo) {
		this(null, peso,  targa,  idVolo);
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
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Set del peso del pallet
	 *
	 * @param peso Il peso del pallet
	 */
	public void setPeso(Double peso) {
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
	 * @param La targa del pallet
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	/**
	 * Ottiene l'id del volo su cui si trova il pallet
	 *
	 * @return L'id del volo su cui si trova il pallet
	 */
	public Integer getIdVolo() {
		return idVolo;
	}
	
	/**
	 * Set id del volo su cui si trova il pallet
	 *
	 * @param idVolo Id del volo su cui si trova il pallet
	 */
	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}
	
	/**
	 * Ottiene il serial version UID
	 *
	 * @return Il serial version UID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	// ottiene la lista dei campi della classe pallet
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Pallet.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
}
