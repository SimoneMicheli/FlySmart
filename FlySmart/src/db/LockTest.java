package db;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 */
public class LockTest {

	/**
	 * Method setUp.
	 * @throws Exception
	 */
	@BeforeClass
	public void setUp() throws Exception {
	}

	@Test
	public final void testAcquireLock() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testReleaseLock() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetInstance() {
		Lock l = Lock.getInstance();
		assertNotNull(l);
	}

}
