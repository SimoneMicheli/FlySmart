package model;

import java.sql.Date;

public class Volo {
	private int id;
	private Date dataora;
	private int aeroportoPartenza;
	private int aeroportoDestinazione;
	private int aereo;
	private int postiDisponibili;
	private int palletDisponibili;
	
	
	
	public Volo(int id, Date dataora, int aeroportoPartenza, int aeroportoDestinazione, int aereo, int postiDisponibili, int palletDisponibili) {
		this.id = id;
		this.dataora = dataora;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.aereo = aereo;
		this.postiDisponibili = postiDisponibili;
		this.palletDisponibili = palletDisponibili;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDataora() {
		return dataora;
	}



	public void setDataora(Date dataora) {
		this.dataora = dataora;
	}



	public int getAeroportoPartenza() {
		return aeroportoPartenza;
	}



	public void setAeroportoPartenza(int aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}



	public int getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}



	public void setAeroportoDestinazione(int aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}



	public int getAereo() {
		return aereo;
	}



	public void setAereo(int aereo) {
		this.aereo = aereo;
	}



	public int getPostiDisponibili() {
		return postiDisponibili;
	}



	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}



	public int getPalletDisponibili() {
		return palletDisponibili;
	}



	public void setPalletDisponibili(int palletDisponibili) {
		this.palletDisponibili = palletDisponibili;
	}
	
	

}
