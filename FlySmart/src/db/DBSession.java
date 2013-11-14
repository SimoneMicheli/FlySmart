/**
 * 
 */
package db;

import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Pallet;
import model.Passeggero;
import model.Volo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.mongodb.Mongo;

/**
 * crea il gestore db db con un design pattern di tipo singleton
 *
 */
public class DBSession{
	
	//configuration----------------------
	private static String DBName = "FlySmart";
	
	//runtime var------------------------
	
	private static Logger log = LogManager.getLogger(DBSession.class.getCanonicalName().toString());
	
	private static Mongo mongo = null;
	
	private static Morphia morphia = null;
	
	private static Datastore ds = null;

	private static DBSession session = null;
	
	private static VoloDAO vDAO=null;
	
	private static PasseggeroDAO passDAO=null;
	
	private static PalletDAO pallDAO=null;
	
	private DBSession() throws UnknownHostException{
		
		log.info("Connessione a mongo DB");
		mongo = new Mongo();
		morphia = new Morphia();
		
		setMapper();
		
		ds = morphia.createDatastore(mongo, DBName);
		
		ds.ensureIndexes();
		ds.ensureCaps();
		
		log.info("Connessione Riuscita");
	}
	
	private void setMapper(){
		
		log.info("Mapping object to DB");
		morphia.map(Pallet.class);
		morphia.map(Passeggero.class);
		morphia.map(Volo.class);
		
	}

	
	public static Datastore getInstance() {
		log.entry();
		if(session == null)
			try {
				session = new DBSession();
			} catch (UnknownHostException e) {
				log.catching(e);
			}
		log.exit(ds);
		return ds;
	}
	
	public static VoloDAO getVoloDAO(){
		log.entry();
		if(session == null)
			try {
				session = new DBSession();
			} catch (UnknownHostException e) {
				log.catching(e);
			}
		if(vDAO == null)
			vDAO = new VoloDAO(ds);
		log.exit(vDAO);
		return vDAO;
	}
	
	public static PasseggeroDAO getPasseggeroDAO(){
		log.entry();
		if(session == null)
			try {
				session = new DBSession();
			} catch (UnknownHostException e) {
				log.catching(e);
			}
		if(passDAO == null)
			passDAO = new PasseggeroDAO(ds);
		log.exit(passDAO);
		return passDAO;
	}
	
	public static PalletDAO getPalletDAO(){
		log.entry();
		if(session == null)
			try {
				session = new DBSession();
			} catch (UnknownHostException e) {
				log.catching(e);
			}
		if(pallDAO == null)
			pallDAO = new PalletDAO(ds);
		log.exit(pallDAO);
		return pallDAO;
	}
	
}
