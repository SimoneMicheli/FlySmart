package model;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * @author Demarinis - Micheli - Scarpellini
 * The Class Volo.
 */
public class Volo extends Model {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data ora. */
	private Date dataOra;
	
	/** The aeroporto partenza. */
	private Integer  aeroportoPartenza;
	
	/** The aeroporto destinazione. */
	private Integer  aeroportoDestinazione;
	
	/** The aereo. */
	private Integer  aereo;
	
	/** The posti disponibili. */
	private Integer  postiDisponibili;
	
	/** The pallet disponibili. */
	private Integer  palletDisponibili;
	
	/** The prezzo. */
	private Double prezzo;
	
	/**
	 * Instantiates a new volo.
	 *
	 * @param id the id
	 * @param dataOra the data ora
	 * @param aeroportoPartenza the aeroporto partenza
	 * @param aeroportoDestinazione the aeroporto destinazione
	 * @param aereo the aereo
	 * @param postiDisponibili the posti disponibili
	 * @param palletDisponibili the pallet disponibili
	 * @param prezzo the prezzo
	 */
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
	
	/**
	 * Instantiates a new volo.
	 */
	public Volo(){
		
	}

	/**
	 * Gets the data ora.
	 *
	 * @return the data ora
	 */
	public Date getDataOra() {
		return dataOra;
	}



	/**
	 * Sets the datao ora.
	 *
	 * @param dataOra the new datao ora
	 */
	public void setDataoOra(Date dataOra) {
		this.dataOra = dataOra;
	}



	/**
	 * Gets the serial version uid.
	 *
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	/**
	 * Gets the aeroporto partenza.
	 *
	 * @return the aeroporto partenza
	 */
	public Integer getAeroportoPartenza() {
		return aeroportoPartenza;
	}



	/**
	 * Sets the aeroporto partenza.
	 *
	 * @param aeroportoPartenza the new aeroporto partenza
	 */
	public void setAeroportoPartenza(Integer aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}



	/**
	 * Gets the aeroporto destinazione.
	 *
	 * @return the aeroporto destinazione
	 */
	public Integer getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}



	/**
	 * Sets the aeroporto destinazione.
	 *
	 * @param aeroportoDestinazione the new aeroporto destinazione
	 */
	public void setAeroportoDestinazione(Integer aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}



	/**
	 * Gets the aereo.
	 *
	 * @return the aereo
	 */
	public Integer getAereo() {
		return aereo;
	}



	/**
	 * Sets the aereo.
	 *
	 * @param aereo the new aereo
	 */
	public void setAereo(Integer aereo) {
		this.aereo = aereo;
	}



	/**
	 * Gets the posti disponibili.
	 *
	 * @return the posti disponibili
	 */
	public Integer getPostiDisponibili() {
		return postiDisponibili;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		return  id + " " + format.format(dataOra) + "          " +postiDisponibili+ " posti disponibili";
	}




	/**
	 * Sets the posti disponibili.
	 *
	 * @param postiDisponibili the new posti disponibili
	 */
	public void setPostiDisponibili(Integer postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}



	/**
	 * Gets the pallet disponibili.
	 *
	 * @return the pallet disponibili
	 */
	public Integer getPalletDisponibili() {
		return palletDisponibili;
	}



	/**
	 * Sets the pallet disponibili.
	 *
	 * @param palletDisponibili the new pallet disponibili
	 */
	public void setPalletDisponibili(Integer palletDisponibili) {
		this.palletDisponibili = palletDisponibili;
	}



	/**
	 * Gets the prezzo.
	 *
	 * @return the prezzo
	 */
	public Double getPrezzo() {
		return prezzo;
	}



	/**
	 * Sets the prezzo.
	 *
	 * @param prezzo the new prezzo
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	

	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Volo.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
	

}
