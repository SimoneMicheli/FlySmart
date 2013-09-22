package model;

public class Pallet {
	private int id;
	private double peso;
	private String targa;
	private int idVolo;
	
	public Pallet(Integer id, Double peso, String targa, Integer idVolo) {
		this.id = id;
		this.peso = peso;
		this.targa = targa;
		this.idVolo = idVolo;
	}
	
	public Pallet(double peso, String targa, int idVolo) {
		this(null, peso,  targa,  idVolo);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public int getIdVolo() {
		return idVolo;
	}
	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}
}
