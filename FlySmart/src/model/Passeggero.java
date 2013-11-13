package model;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

/**Rappresenta un cliente della compagnia aerea, e le sue informazioni personali.
 * Sono contenute inoltre informazioni circa il volo da lui prenotato e il posto assegnatogli
 * 
 * @author Demarinis - Micheli - Scarpellini
 * 
 * 
 */
@Entity
public class Passeggero extends Model {
	
	/** Id del gruppo di passeggero */
	private Integer idGruppo;
	
	/** Nome del passeggero */
	private String nome;
	
	/** Cognome del passeggero */
	private String cognome;
	
	/** età del passeggero (numero intero di anni) */
	private Integer eta;
	
	/** Giorno di nascita */
	private Integer giorno;
	
	/** Mese di nascita */
	private Integer mese;
	
	/** Anno di nascita */
	private Integer anno;
	
	/** Sesso del passeggero:
	 * 'm' per maschio
	 * 'f' per femmina
	 */
	@Embedded
	private Sesso sesso;
	
	/** Peso dei bagagli del passeggero (in Kilogrammi) */
	private Double pesoBagagli;
	
	/** Id del volo del passeggero */
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
	 * @param eta età del passeggero (numero intero di anni)
	 * @param sesso Sesso del passeggero
	 * @param pesoBagagli Peso dei bagagli del passeggero (in Kilogrammi)
	 * @param idVolo Id del volo del passeggero 
	 * @param fila numero di fila assegnata al passeggero
	 * @param colonna numero id colonna assegnata al passeggero
	 * @param giorno Giorno di nascita
	 * @param mese Mese di nascita
	 * @param anno Anno di nascita
	 */
	public Passeggero(Integer idGruppo, String nome, String cognome, Integer eta, Sesso sesso, Double pesoBagagli, ObjectId idVolo, Integer fila, Integer colonna, Integer giorno, Integer mese, Integer anno) {
		this.idGruppo = idGruppo;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.sesso = sesso;
		this.pesoBagagli = pesoBagagli;
		this.idVolo = idVolo;
		this.fila = fila;
		this.colonna = colonna;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
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
	public Passeggero(String nome, String cognome,String giorno, String mese, String anno, Sesso sesso) {
		this(null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, null, Integer.parseInt(giorno), Integer.parseInt(mese), Integer.parseInt(anno));
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
		this(null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, null, giorno, mese, anno);
	}

	/**
	 * Calcola eta del passeggero a partire dalla sua data di nascita
	 *
	 * @param g Giorno di nascita
	 * @param m Mese di nascita
	 * @param a Anno di nascita
	 * @return età del passeggero (numero intero di anni)
	 */
	protected static int calcolaEta(String g, String m, String a){
		return calcolaEta(Integer.parseInt(g), Integer.parseInt(m), Integer.parseInt(a));
	}
	
	//calcola l'eta a partire da una data di nascita
	/**
	 * Calcola eta del passeggero a partire dalla sua data di nascita
	 *
	 * @param g Giorno di nascita
	 * @param m Mese di nascita
	 * @param a Anno di nascita
	 * @return età del passeggero (numero intero di anni)
	 */
	protected static int calcolaEta(int g, int m, int a){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-a;
		if(m > 1+c.get(Calendar.MONTH) || (m == 1+c.get(Calendar.MONTH) && g >= c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio � lo zero
			return anni-1;
		}
		return anni;
	}


	/**
	 * Calcola l'eta del passeggero
	 *
	 */
	public void calcolaEta(){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-anno;
		if(mese>1+c.get(Calendar.MONTH) || (mese==1+c.get(Calendar.MONTH) && giorno>=c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio � lo zero
			eta = anni-1;
		}else{
			eta = anni;
		}
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
	 * Ottiene il peso dei bagagli in Kilogrammi
	 *
	 * @return Il peso dei bagagli in Kilogrammi
	 */
	public Double getPesoBagagli() {
		return pesoBagagli;
	}


	/**
	 * Set il peso dei bagali in Kilogrammi
	 *
	 * @param pesoBagagli Il peso dei bagagli in Kilogrammi
	 */
	public void setPesoBagagli(Double pesoBagagli) {
		this.pesoBagagli = pesoBagagli;
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


	/**
	 * Ottiene l' età del passeggero (numero intero di anni)
	 *
	 * @return età del passeggero (numero intero di anni)
	 */
	public Integer getEta() {
		return eta;
	}


	/**
	 * Set l'età del passeggero (numero intero di anni)
	 *
	 * @param eta età del passeggero (numero intero di anni)
	 */
	public void setEta(Integer eta) {
		this.eta = eta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nome + " " + cognome;
	}

	/**
	 * Gets Il mese di nascita
	 *
	 * @return Il mese di nascita
	 */
	public Integer getMese() {
		return mese;
	}

	/**
	 * Sets Il mese di nascita
	 *
	 * @param mese Il mese di nascita
	 */
	public void setMese(Integer mese) {
		this.mese = mese;
	}

	/**
	 * Ottiene il giorno di nascita
	 *
	 * @return il giorno di nascita
	 */
	public Integer getGiorno() {
		return giorno;
	}

	/**
	 * Set il giorno di nascita
	 *
	 * @param giorno il giorno di nascita
	 */
	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	/**
	 * Ottiene l'anno di nascita
	 *
	 * @return l'anno di nascita
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * Set l'anno di nascita
	 *
	 * @param anno l'anno di nascita
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * Ottiene l'Id del gruppo di passeggeri
	 *
	 * @return Id del gruppo di passeggeri
	 */
	public Integer getIdGruppo() {
		return idGruppo;
	}

	/**
	 * Set Id del gruppo di passeggeri
	 *
	 * @param idGruppo Id del gruppo di passeggeri
	 */
	public void setIdGruppo(Integer idGruppo) {
		this.idGruppo = idGruppo;
	}
	
	/**
	 * restituisce il peso di un passeggero in base al sesso e all'età
	 * @return peso del passeggero
	 */
	public int getPeso(){
		int p = sesso.getPeso();
		if (eta < 14){
			p = (int) ((int) p*0.5);
		} else if (eta < 18){
			p = (int) ((int) p*0.8);
		}
		return p;
	}

}
