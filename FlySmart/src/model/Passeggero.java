package model;

import java.io.Serializable;
import java.util.Calendar;


public class Passeggero implements Serializable, Comparable<Passeggero>{
	// private static final long serialVersionUID = -6370829625592709820L;
	private Integer id;
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


	//calcola l'età a partire da una data di nascita
	protected static int calcolaEta(String g, String m, String a){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-Integer.parseInt(a);
		if(Integer.parseInt(m)>1+c.get(Calendar.MONTH) || (Integer.parseInt(m)==1+c.get(Calendar.MONTH) && Integer.parseInt(g)>=c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public char getSesso() {
		return sesso;
	}


	public void setSesso(char sesso) {
		this.sesso = sesso;
	}


	public double getPesoBagagli() {
		return pesoBagagli;
	}


	public void setPesoBagagli(double pesoBagagli) {
		this.pesoBagagli = pesoBagagli;
	}


	public int getIdVolo() {
		return idVolo;
	}


	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}


	public int getPosto() {
		return posto;
	}


	public void setPosto(int posto) {
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


	public int getEta() {
		return eta;
	}


	public void setEta(int eta) {
		this.eta = eta;
	}

	@Override
	public String toString() {
		return nome + " " + cognome;
	}

	@Override
	public int compareTo(Passeggero o) {
		int cmp = this.cognome.compareTo(o.cognome);
		if (cmp == 0)
			cmp = this.nome.compareTo(o.nome);
		return cmp;
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

}

