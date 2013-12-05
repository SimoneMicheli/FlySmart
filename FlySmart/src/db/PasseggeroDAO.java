package db;

import java.util.List;

import org.bson.types.ObjectId;

import model.Passeggero;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;

/**
 * Implementa il paradigma DAO (Data Access Object) di java per la classe Passeggero
 *
 */
public class PasseggeroDAO extends BasicDAO<Passeggero, ObjectId> {

	/**
	 * Constructor for PasseggeroDAO.
	 * @param ds Datastore
	 */
	public PasseggeroDAO(Datastore ds){
		super(Passeggero.class, ds);
	}
	
	/**
	 * ottiene l'elenco dei passegeri di un certo volo
	 * @param idVolo id del volo da cercare
	
	 * @return elenco dei passeggeri del volo */
	public Query<Passeggero> getByIdVolo(ObjectId idVolo){
		return ds.createQuery(Passeggero.class).filter("idVolo =", idVolo);
	}
	
	/**
	 * salva una lista di passeggeri
	 * @param list lista di passeggeri da salvare
	 */
	public void saveList(List<Passeggero> list){
		for(Passeggero p : list){
			this.save(p);
		}
	}
	
	/**
	 * ritorna la query contenente i passeggeri di un certo gruppo
	 * @param id del gruppo
	
	 * @return query dei passeggeri */
	public Query<Passeggero> getByGroupId(ObjectId id){
		return ds.createQuery(Passeggero.class).filter("idGruppo =", id);
	}
}
