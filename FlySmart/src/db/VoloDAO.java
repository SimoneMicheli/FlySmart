package db;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.DAO;

import model.Volo;

public interface VoloDAO extends DAO<Volo, ObjectId>{

	/**
	 * recupera un volo passando l'id come stringa
	 * @param id stringa del'id del volo
	 * @return volo richiesto se presente */
	public abstract Volo getById(String id);

	/**
	 * restituisce l'elenco dei voli che partono da un aeroporto (id partenza) ed arrivano ad un'altro (id arrivo)
	 * ordinati per data decresente
	 * @param p id aeroporto partenza
	 * @param a id aeroporto arrivo
	
	 * @return elenco voli */
	public abstract List<Volo> getByPartenzaDestinazione(Integer p, Integer a);

	/**
	 * restituisce tutti i voli dall'aeroporto di partenza
	 * @param p aeroporto partenza
	
	 * @return elenco volo */
	public abstract List<Volo> getByPartenza(Integer p);

}