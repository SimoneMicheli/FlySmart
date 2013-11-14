/**
 * 
 */
package db;

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
}
