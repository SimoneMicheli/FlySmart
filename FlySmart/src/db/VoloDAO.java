/**
 * 
 */
package db;

import java.util.List;

import model.Volo;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

/**
 *
 */
public class VoloDAO extends BasicDAO<Volo, ObjectId> {

	public VoloDAO(Datastore ds){
		super(Volo.class, ds);
	}
	
	public Volo getById(String id){
		return getById(new ObjectId(id));
	}
	
	public Volo getById(ObjectId id){
		return ds.createQuery(Volo.class).field("id").equal(id).get();
	}
	
	public List<Volo> getByPartenzaDestinazione(Integer p, Integer a){
		return ds.createQuery(Volo.class)
				.filter("aeroportoPartenza =",p)
				.filter("aeroportoDestinazione =", a)
				.order("-dataOra")
				.asList();
	}
}
