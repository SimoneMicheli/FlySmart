package db;

import java.util.List;

import model.Passeggero;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.DAO;
import com.google.code.morphia.query.Query;

public interface PasseggeroDAO extends DAO<Passeggero, ObjectId> {

	/**
	 * ottiene l'elenco dei passegeri di un certo volo
	 * @param idVolo id del volo da cercare
	
	 * @return elenco dei passeggeri del volo */
	public abstract Query<Passeggero> getByIdVolo(ObjectId idVolo);

	/**
	 * salva una lista di passeggeri
	 * @param list lista di passeggeri da salvare
	 */
	public abstract void saveList(List<Passeggero> list);

	/**
	 * ritorna la query contenente i passeggeri di un certo gruppo
	 * @param id del gruppo
	
	 * @return query dei passeggeri */
	public abstract Query<Passeggero> getByGroupId(ObjectId id);

}