package model;

import java.lang.reflect.Field;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * @author Demarinis - Micheli - Scarpellini
 * The Class Aeroporto.
 */
public class Aeroporto extends Model {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5654644415727177592L;
	
	/** The nome. */
	private String nome;
	
	/** The prezzo carburante. */
	private Double prezzoCarburante;
	
	/** The tasse. */
	private Double tasse;
	
	
	/**
	 * Instantiates a new aeroporto.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param prezzoCarburante the prezzo carburante
	 * @param tasse the tasse
	 */
	public Aeroporto(Integer id, String nome, Double prezzoCarburante, Double tasse) {
		this.id = id;
		this.nome = nome;
		this.prezzoCarburante = prezzoCarburante;
		this.tasse = tasse;
	}
	
	/**
	 * Instantiates a new aeroporto.
	 */
	public Aeroporto(){
		this(null, "", 0.0, 0.0);
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the prezzo carburante.
	 *
	 * @return the prezzo carburante
	 */
	public Double getPrezzoCarburante() {
		return prezzoCarburante;
	}
	
	/**
	 * Sets the prezzo carburante.
	 *
	 * @param prezzoCarburante the new prezzo carburante
	 */
	public void setPrezzoCarburante(Double prezzoCarburante) {
		this.prezzoCarburante = prezzoCarburante;
	}
	
	/**
	 * Gets the tasse.
	 *
	 * @return the tasse
	 */
	public Double getTasse() {
		return tasse;
	}
	
	/**
	 * Sets the tasse.
	 *
	 * @param tasse the new tasse
	 */
	public void setTasse(Double tasse) {
		this.tasse = tasse;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Aeroporto.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
}
