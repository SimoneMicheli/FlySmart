package model;

import java.lang.reflect.Field;
import java.util.List;

public class Aeroporto extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5654644415727177592L;
	private String nome;
	private Double prezzoCarburante;
	private Double tasse;
	
	
	public Aeroporto(Integer id, String nome, Double prezzoCarburante, Double tasse) {
		this.id = id;
		this.nome = nome;
		this.prezzoCarburante = prezzoCarburante;
		this.tasse = tasse;
	}
	
	public Aeroporto(){
		this(null, "", 0.0, 0.0);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPrezzoCarburante() {
		return prezzoCarburante;
	}
	public void setPrezzoCarburante(Double prezzoCarburante) {
		this.prezzoCarburante = prezzoCarburante;
	}
	public Double getTasse() {
		return tasse;
	}
	public void setTasse(Double tasse) {
		this.tasse = tasse;
	}
	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Aeroporto.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
}
