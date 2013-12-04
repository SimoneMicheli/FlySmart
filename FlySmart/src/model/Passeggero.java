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
	
	/** Peso del passeggero */
	private Integer peso;

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
	public Passeggero(ObjectId idGruppo, String nome, String cognome, Sesso sesso, ObjectId idVolo, Integer fila, Integer colonna, Date nascita) {
		this.idGruppo = idGruppo;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.idVolo = idVolo;
		this.fila = fila;
		this.colonna = colonna;
		this.nascita = nascita;
		this.peso = setPeso();
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
	public Passeggero(ObjectId idGruppo, String nome, String cognome, Sesso sesso, ObjectId idVolo, Integer fila, Integer colonna, int giorno, int mese, int anno) {
		this.idGruppo = idGruppo;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.idVolo = idVolo;
		this.fila = fila;
		this.colonna = colonna;
		Calendar c =  Calendar.getInstance();
		c.set(anno, mese, giorno);
		this.nascita = c.getTime();
		this.peso = setPeso();
	}

	/**
	 * Istanza un nuovo passeggero (costruttore vuoto)
	 */
	public Passeggero() {
	}
	
	
	/**
	 * Istanza un nuovo passeggero.
	 *
	 * @param nome Nome del passeggero
	 * @param cognome Cognome del passeggero
	 * @param giorno Giorno di nascita
	 * @param mese Mese di nascita
	 * @param anno Anno di nascita
	 * @param sesso Sesso del passeggero
	 */
	public Passeggero(String nome, String cognome,int giorno, int mese, int anno, Sesso sesso) {
		this(null, nome,  cognome, sesso,  null,  null, null, giorno, mese, anno);
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
	 * Set il sesso del passeggero
	 *
	 * @param sesso Il sesso del passeggero: 'm' per maschio, 'f' per femmina
	 * 
	 */
	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
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
	 * Sets il nome del passeggero
	 *
	 * @param nome Il nome del passeggero
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Ottiene il cognome del passeggero
	 *
	 * @return Il cognome del passeggero
	 */
	public String getCognome() {
		return cognome;
	}


	/**
	 * Set Il cognome del passeggero
	 *
	 * @param cognome Il cognome del passeggero
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * restituisce il peso di un passeggero in base al sesso e all'età
	 * @return peso del passeggero
	 */
	public int getPeso(){
		return peso;
	}
	
	/**
	 * @return the nascita
	 */
	public Date getNascita() {
		return nascita;
	}

	/**
	 * @param nascita the nascita to set
	 */
	public void setNascita(Date nascita) {
		this.nascita = nascita;
		peso = setPeso();
	}

	/**
	 * setta il peso in base al sesso e all'età
	 * può essere chiamato solo dal costruttore dell'oggetto
	 * @return peso da settare
	 */
	private int setPeso(){
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
