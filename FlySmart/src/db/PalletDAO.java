package db;

import java.util.List;

import model.Pallet;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.DAO;
import com.google.code.morphia.query.Query;

public interface PalletDAO extends DAO<Pallet, ObjectId>{


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