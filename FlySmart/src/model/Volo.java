package model;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Rappresenta uno specifico volo che collega due aeroporti in una determinata data.
 * Contiene informazioni circa posti disponibili, pallet disponibili e prezzi del volo.
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class Volo extends Model {
	
	/** La costante serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Data del volo (in particolare la partenza) */
	private Date dataOra;
	
	/** Id dell'aeroporto di partenza */
	private Integer  aeroportoPartenza;
	
	/** Id dell'aeroporto di destinazione */
	private Integer  aeroportoDestinazione;
	
	/** Id dell'aereo assegnato al volo */
	private Integer  aereo;
	
	/** Numero di posti per passeggeri ancora disponibili sull'aereo */
	private Integer  postiDisponibili;
	
	/** Numero di posti per pallet ancora disponibili sull'aereo */
	private Integer  palletDisponibili;
	
	/** Prezzo del volo (espresso in euro) */
	private Double prezzo;
	
	/**
	 * Istanzia un nuovo volo (supercostruttore)
	 *
	 * @param id Id del volo
	 * @param dataOra Data del volo (in particolare la partenza)
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 * @param aereo Id dell'aereo assegnato al volo
	 * @param postiDisponibili Numero di posti per passeggeri ancora disponibili sull'aereo
	 * @param palletDisponibili  Numero di posti per pallet ancora disponibili sull'aereo
	 * @param prezzo Prezzo del volo (espresso in euro)
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
	 * Istanzia un nuovo volo (costruttore vuoto)
	 */
	public Volo(){
		
	}

	/**
	 * Ottiene la Data del volo (in particolare la partenza)
	 *
	 * @return Data del volo (in particolare la partenza)
	 */
	public Date getDataOra() {
		return dataOra;
	}



	/**
	 * Set Data del volo (in particolare la partenza)
	 *
	 * @param dataOra Data del volo (in particolare la partenza)
	 */
	public void setDataoOra(Date dataOra) {
		this.dataOra = dataOra;
	}



	/**
	 * Ottiene il serial version UID
	 *
	 * @return the serial version UID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	/**
	 * Gets Id dell'aeroporto di partenza
	 *
	 * @return Id dell'aeroporto di partenza
	 */
	public Integer getAeroportoPartenza() {
		return aeroportoPartenza;
	}



	/**
	 * Set Id dell'aeroporto di partenza
	 *
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 */
	public void setAeroportoPartenza(Integer aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}



	/**
	 * Ottiene Id dell'aeroporto di destinazione
	 *
	 * @return Id dell'aeroporto di destinazione
	 */
	public Integer getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}



	/**
	 * Set Id dell'aeroporto di destinazione
	 *
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 */
	public void setAeroportoDestinazione(Integer aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}



	/**
	 * Ottiene Id dell'aereo assegnato al volo
	 *
	 * @return Id dell'aereo assegnato al volo
	 */
	public Integer getAereo() {
		return aereo;
	}



	/**
	 * Set Id dell'aereo assegnato al volo
	 *
	 * @param aereo Id dell'aereo assegnato al volo
	 */
	public void setAereo(Integer aereo) {
		this.aereo = aereo;
	}



	/**
	 * Ottiene il Numero di posti per passeggeri ancora disponibili sull'aereo
	 *
	 * @return Numero di posti per passeggeri ancora disponibili sull'aereo
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
	 * Set il Numero di posti per passeggeri ancora disponibili sull'aereo
	 *
	 * @param postiDisponibili Numero di posti per passeggeri ancora disponibili sull'aereo
	 */
	public void setPostiDisponibili(Integer postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}



	/**
	 * Ottiene il Numero di posti per pallet ancora disponibili sull'aereo
	 *
	 * @return Numero di posti per pallet ancora disponibili sull'aereo
	 */
	public Integer getPalletDisponibili() {
		return palletDisponibili;
	}



	/**
	 * Set il Numero di posti per pallet ancora disponibili sull'aereo
	 *
	 * @param palletDisponibili Numero di posti per pallet ancora disponibili sull'aereo
	 */
	public void setPalletDisponibili(Integer palletDisponibili) {
		this.palletDisponibili = palletDisponibili;
	}



	/**
	 * Ottiene il Prezzo del volo (espresso in euro)
	 *
	 * @return Prezzo del volo (espresso in euro)
	 */
	public Double getPrezzo() {
		return prezzo;
	}



	/**
	 * Set il Prezzo del volo (espresso in euro)
	 *
	 * @param prezzo Prezzo del volo (espresso in euro)
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	

	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	//ottiene i campi della classe Volo
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Volo.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}
	

}
