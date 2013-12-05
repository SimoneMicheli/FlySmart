/**
 * 
 */
package db;

import java.util.Date;
import java.util.List;

import model.StatoVolo;
import model.Volo;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

/**
 *Implementa il paradigma DAO (Data Access Object) di java per la classe Volo
 */
public class VoloDAO extends BasicDAO<Volo, ObjectId> {

	/**
	 * Constructor for VoloDAO.
	 * @param ds Datastore
	 */
	public VoloDAO(Datastore ds){
		super(Volo.class, ds);
	}

	/**
	 * recupera un volo passando l'id come stringa
	 * @param id stringa del'id del volo
	
	 * @return volo richiesto se presente */
	public Volo getById(String id){
		return get(new ObjectId(id));
	}
	
	/**
	 * restituisce l'elenco dei voli che partono da un aeroporto (id partenza) ed arrivano ad un'altro (id arrivo)
	 * ordinati per data decresente
	 * @param p id aeroporto partenza
	 * @param a id aeroporto arrivo
	
	 * @return elenco voli */
	public List<Volo> getByPartenzaDestinazione(Integer p, Integer a){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("aeroportoDestinazione =", a)
				.filter("dataOra >", new Date())
				.filter("stato =", StatoVolo.OPEN)
				.order("dataOra")
				.asList();
	}
	
	/**
	 * restituisce tutti i voli dall'aeroporto di partenza
	 * @param p aeroporto partenza
	
	 * @return elenco volo */
	public List<Volo> getByPartenza(Integer p){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("dataOra >", new Date())
				.filter("stato =", StatoVolo.OPEN)
				.order("dataOra")
				.asList();
	}
}
