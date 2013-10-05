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
	private double prezzoCarburante;
	private double tasse;
	
	
	public Aeroporto(int id, String nome, double prezzoCarburante, double tasse) {
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
	public double getPrezzoCarburante() {
		return prezzoCarburante;
	}
	public void setPrezzoCarburante(double prezzoCarburante) {
		this.prezzoCarburante = prezzoCarburante;
	}
	public double getTasse() {
		return tasse;
	}
	public void setTasse(double tasse) {
		this.tasse = tasse;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

}
