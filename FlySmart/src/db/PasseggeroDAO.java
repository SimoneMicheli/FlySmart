package db;

import org.bson.types.ObjectId;

import model.Passeggero;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

/**
 * Implementa il paradigma DAO (Data Access Object) di java per la classe Passeggero
 *
 */
public class PasseggeroDAO extends BasicDAO<Passeggero, ObjectId> {

	public PasseggeroDAO(Datastore ds){
		super(Passeggero.class, ds);
	}
}
