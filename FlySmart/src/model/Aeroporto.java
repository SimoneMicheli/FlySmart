package model;

import java.io.Serializable;

public class Aeroporto implements Serializable{
	
	private static final long serialVersionUID = -5578527938970594682L;
	private int id;
	private String citta;
	private double prezzoCarburante;
	private double tasse;
	
	
	public Aeroporto(int id, String citta, double prezzoCarburante, double tasse) {
		this.id = id;
		this.citta = citta;
		this.prezzoCarburante = prezzoCarburante;
		this.tasse = tasse;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
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
		return id +" "+citta+ " "+prezzoCarburante+ " "+tasse;
	}
}
