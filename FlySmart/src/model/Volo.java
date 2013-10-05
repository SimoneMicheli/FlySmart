package model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Volo extends Model implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date dataOra;
	private Integer  aeroportoPartenza;
	private Integer  aeroportoDestinazione;
	private Integer  aereo;
	private Integer  postiDisponibili;
	private Integer  palletDisponibili;
	private Double prezzo;
	
	public Volo(Integer id, Date dataOra, Integer aeroportoPartenza, Integer aeroportoDestinazione, Integer aereo, Integer postiDisponibili, Integer palletDisponibili, Double prezzo) {
		this.id = id;
		this.dataOra = dataOra;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.aereo = aereo;
		this.postiDisponibili = postiDisponibili;
		this.palletDisponibili = palletDisponibili;
		this.setPrezzo(prezzo);
		
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



	public Integer getAeroportoPartenza() {
		return aeroportoPartenza;
	}



	public void setAeroportoPartenza(Integer aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}



	public Integer getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}



	public void setAeroportoDestinazione(Integer aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}



	public Integer getAereo() {
		return aereo;
	}



	public void setAereo(Integer aereo) {
		this.aereo = aereo;
	}



	public Integer getPostiDisponibili() {
		return postiDisponibili;
	}



	@Override
	public String toString() {
		return id + "         " + dataOra + "          " +postiDisponibili+ " posti disponibili";
	}




	public void setPostiDisponibili(Integer postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}



	public Integer getPalletDisponibili() {
		return palletDisponibili;
	}



	public void setPalletDisponibili(Integer palletDisponibili) {
		this.palletDisponibili = palletDisponibili;
	}



	public Double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	

	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Volo.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
	

}
