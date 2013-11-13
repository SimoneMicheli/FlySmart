/**
 * 
 */
package db;

import java.net.UnknownHostException;

import model.Pallet;
import model.Passeggero;
import model.Volo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

/**
 * crea il gestore db db con un design pattern di tipo singleton
 *
 */
public class DBSession {
	private static Mongo mongo = null;
	
	private static Morphia morphia = null;
	
	private static Datastore ds = null;

	private static DBSession session = null;
	
	private static VoloDAO vDAO=null;
	
	private DBSession() throws UnknownHostException{
		mongo = new Mongo();
		morphia = new Morphia();
		
		setMapper();
		
		ds = morphia.createDatastore(mongo, "FlySmart");
		
		ds.ensureIndexes();
		ds.ensureCaps();
		
		createDAO();
	}
	
	private void setMapper(){
		
		morphia.map(Pallet.class);
		morphia.map(Passeggero.class);
		morphia.map(Volo.class);
		
	}
	
	private void createDAO(){
		vDAO = new VoloDAO(mongo, morphia, "FlySmart");
	}
	
	public static Datastore getInstance() throws UnknownHostException{
		if(session == null)
			session = new DBSession();
		return ds;
	}
	
	public static VoloDAO getVoloDAO(){
		return vDAO;
	}
	
}
