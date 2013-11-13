/**
 * 
 */
package db;

import java.util.List;

import model.Volo;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;

/**
 *
 */
public class VoloDAO extends BasicDAO<Volo, ObjectId> {

	public VoloDAO(Mongo mongo, Morphia morphia, String DBName){
		super(mongo, morphia, DBName);
	}
	
	public Volo getById(String id){
		return getById(new ObjectId(id));
	}
	
	public Volo getById(ObjectId id){
		return ds.createQuery(Volo.class).field("id").equal(id).get();
	}
}
