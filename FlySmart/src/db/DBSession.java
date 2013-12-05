package db;

import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.Options;

import model.Pallet;
import model.Passeggero;
import model.Volo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

// TODO: Auto-generated Javadoc
/**
 * crea il gestore del db
 * implementazione con design pattern di tipo singleton.
 */
public final class DBSession{

	/** The log. */
	private static Logger log = LogManager.getLogger(DBSession.class.getCanonicalName().toString());

	/** connessione a mongoDB. */
	private static Mongo mongo = null;
	
	/** mapper to/from mongoDB. */
	private static Morphia morphia = null;
	
	/** gestisce lo scambio di dati da e per il DB. */
	protected static Datastore ds = null;

	/** The session. */
	private static DBSession session = null;
	
	/** The v dao. */
	private static VoloDAO vDAO=null;
	
	/** The pass dao. */
	private static PasseggeroDAO passDAO=null;
	
	/** The pall dao. */
	private static PalletDAO pallDAO=null;
	
	/**
	 * costruttore privato per singleton.
	 *
	
	 * @throws UnknownHostException the unknown host exception */
	protected DBSession() throws UnknownHostException{
		
		log.info("Connessione a MongoDB");
		
		//MorphiaLoggerFactory.registerLogger( SilentLogrFactory.class);
		
		mongo = new Mongo(Options.mongoHost, Options.mongoPort);
		morphia = new Morphia();
		
		
		setMapper();
		
		ds = morphia.createDatastore(mongo, Options.mongoDBName);
		
		ds.ensureIndexes();
		ds.ensureCaps();
		
		log.info("Connesso a MongoDB");
	}
	
	/**
	 * configura morphia per eseguire il mapping degli oggetti.
	 */
	private void setMapper(){
		
		log.info("Mapping object to DB");
		morphia.map(Pallet.class);
		morphia.map(Passeggero.class);
		morphia.map(Volo.class);
		
	}

	/**
	 * ritorna l'istanza del datastore che permette di eseguire query sul DB.
	 *
	
	 * @return datastore */
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
	
	/**
	 * ritorna un istanza di VoloDAO.
	 *
	
	 * @return VoloDAO */
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
	
	/**
	 * ritorna un istanza di PasseggeroDAO.
	 *
	
	 * @return PasseggeroDAO */
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
	
	/**
	 * ritorna un istanza di PalletDAO.
	 *
	
	 * @return PalletDAO */
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
