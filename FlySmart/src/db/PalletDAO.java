/**
 * 
 */
package db;

import java.util.List;

import org.bson.types.ObjectId;

import model.Pallet;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

/**
 * Implementa il paradigma DAO (Data Access Object) di java per la classe Pallet
 *
 */
public class PalletDAO extends BasicDAO<Pallet, ObjectId> {

	public PalletDAO(Datastore ds){
		super(Pallet.class, ds);
	}
	
	/**
	 * ottiene l'elenco dei pallet di un certo volo
	 * @param idVolo id del volo da cercare
	 * @return elenco dei pallet del volo
	 */
	public List<Pallet> getByIdVolo(ObjectId idVolo){
		return ds.createQuery(Pallet.class).filter("idVolo =", idVolo).asList();
	}
	
	/**
	 * salva una lista di pallet
	 * @param list lista di pallet da salvare
	 */
	public void saveList(List<Pallet> list){
		for(Pallet p : list){
			this.save(p);
		}
	}
}
