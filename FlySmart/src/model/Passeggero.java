package model;



public class Passeggero{
	private int id;
	private String nome;
	private String cognome;
	private int eta;
	private char sesso;
	private double pesoBagagli;
	private int idVolo;
	private int posto;
	
	
	
	
	
	public Passeggero(Integer id, String nome, String cognome, Integer eta, Character sesso, Double pesoBagagli, Integer idVolo, Integer posto) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.sesso = sesso;
		this.pesoBagagli = pesoBagagli;
		this.idVolo = idVolo;
		this.posto = posto;
	}
	
	
	public Passeggero(String nome, String cognome, Integer eta, Character sesso) {
		this(null, nome,  cognome,  eta,  sesso,  null,  null,  null);
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
	
	
	
}
