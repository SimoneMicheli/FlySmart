package db;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.mapping.MappingException;

import util.Options;

/**
 */
public class DBSessionTest {

	/**
	 * Method setUp.
	 * @throws Exception
	 */
	@BeforeClass
	public void setUp() throws Exception {
		Options.initOptions();
	}

	@Test
	public final void testDBSession() {
		DBSession db = null;
		try {
			 db = new DBSession();
		} catch (UnknownHostException e) {
			fail("Impossibile connettersi al server MongoDb");
		} catch (MappingException e) {
			fail("impossibile eseguire mapping oggetti");
		}
		
		assertNotNull(db);
		assertNotNull("Datastore null",DBSession.ds);
	}

	@Test
	public final void testGetInstance() {
		Datastore ds = DBSession.getInstance();
		assertNotNull(ds);
	}

	@Test
	public final void testGetVoloDAO() {
		Object v = DBSession.getVoloDAO();
		assertNotNull(v);
		assertTrue("Tipo errato",v instanceof VoloDAOImpl);
	}

	@Test
	public final void testGetPasseggeroDAO() {
		Object v = DBSession.getPasseggeroDAO();
		assertNotNull(v);
		assertTrue("Tipo errato",v instanceof PasseggeroDAOImpl);
	}

	@Test
	public final void testGetPalletDAO() {
		Object v = DBSession.getPalletDAO();
		assertNotNull(v);
		assertTrue("Tipo errato",v instanceof PalletDAOImpl);
	}

}
