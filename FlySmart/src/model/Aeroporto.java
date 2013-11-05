package model;

import java.lang.reflect.Field;
import java.util.List;

/**Rappresenta un determinato aeroporto, e informazioni circa tasse e prezzo del carburante.
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class Aeroporto extends Model {
	
	/** La costante serialVersionUID. */
	private static final long serialVersionUID = -5654644415727177592L;
	
	/** Il nome dell'aeroporto */
	private String nome;
	
	
	/**
	 * Istanzia un nuovo aeroporto (Supercostruttore)
	 *
	 * @param id Id dell'aeroporto
	 * @param nome Nome dell'aeroporto
	 * @param prezzoCarburante Prezzo del carburante
	 * @param tasse Tasse pagate
	 */
	public Aeroporto(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	/**
	 * Istanzia un nuovo aeroporto
	 */
	public Aeroporto(){
		this(null, "");
	}

	/**
	 * Ottiene il nome dell'aeroporto
	 *
	 * @return Il nome dell'aeroporto
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Set del nome
	 *
	 * @param nome Il nome dell'aeroporto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	/**
	 * Ottiene il serial version UID.
	 *
	 * @return Il serial version UID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	//override del metodo toString
	public String toString() {
		return nome;
	}

	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	//ottiene i campi della classe Aeroporto
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Aeroporto.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
}
