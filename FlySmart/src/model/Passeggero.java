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
public class Passeggero extends Posto{
	
	/** Id del gruppo di passeggero */
	@Indexed
	private ObjectId idGruppo;
	
	/** Nome del passeggero */
	private String nome;
	
	/** Cognome del passeggero */
	private String cognome;
	
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

	/**
	 * Istanzia un nuovo passeggero (supercostruttore)
	 * @param nome Nome del passeggero
	 * @param cognome Cognome del passeggero
	 * @param sesso Sesso del passeggero
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
	 * @param nome Nome del passeggero
	 * @param cognome Cognome del passeggero
	 * @param sesso Sesso del passeggero
	 * @param giorno Giorno di nascita
	 * @param mese Mese di nascita
	 * @param anno Anno di nascita
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
	 * @return int eta
	 */
	@SuppressWarnings("deprecation")
	public int getEta(){
		return (new Date()).getYear() - nascita.getYear();
	}


	/**
	 * Ottiene il sesso del passeggero
	 *
	
	 * @return Il sesso del passeggero */
	public Sesso getSesso() {
		return sesso;
	}

	/**
	 * Ottiene l'Id del volo del passeggero 
	 *
	
	 * @return Id del volo del passeggero  */
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
	 * Ottiene il nome del passeggero
	 *
	
	 * @return Il nome del passeggero */
	public String getNome() {
		return nome;
	}


	/**
	 * Ottiene il cognome del passeggero
	 *
	
	 * @return Il cognome del passeggero */
	public String getCognome() {
		return cognome;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nome: "+ nome + " Cognome: " + cognome+ " Peso: "+getPeso()+ " Fila: "+fila+ " colonna: "+colonna;
	}

	/**
	 * Ottiene l'Id del gruppo di passeggeri
	 *
	
	 * @return Id del gruppo di passeggeri */
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
	 * Restituisce l'ogggetto di tipo Date rappresentante la data di nascita
	 * @return data di nascita */
	public Date getNascita() {
		return nascita;
	}

	/**
	 * setta il peso in base al sesso e all'et��
	 * pu�� essere chiamato solo dal costruttore dell'oggetto
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

	/**
	 * Method equals.
	 * @param obj Object
	 * @return boolean
	 */
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
