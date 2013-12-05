package model;

import java.util.Calendar;
import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;

/**Rappresenta un cliente della compagnia aerea, e le sue informazioni personali.
 * Sono contenute inoltre informazioni circa il volo da lui prenotato e il posto assegnatogli
 * 
 * @author Demarinis - Micheli - Scarpellini
 * 
 * 
 */
@SuppressWarnings("serial")
@Entity
public class Passeggero extends Model {
	
	/** Id del gruppo di passeggero */
	@Indexed
	private ObjectId idGruppo;
	
	/** Nome del passeggero */
	private String nome;
	
	/** Cognome del passeggero */
	private String cognome;
	
	/** età del passeggero (numero intero di anni) */
	//private Integer eta;
	
	/** Giorno di nascita */
	//private Integer giorno;
	
	/** Mese di nascita */
	//private Integer mese;
	
	/** Anno di nascita */
	//private Integer anno;
	
	/** Data di nascita*/
	Date nascita;
	
	/** Sesso del passeggero:
	 * 'm' per maschio
	 * 'f' per femmina
	 */
	@Embedded
	private Sesso sesso;
	
	/** Id del volo del passeggero */
	@Indexed
	private ObjectId idVolo;
	
	/** Fila del posto del passeggero */
	private Integer fila;
	
	/** Colonna del posto del passeggero */
	private Integer colonna;

	/**
	 * Istanzia un nuovo passeggero (supercostruttore)
	 *
	 * @param idGruppo Id del gruppo di passeggero
	 * @param nome Nome del passeggero
	 * @param cognome Cognome del passeggero
	 * @param sesso Sesso del passeggero
	 * @param pesoBagagli Peso dei bagagli del passeggero (in Kilogrammi)
	 * @param idVolo Id del volo del passeggero 
	 * @param fila numero di fila assegnata al passeggero
	 * @param colonna numero id colonna assegnata al passeggero
	 * @param nascita data di nascita
	 */
	public Passeggero(String nome, String cognome, Date nascita, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.nascita = nascita;
	}
	
	/**
	 * Istanzia un nuovo passeggero (supercostruttore)
	 *
	 * @param idGruppo Id del gruppo di passeggero
	 * @param nome Nome del passeggero
	 * @param cognome Cognome del passeggero
	 * @param sesso Sesso del passeggero
	 * @param pesoBagagli Peso dei bagagli del passeggero (in Kilogrammi)
	 * @param idVolo Id del volo del passeggero 
	 * @param fila numero di fila assegnata al passeggero
	 * @param colonna numero id colonna assegnata al passeggero
	 */
	public Passeggero(String nome, String cognome, int giorno, int mese, int anno, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		Calendar c =  Calendar.getInstance();
		c.set(anno, mese, giorno);
		this.nascita = c.getTime();
	}

	/**
	 * Istanza un nuovo passeggero (costruttore vuoto)
	 */
	public Passeggero() {
	}

	/**
	 * restituisce l'eta del passeggero
	 *
	 */
	@SuppressWarnings("deprecation")
	public int getEta(){
		return (new Date()).getYear() - nascita.getYear();
	}


	/**
	 * Ottiene il sesso del passeggero
	 *
	 * @return Il sesso del passeggero
	 */
	public Sesso getSesso() {
		return sesso;
	}

	/**
	 * Ottiene l'Id del volo del passeggero 
	 *
	 * @return Id del volo del passeggero 
	 */
	public ObjectId getIdVolo() {
		return idVolo;
	}

	/**
	 * Set l'Id del volo del passeggero 
	 *
	 * @param idVolo Id del volo del passeggero 
	 */
	public void setIdVolo(ObjectId idVolo) {
		this.idVolo = idVolo;
	}


	/**
	 * Ottiene il numero di fila sul volo prenotato dal passeggero
	 *
	 * @return Il numero di fila sul volo prenotato dal passeggero
	 */
	public Integer getFila() {
		return fila;
	}


	/**
	 * Setta il numero di fila sul volo prenotato dal passeggero
	 *
	 * @param fila Il numero di fila sul volo prenotato dal passeggero
	 */
	public void setFila(Integer fila) {
		this.fila = fila;
	}


	/**
	 * Ottiene il numero di colonna sul volo prenotato dal passeggero
	 *
	 * @return Il numero di colonna sul volo prenotato dal passeggero
	 */
	public Integer getColonna() {
		return colonna;
	}

	/**
	 * Setta il numero di colonna sul volo prenotato dal passeggero
	 *
	 * @param colonna Il numero di colonna sul volo prenotato dal passeggero
	 */
	public void setColonna(Integer colonna) {
		this.colonna = colonna;
	}

	/**
	 * Ottiene il nome del passeggero
	 *
	 * @return Il nome del passeggero
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Ottiene il cognome del passeggero
	 *
	 * @return Il cognome del passeggero
	 */
	public String getCognome() {
		return cognome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nome: "+ nome + " Cognome: " + cognome+ " Peso: "+getPeso();
	}

	/**
	 * Ottiene l'Id del gruppo di passeggeri
	 *
	 * @return Id del gruppo di passeggeri
	 */
	public ObjectId getIdGruppo() {
		return idGruppo;
	}

	/**
	 * Set Id del gruppo di passeggeri
	 *
	 * @param idGruppo Id del gruppo di passeggeri
	 */
	public void setIdGruppo(ObjectId idGruppo) {
		this.idGruppo = idGruppo;
	}
	
	/**
	 * @return the nascita
	 */
	public Date getNascita() {
		return nascita;
	}

	/**
	 * setta il peso in base al sesso e all'età
	 * può essere chiamato solo dal costruttore dell'oggetto
	 * @return peso da settare
	 */
	public int getPeso(){
		int p = sesso.getPeso();
		if (getEta() < 14){
			p = (int) ((int) p*0.45);
		} else if (getEta() < 18){
			p = (int) ((int) p*0.85);
		}
		return p;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof Passeggero))
			return false;
		Passeggero p = (Passeggero) obj;
		if(this.nome.equals(p.nome) && this.cognome.equals(p.cognome) && getEta() == p.getEta())
			return true;
		return false;
	}

}
