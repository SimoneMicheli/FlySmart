package model;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

public class Passeggero extends Model {
	private Integer idGruppo;
	private String nome;
	private String cognome;
	private Integer eta;
	private Integer giorno;
	private Integer mese;
	private Integer anno;
	private Character sesso;
	private Double pesoBagagli;
	private Integer idVolo;
	private Integer posto;

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

	public Passeggero() {
	}


	public Passeggero(String nome, String cognome,String giorno, String mese, String anno, Character sesso) {
		this(null, null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, Integer.parseInt(giorno), Integer.parseInt(mese), Integer.parseInt(anno));
	}
	
	
	public Passeggero(String nome, String cognome,int giorno, int mese, int anno, char sesso) {
		this(null, null, nome,  cognome,  calcolaEta(giorno, mese, anno),  sesso,  null,  null,  null, giorno, mese, anno);
	}

	protected static int calcolaEta(String g, String m, String a){
		return calcolaEta(Integer.parseInt(g), Integer.parseInt(m), Integer.parseInt(a));
	}
	
	//calcola l'eta a partire da una data di nascita
	protected static int calcolaEta(int g, int m, int a){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-a;
		if(m > 1+c.get(Calendar.MONTH) || (m == 1+c.get(Calendar.MONTH) && g >= c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
			return anni-1;
		}
		return anni;
	}


	public void calcolaEta(){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-anno;
		if(mese>1+c.get(Calendar.MONTH) || (mese==1+c.get(Calendar.MONTH) && giorno>=c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
			eta = anni-1;
		}else{
			eta = anni;
		}
	}


	public char getSesso() {
		return sesso;
	}


	public void setSesso(char sesso) {
		this.sesso = sesso;
	}


	public Double getPesoBagagli() {
		return pesoBagagli;
	}


	public void setPesoBagagli(Double pesoBagagli) {
		this.pesoBagagli = pesoBagagli;
	}


	public Integer getIdVolo() {
		return idVolo;
	}


	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}


	public Integer getPosto() {
		return posto;
	}


	public void setPosto(Integer posto) {
		this.posto = posto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public Integer getEta() {
		return eta;
	}


	public void setEta(Integer eta) {
		this.eta = eta;
	}

	@Override
	public String toString() {
		return nome + " " + cognome;
	}

	public Integer getMese() {
		return mese;
	}

	public void setMese(Integer mese) {
		this.mese = mese;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(Integer idGruppo) {
		this.idGruppo = idGruppo;
	}
	
	@Override
	public List<Field> getFields() {
		List<Field>  fields = super.getFields();
		Field[] currentFields = Passeggero.class.getDeclaredFields();
		for(Field f : currentFields)
			fields.add(f);
		return  fields;
	}

}
