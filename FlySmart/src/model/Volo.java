package model;

import java.io.Serializable;
import java.util.Date;

public class Volo extends Model implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dataOra;
	private int aeroportoPartenza;
	private int aeroportoDestinazione;
	private int aereo;
	private int postiDisponibili;
	private int palletDisponibili;
	
	
	public Volo(int id, Date dataOra, int aeroportoPartenza, int aeroportoDestinazione, int aereo, int postiDisponibili, int palletDisponibili) {
		this.id = id;
		this.dataOra = dataOra;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.aereo = aereo;
		this.postiDisponibili = postiDisponibili;
		this.palletDisponibili = palletDisponibili;
	}


	public Date getDataOra() {
		return dataOra;
	}



	public void setDataoOra(Date dataOra) {
		this.dataOra = dataOra;
	}



	public static long getSerialVersionUID() {
		return serialVersionUID;
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



	@Override
	public String toString() {
		return id + "         " + dataOra + "          " +postiDisponibili+ " posti disponibili";
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
