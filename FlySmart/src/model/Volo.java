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
	
	/** Numero di posti prenotati sull'aereo */
	private Integer  postiDisponibili;
	
	/** Numero di posti per pallet prenotati sull'aereo */
	private Integer  palletDisponibili;
	
	/** Prezzo del volo (espresso in euro) */
	private Double prezzo;
	
	/** Stato del volo */
	private StatoVolo stato;
	
	/** Tipo di aereo per il volo*/
	private TipoAereo tipoAereo;
	
	/** ultimi id assegnati */
	private Integer lastID=0, lastPalletID=0, lastGroupID=0;
	
	/**
	 * Istanzia un nuovo volo (supercostruttore) usato solo nella classe XMLToObj
	 *
	 * @param id Id del volo
	 * @param dataOra Data del volo (in particolare la partenza)
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 * @param aereo Id dell'aereo assegnato al volo
	 * @param postiDisponibili Numero di posti per passeggeri ancora disponibili sull'aereo
	 * @param palletDisponibili  Numero di posti per pallet ancora disponibili sull'aereo
	 * @param prezzo Prezzo del volo (espresso in euro)
	 * @param stato stato del volo
	 * @param tipoAereo tipo di aereo usato per il volo
	 * @param lastID ultimo id passeggeri assegnato
	 * @param lastPalletID ultimo id pallet assegnato
	 * @param lastGroupID ultimo id gruppo assegnato
	 */
	public Volo(Integer id, Date dataOra, Integer aeroportoPartenza, Integer aeroportoDestinazione, Integer aereo, Integer postiDisponibili, Integer palletDisponibili, Double prezzo, StatoVolo stato, TipoAereo tipoAereo, Integer lastID, Integer lastPalletID, Integer lastGroupID) {
		this.id = id;
		this.dataOra = dataOra;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.aereo = aereo;
		this.postiDisponibili = postiDisponibili;
		this.palletDisponibili = palletDisponibili;
		this.setPrezzo(prezzo);
		this.stato = stato;
		this.tipoAereo = tipoAereo;
		this.lastID = lastID;
		this.lastPalletID = lastPalletID;
		this.lastGroupID = lastGroupID;
	}
	
	/**
	 * costruttore da utilizzare per creare un volo valido
	 * @param id Id del volo
	 * @param dataOra Data del volo (in particolare la partenza)
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 * @param aereo Id dell'aereo assegnato al volo
	 * @param prezzo Prezzo del volo (espresso in euro)
	 * @param stato stato del volo
	 * @param tipoAereo tipo di aereo usato per il volo
	 */
	public Volo(Integer id, Date dataOra, Integer aeroportoPartenza, Integer aeroportoDestinazione, Integer aereo, Double prezzo, StatoVolo stato, TipoAereo tipoAereo){
		this(id, dataOra, aeroportoPartenza, aeroportoDestinazione, aereo, 0, 0, prezzo, stato, tipoAereo, new Integer(0), new Integer(0), new Integer(0));
		setPostiDisponibili(tipoAereo.getFilePasseggeri() * tipoAereo.getColonnePasseggeri());
		setPalletDisponibili(tipoAereo.getFilePallet() * tipoAereo.getColonnePallet());
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
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy   hh:mm");
		return  format.format(dataOra) + "          " +postiDisponibili+ " posti disponibili         "+palletDisponibili+ " pallet disponibili         " +prezzo+ " �";
	}




	/**
	 * Set il Numero di posti per passeggeri ancora disponibili sull'aereo
	 *
	 * @param postiDisponibili Numero di posti per passeggeri ancora disponibili sull'aereo
	 */
	public synchronized void setPostiDisponibili(Integer postiDisponibili) {
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
	public synchronized void setPalletDisponibili(Integer palletDisponibili) {
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
	
	
	/**
	 * ottiene lo stato del volo
	 * @return stato volo
	 */
	public StatoVolo getStato() {
		return stato;
	}

	/**
	 * Imposta lo stato del volo
	 * @param stato
	 */
	public void setStato(StatoVolo stato) {
		this.stato = stato;
	}

	/**
	 * ritorna il tipo di aereo utilizzato per il volo
	 * @return tipo di aereo
	 */
	public TipoAereo getTipoAereo() {
		return tipoAereo;
	}

	/**
	 * imposta il tipo di aereo usato per il volo
	 * @param tipoAereo
	 */
	public void setTipoAereo(TipoAereo tipoAereo) {
		this.tipoAereo = tipoAereo;
	}
	
	/**
	 * restituisce il primo id passeggero libero e blocca tutti i successivi length id
	 * metodo synchronized perchè condiviso tra tutti i server thread
	 * @param length numero id da bloccare
	 * @return primo id disponibile
	 */
	public synchronized int getNextID(int length){
		int oldID = lastID;
		lastID += length;
		return oldID;
	}
	
	/**
	 * @return il primo id libero da assegnare ad un passeggero
	 */
	public synchronized int getNextID(){
		return getNextID(1);
	}
	
	/**
	 * restituisce il primo id pallet libero e blocca tutti i successivi length id
	 * metodo synchronized perchè condiviso tra tutti i server thread
	 * @param length numero id da bloccare
	 * @return primo id disponibile
	 */
	public synchronized int getNextPalletID(int length){
		int oldID = lastPalletID;
		lastPalletID += length;
		return oldID;
	}

	/**
	 * 
	 * @return primo id libero per i pallet
	 */
	public synchronized int getNextPalletID(){
		return getNextPalletID(1);
	}
	
	/**
	 * 
	 * @return oldId primo id libero per gruppi
	 */
	public synchronized int getNextGroupID(){
		int oldID = lastGroupID;
		lastGroupID ++;
		return oldID;
	}
	
	public synchronized int getGroupNumber(){
		return lastGroupID;
	}

	/**
	 * @return the lastID
	 */
	public Integer getLastID() {
		return lastID;
	}

	/**
	 * @return the lastPalletID
	 */
	public Integer getLastPalletID() {
		return lastPalletID;
	}

	/**
	 * @return the lastGroupID
	 */
	public Integer getLastGroupID() {
		return lastGroupID;
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
