package model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**Rappresenta un determinato aeroporto, e informazioni circa tasse e prezzo del carburante.
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
@SuppressWarnings("serial")
public class Aeroporto implements GetFields, Serializable{
	
	/**id Aeroporto*/
	private Integer id;
	
	/** Il nome dell'aeroporto */
	private String nome;
	
	
	/**
	 * Istanzia un nuovo aeroporto (Supercostruttore)
	 *
	 * @param id Id dell'aeroporto
	 * @param nome Nome dell'aeroporto
	 */
	public Aeroporto(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public List<Field> getFields() {
		Field[] currentFields = Aeroporto.class.getDeclaredFields();
		List<Field> fields = new LinkedList<Field>();
		for (Field f : currentFields){
				fields.add(f);
		}
		return fields;
	}
}
