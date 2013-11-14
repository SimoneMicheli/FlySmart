/**
 * 
 */
package db;

import java.util.List;

import model.Volo;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

/**
 *
 */
public class VoloDAO extends BasicDAO<Volo, ObjectId> {

	public VoloDAO(Datastore ds){
		super(Volo.class, ds);
	}
	
	public Volo getById(String id){
		return get(new ObjectId(id));
	}
	
	public List<Volo> getByPartenzaDestinazione(Integer p, Integer a){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("aeroportoDestinazione =", a)
				.order("-dataOra")
				.asList();
	}
}
