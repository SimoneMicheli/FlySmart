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
public class VoloDAOImpl extends BasicDAO<Volo, ObjectId> implements VoloDAO {

	/**
	 * Constructor for VoloDAO.
	 * @param ds Datastore
	 */
	public VoloDAOImpl(Datastore ds){
		super(Volo.class, ds);
	}

	/* (non-Javadoc)
	 * @see db.VoloDAO#getById(java.lang.String)
	 */
	@Override
	public Volo getById(String id){
		return get(new ObjectId(id));
	}
	
	/* (non-Javadoc)
	 * @see db.VoloDAO#getByPartenzaDestinazione(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Volo> getByPartenzaDestinazione(Integer p, Integer a){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("aeroportoDestinazione =", a)
				.filter("dataOra >", new Date())
				.filter("stato =", StatoVolo.OPEN)
				.order("dataOra")
				.asList();
	}
	
	/* (non-Javadoc)
	 * @see db.VoloDAO#getByPartenza(java.lang.Integer)
	 */
	@Override
	public List<Volo> getByPartenza(Integer p){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("dataOra >", new Date())
				.filter("stato =", StatoVolo.OPEN)
				.order("dataOra")
				.asList();
	}
}
