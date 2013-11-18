package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;

/**
 * Rappresenta uno specifico volo che collega due aeroporti in una determinata data.
 * Contiene informazioni circa posti disponibili, pallet disponibili e prezzi del volo.
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Volo extends Model{
	
	/** Data del volo (in particolare la partenza) */
	@Indexed
	private Date dataOra;
	
	/** Id dell'aeroporto di partenza */
	@Indexed
	private Integer  aeroportoPartenza;
	
	/** Id dell'aeroporto di destinazione */
	@Indexed
	private Integer  aeroportoDestinazione;
	
	/** Numero di posti prenotati sull'aereo */
	private Integer  postiDisponibili;
	
	/** Numero di posti per pallet prenotati sull'aereo */
	private Integer  palletDisponibili;
	
	/** Prezzo del volo (espresso in euro) */
	private Double prezzoPasseggero;
	
	/** Prezzo del volo per pallet(in euro) */
	private Double prezzoPallet;
	
	/** Stato del volo */
	@Embedded
	private StatoVolo stato;
	
	/** Tipo di aereo per il volo*/
	@Embedded
	private TipoAereo tipoAereo;
	
	/**elenco dei passeggeri sul volo*/
	@Reference
	private List<Passeggero> passeggeri;
	
	/**elenco dei pallet sul volo */
	@Reference
	private List<Pallet> pallet;
	
	/**
	 * Istanzia un nuovo volo (supercostruttore) usato solo nella classe XMLToObj
	 *
	 * @param dataOra Data del volo (in particolare la partenza)
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 * @param postiDisponibili Numero di posti per passeggeri ancora disponibili sull'aereo
	 * @param palletDisponibili  Numero di posti per pallet ancora disponibili sull'aereo
	 * @param prezzoPasseggero Prezzo del volo per ogni passeggero (espresso in euro)
	 * @param prezzoPallet Prezzo del volo per ogni pallet (espresso in euro)
	 * @param stato stato del volo
	 * @param tipoAereo tipo di aereo usato per il volo
	 * @param lastID ultimo id passeggeri assegnato
	 * @param lastPalletID ultimo id pallet assegnato
	 * @param lastGroupID ultimo id gruppo assegnato
	 */
	public Volo(Date dataOra, Integer aeroportoPartenza, Integer aeroportoDestinazione, Integer postiDisponibili, Integer palletDisponibili, Double prezzoPasseggero, Double prezzoPallet, StatoVolo stato, TipoAereo tipoAereo) {
		this.dataOra = dataOra;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.postiDisponibili = postiDisponibili;
		this.palletDisponibili = palletDisponibili;
		this.prezzoPasseggero = prezzoPasseggero;
		this.prezzoPallet = prezzoPallet;
		this.stato = stato;
		this.tipoAereo = tipoAereo;
		passeggeri = new LinkedList<Passeggero>();
		pallet = new LinkedList<Pallet>();
	}
	
	/**
	 * costruttore da utilizzare per creare un volo valido
	 * @param dataOra Data del volo (in particolare la partenza)
	 * @param aeroportoPartenza Id dell'aeroporto di partenza
	 * @param aeroportoDestinazione Id dell'aeroporto di destinazione
	 * @param prezzoPasseggero Prezzo del volo per ogni passeggero (espresso in euro)
	 * @param prezzoPallet Prezzo del volo per ogni pallet (espresso in euro)
	 * @param stato stato del volo
	 * @param tipoAereo tipo di aereo usato per il volo
	 */
	public Volo(Date dataOra, Integer aeroportoPartenza, Integer aeroportoDestinazione, Double prezzoPasseggero, Double prezzoPallet, StatoVolo stato, TipoAereo tipoAereo){
		this(dataOra, aeroportoPartenza, aeroportoDestinazione, 0, 0, prezzoPasseggero, prezzoPallet, stato, tipoAereo);
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
	 * restituisce la data e lora del volo in formato stringa
	 * @return stringa contentente data e ora
	 */
	public String getDataOraString(){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy   hh:mm");
		return format.format(dataOra);
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
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy   H:mm");
		return  format.format(dataOra) + "          " +postiDisponibili+ " posti disponibili         "+palletDisponibili+ " pallet disponibili         " +prezzoPasseggero+ " €";
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
	public Double getPrezzoPasseggero() {
		return prezzoPasseggero;
	}



	/**
	 * Set il Prezzo del volo (espresso in euro)
	 *
	 * @param prezzo Prezzo del volo (espresso in euro)
	 */
	public void setPrezzoPasseggero(Double prezzo) {
		this.prezzoPasseggero = prezzo;
	}
	
	/**
	 * Restituisce il prezzo unitario per pallet 
	 * @return prezzo unitario del pallet
	 */
	public Double getPrezzoPallet() {
		return prezzoPallet;
	}

	/**
	 * Setta il prezzo del pallet
	 * @param prezzoPallet nuovo prezzo del pallet
	 */
	public void setPrezzoPallet(Double prezzoPallet) {
		this.prezzoPallet = prezzoPallet;
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
	 * restituisce l'id del gruppo da prenotare
	 * @return id degl gruppo
	 */
	public ObjectId getGroupNumber(){
		return new ObjectId();
	}

	/**
	 * @return the passeggeri
	 */
	public List<Passeggero> getPasseggeri() {
		return passeggeri;
	}

	/**
	 * @param passeggeri the passeggeri to set
	 */
	public void setPasseggeri(List<Passeggero> passeggeri) {
		this.passeggeri = passeggeri;
	}

	/**
	 * @return the pallet
	 */
	public List<Pallet> getPallet() {
		return pallet;
	}

	/**
	 * @param pallet the pallet to set
	 */
	public void setPallet(List<Pallet> pallet) {
		this.pallet = pallet;
	}
	
	
}
