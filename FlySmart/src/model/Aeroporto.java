package model;

import java.io.Serializable;

public class Aeroporto extends Model implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5654644415727177592L;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private String nome;
	private Double prezzoCarburante;
	private Double tasse;
	
	
	public Aeroporto(Integer id, String nome, Double prezzoCarburante, Double tasse) {
		this.id = id;
		this.nome = nome;
		this.prezzoCarburante = prezzoCarburante;
		this.tasse = tasse;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

}
