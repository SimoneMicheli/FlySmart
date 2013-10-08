package model;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * @author Demarinis - Micheli - Scarpellini
 * The Class Passeggero.
 */
public class Passeggero extends Model {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7403817763041464772L;
	
	/** The id gruppo. */
	private Integer idGruppo;
	
	/** The nome. */
	private String nome;
	
	/** The cognome. */
	private String cognome;
	
	/** The eta. */
	private Integer eta;
	
	/** The giorno. */
	private Integer giorno;
	
	/** The mese. */
	private Integer mese;
	
	/** The anno. */
	private Integer anno;
	
	/** The sesso. */
	private Character sesso;
	
	/** The peso bagagli. */
	private Double pesoBagagli;
	
	/** The id volo. */
	private Integer idVolo;
	
	/** The posto. */
	private Integer posto;

	/**
	 * Instantiates a new passeggero.
	 *
	 * @param id the id
	 * @param idGruppo the id gruppo
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param eta the eta
	 * @param sesso the sesso
	 * @param pesoBagagli the peso bagagli
	 * @param idVolo the id volo
	 * @param posto the posto
	 * @param giorno the giorno
	 * @param mese the mese
	 * @param anno the anno
	 */
	public Passeggero(Integer id, Integer idGruppo, String nome, String cognome, Integer eta, Character sesso, Double pesoBagagli, Integer idVolo, Integer posto, Integer giorno, Integer mese, Integer anno) {
		this.id = id;
		this.idGruppo = idGruppo;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.sesso = sesso;
		this.pesoBagagli = pesoBagagli;
		this.idVolo = idVolo;
		this.posto = posto;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}

	/**
	 * Instantiates a new passeggero.
	 */
	public Passeggero() {
	}


	/**
	 * Instantiates a new passeggero.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param giorno the giorno
	 * @param mese the mese
	 * @param anno the anno
	 * @param sesso the sesso
	 */
	public Passeggero(String nome, String cognome,String giorno, String mese, String anno, Character sesso) {
		this(null, null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, Integer.parseInt(giorno), Integer.parseInt(mese), Integer.parseInt(anno));
	}
	
	
	/**
	 * Instantiates a new passeggero.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param giorno the giorno
	 * @param mese the mese
	 * @param anno the anno
	 * @param sesso the sesso
	 */
	public Passeggero(String nome, String cognome,int giorno, int mese, int anno, char sesso) {
		this(null, null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, giorno, mese, anno);
	}

	/**
	 * Calcola eta.
	 *
	 * @param g the g
	 * @param m the m
	 * @param a the a
	 * @return the int
	 */
	protected static int calcolaEta(String g, String m, String a){
		return calcolaEta(Integer.parseInt(g), Integer.parseInt(m), Integer.parseInt(a));
	}
	
	//calcola l'eta a partire da una data di nascita
	/**
	 * Calcola eta.
	 *
	 * @param g the g
	 * @param m the m
	 * @param a the a
	 * @return the int
	 */
	protected static int calcolaEta(int g, int m, int a){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-a;
		if(m > 1+c.get(Calendar.MONTH) || (m == 1+c.get(Calendar.MONTH) && g >= c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
			return anni-1;
		}
		return anni;
	}


	/**
	 * Calcola eta.
	 */
	public void calcolaEta(){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-anno;
		if(mese>1+c.get(Calendar.MONTH) || (mese==1+c.get(Calendar.MONTH) && giorno>=c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
			eta = anni-1;
		}else{
			eta = anni;
		}
	}


	/**
	 * Gets the sesso.
	 *
	 * @return the sesso
	 */
	public char getSesso() {
		return sesso;
	}


	/**
	 * Sets the sesso.
	 *
	 * @param sesso the new sesso
	 */
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}


	/**
	 * Gets the peso bagagli.
	 *
	 * @return the peso bagagli
	 */
	public Double getPesoBagagli() {
		return pesoBagagli;
	}


	/**
	 * Sets the peso bagagli.
	 *
	 * @param pesoBagagli the new peso bagagli
	 */
	public void setPesoBagagli(Double pesoBagagli) {
		this.pesoBagagli = pesoBagagli;
	}


	/**
	 * Gets the id volo.
	 *
	 * @return the id volo
	 */
	public Integer getIdVolo() {
		return idVolo;
	}


	/**
	 * Sets the id volo.
	 *
	 * @param idVolo the new id volo
	 */
	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}


	/**
	 * Gets the posto.
	 *
	 * @return the posto
	 */
	public Integer getPosto() {
		return posto;
	}


	/**
	 * Sets the posto.
	 *
	 * @param posto the new posto
	 */
	public void setPosto(Integer posto) {
		this.posto = posto;
	}


	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}


	/**
	 * Sets the cognome.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	/**
	 * Gets the eta.
	 *
	 * @return the eta
	 */
	public Integer getEta() {
		return eta;
	}


	/**
	 * Sets the eta.
	 *
	 * @param eta the new eta
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
	 * Gets the mese.
	 *
	 * @return the mese
	 */
	public Integer getMese() {
		return mese;
	}

	/**
	 * Sets the mese.
	 *
	 * @param mese the new mese
	 */
	public void setMese(Integer mese) {
		this.mese = mese;
	}

	/**
	 * Gets the giorno.
	 *
	 * @return the giorno
	 */
	public Integer getGiorno() {
		return giorno;
	}

	/**
	 * Sets the giorno.
	 *
	 * @param giorno the new giorno
	 */
	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	/**
	 * Gets the anno.
	 *
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * Sets the anno.
	 *
	 * @param anno the new anno
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * Gets the id gruppo.
	 *
	 * @return the id gruppo
	 */
	public Integer getIdGruppo() {
		return idGruppo;
	}

	/**
	 * Sets the id gruppo.
	 *
	 * @param idGruppo the new id gruppo
	 */
	public void setIdGruppo(Integer idGruppo) {
		this.idGruppo = idGruppo;
	}
	

	/**
	 * Gets the serial version uid.
	 *
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#getFields()
	 */
	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Passeggero.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}

}
