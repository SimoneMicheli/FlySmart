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
public class PasseggeroDAOImpl extends BasicDAO<Passeggero, ObjectId> implements PasseggeroDAO {

	/**
	 * Constructor for PasseggeroDAO.
	 * @param ds Datastore
	 */
	public PasseggeroDAOImpl(Datastore ds){
		super(Passeggero.class, ds);
	}
	
	/* (non-Javadoc)
	 * @see db.PasseggeroDAO#getByIdVolo(org.bson.types.ObjectId)
	 */
	@Override
	public Query<Passeggero> getByIdVolo(ObjectId idVolo){
		return ds.createQuery(Passeggero.class).filter("idVolo =", idVolo);
	}
	
	/* (non-Javadoc)
	 * @see db.PasseggeroDAO#saveList(java.util.List)
	 */
	@Override
	public void saveList(List<Passeggero> list){
		for(Passeggero p : list){
			this.save(p);
		}
	}
	
	/* (non-Javadoc)
	 * @see db.PasseggeroDAO#getByGroupId(org.bson.types.ObjectId)
	 */
	@Override
	public Query<Passeggero> getByGroupId(ObjectId id){
		return ds.createQuery(Passeggero.class).filter("idGruppo =", id);
	}
}
