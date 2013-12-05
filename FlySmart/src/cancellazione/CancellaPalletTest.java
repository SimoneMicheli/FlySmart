package cancellazione;

import static org.junit.Assert.*;

import java.util.Date;

import model.Pallet;
import model.Volo;

import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import util.Options;

import db.DBSession;

/**
 */
public class CancellaPalletTest {

	static Pallet  p1;
	static Volo v;
	
	/**
	 * Method setUpBeforeClass.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Options.initOptions();
		v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",1)
				.filter("aeroportoDestinazione =", 2)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);
		
		p1 = new Pallet(1000, "test1", v.getId());
		DBSession.getPalletDAO().save(p1);
		v.getPallet().add(p1);
		
		DBSession.getVoloDAO().save(v);
	}

	@Test
	public void testGetInfoPallet() {
		CancellaPallet cp = new CancellaPallet();
		
		Pallet p = cp.getInfoPallet(p1.getId());
		assertEquals(p1, p);
		
	}

	@Test
	public void testCancellaPallet() {
		CancellaPallet cp = new CancellaPallet();
		
		int posti = DBSession.getVoloDAO().get(p1.getIdVolo()).getPalletDisponibili();
		
		cp.cancellaPallet(p1.getId());
		int posti2 = DBSession.getVoloDAO().get(p1.getIdVolo()).getPalletDisponibili();
		
		assertEquals(posti + 1, posti2);
		
		assertNull(DBSession.getPalletDAO().get(p1.getId()));
		
	}
	
	@Test(expected = DeleteException.class)
	public void testCancellaPalletException(){
		CancellaPallet cp = new CancellaPallet();
		cp.cancellaPallet(new ObjectId());
	}

}
