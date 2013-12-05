package cancellazione;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import model.Passeggero;
import model.Sesso;
import model.Volo;

import org.bson.types.ObjectId;
import org.junit.BeforeClass;
import org.junit.Test;

import util.Options;
import db.DBSession;

/**
 */
public class CancellaPasseggeroTest {
	
	static Volo v;
	static Passeggero p1;
	static ObjectId idGruppo;

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
		
		p1 = new Passeggero("mario", "rossi", 14, 3, 1960, Sesso.M);
		p1.setIdVolo(v.getId());
		idGruppo = new ObjectId();
		p1.setIdGruppo(idGruppo);
		DBSession.getPasseggeroDAO().save(p1);
		v.getPasseggeri().add(p1);
		
		DBSession.getVoloDAO().save(v);
	}
	
	@Test
	public void testGetPasseggeriGruppo() {
		CancellaPasseggero cp = new CancellaPasseggero();
		
		List<Passeggero> list = cp.getPasseggeriGruppo(idGruppo);
		
		assertFalse(list.isEmpty());
		assertEquals(p1, list.get(0));
	}

	@Test
	public void testCancellaPasseggero() {
		CancellaPasseggero cp = new CancellaPasseggero();
		
		int posti = DBSession.getVoloDAO().get(p1.getIdVolo()).getPostiDisponibili();
		
		cp.cancellaPasseggero(p1.getId());
		int posti2 = DBSession.getVoloDAO().get(p1.getIdVolo()).getPostiDisponibili();
		
		assertEquals(posti + 1, posti2);
		assertNull(DBSession.getPasseggeroDAO().get(p1.getId()));
	}
	
	@Test (expected = DeleteException.class)
	public void testCancellaPasseggeroException(){
		CancellaPasseggero cp = new CancellaPasseggero();
		cp.cancellaPasseggero(new ObjectId());
	}

}
