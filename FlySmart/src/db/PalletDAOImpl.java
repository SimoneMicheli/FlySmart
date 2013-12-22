/**
 * 
 */
package db;

import java.util.List;

import org.bson.types.ObjectId;

import model.Pallet;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;

/**
 * Implementa il paradigma DAO (Data Access Object) di java per la classe Pallet
 *
 */
public class PalletDAOImpl extends BasicDAO<Pallet, ObjectId> implements PalletDAO {

	/**
	 * Constructor for PalletDAO.
	 * @param ds Datastore
	 */
	public PalletDAOImpl(Datastore ds){
		super(Pallet.class, ds);
	}
	
	/* (non-Javadoc)
	 * @see db.PalletDAO#getByIdVolo(org.bson.types.ObjectId)
	 */
	@Override
	public Query<Pallet> getByIdVolo(ObjectId idVolo){
		return ds.createQuery(Pallet.class).filter("idVolo =", idVolo);
	}
	
	/* (non-Javadoc)
	 * @see db.PalletDAO#saveList(java.util.List)
	 */
	@Override
	public void saveList(List<Pallet> list){
		for(Pallet p : list){
			this.save(p);
		}
	}
}
