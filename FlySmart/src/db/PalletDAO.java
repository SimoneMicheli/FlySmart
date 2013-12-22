package db;

import java.util.List;

import model.Pallet;

import org.bson.types.ObjectId;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.mongodb.WriteResult;

public interface PalletDAO {
	
	/**
	 * ritorna il pallet con l'id cercato
	 * @param id id pallet
	 * @return Passeggero
	 */
	public abstract Pallet get(ObjectId id);
	
	/**
	 * salva il pallet
	 * @param p pallet da salvare
	 * @return Key
	 */
	public abstract Key<Pallet> save(Pallet p);
	
	/**
	 * cancella il pallet richiesto
	 * @param id id pallet da cancellare
	 * @return WriteResult
	 */
	public abstract WriteResult deleteById(ObjectId id);

	/**
	 * ottiene l'elenco dei pallet di un certo volo
	 * @param idVolo id del volo da cercare
	
	 * @return elenco dei pallet del volo */
	public abstract Query<Pallet> getByIdVolo(ObjectId idVolo);

	/**
	 * salva una lista di pallet
	 * @param list lista di pallet da salvare
	 */
	public abstract void saveList(List<Pallet> list);

}