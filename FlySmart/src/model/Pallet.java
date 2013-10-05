package model;

import java.io.Serializable;

public class Pallet implements Serializable{
	private static final long serialVersionUID = -5555020451242189950L;
	private Integer id;
	private Double peso;
	private String targa;
	private Integer idVolo;
	
	public Pallet(Integer id, Double peso, String targa, Integer idVolo) {
		this.id = id;
		this.peso = peso;
		this.targa = targa;
		this.idVolo = idVolo;
	}
	
	public Pallet(Double peso, String targa, Integer idVolo) {
		this(null, peso,  targa,  idVolo);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public Integer getIdVolo() {
		return idVolo;
	}
	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}
}
