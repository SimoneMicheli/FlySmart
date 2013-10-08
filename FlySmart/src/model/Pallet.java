package model;

import java.lang.reflect.Field;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * @author Demarinis - Micheli - Scarpellini
 * The Class Pallet.
 */
public class Pallet extends Model {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5555020451242189950L;
	
	/** The peso. */
	private Double peso;
	
	/** The targa. */
	private String targa;
	
	/** The id volo. */
	private Integer idVolo;
	
	/**
	 * Instantiates a new pallet.
	 *
	 * @param id the id
	 * @param peso the peso
	 * @param targa the targa
	 * @param idVolo the id volo
	 */
	public Pallet(Integer id, Double peso, String targa, Integer idVolo) {
		this.id = id;
		this.peso = peso;
		this.targa = targa;
		this.idVolo = idVolo;
	}
	
	/**
	 * Instantiates a new pallet.
	 *
	 * @param peso the peso
	 * @param targa the targa
	 * @param idVolo the id volo
	 */
	public Pallet(Double peso, String targa, Integer idVolo) {
		this(null, peso,  targa,  idVolo);
	}
	
	/**
	 * Instantiates a new pallet.
	 */
	public Pallet(){
	}

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Sets the peso.
	 *
	 * @param peso the new peso
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	/**
	 * Gets the targa.
	 *
	 * @return the targa
	 */
	public String getTarga() {
		return targa;
	}
	
	/**
	 * Sets the targa.
	 *
	 * @param targa the new targa
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	/**
	 * Gets the id volo.
	 *
	 * @return the id volo
	 */
	public Integer getIdVolo() {
		return idVolo;
	}
	
	/**
	 * Sets the id volo.
	 *
	 * @param idVolo the new id volo
	 */
	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}
	
	/**
	 * Gets the serial version uid.
	 *
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Pallet.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
}
