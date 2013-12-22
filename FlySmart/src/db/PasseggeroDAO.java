package db;

import java.util.List;

import model.Passeggero;

import org.bson.types.ObjectId;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.mongodb.WriteResult;

public interface PasseggeroDAO {
	
	/**
	 * ritorna il passeggero con l'id cercato
	 * @param id id passeggero
	 * @return Passeggero
	 */
	public abstract Passeggero get(ObjectId id);
	
	/**
	 * salva il passeggero
	 * @param p passeggero da salvare
	 * @return Key
	 */
	public abstract Key<Passeggero> save(Passeggero p);
	
	/**
	 * cancella il passeggero richiesto
	 * @param id id passeggero da cancellare
	 * @return WriteResult
	 */
	public abstract WriteResult deleteById(ObjectId id);

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