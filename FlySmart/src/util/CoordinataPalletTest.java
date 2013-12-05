package util;

import static org.junit.Assert.*;

import model.TipoAereo;

import org.junit.Before;
import org.junit.Test;

/**
 */
public class CoordinataPalletTest {

	Coordinata coord ;
	
	/**
	 * Method setUpBefore.
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		coord = new CoordinataPallet(TipoAereo.S);
	}

	@Test
	public final void testXAbs() {
		int actual = coord.XAbs(-0.5);
		assertEquals(0, actual);
		
		actual = coord.XAbs(0.5);
		assertEquals(1, actual);
	}

	@Test
	public final void testYAbs() {
		int actual = coord.YAbs(-1.5);
		assertEquals(0, actual);
		
		actual = coord.YAbs(-0.5);
		assertEquals(1, actual);
		
		actual = coord.YAbs(0.5);
		assertEquals(2, actual);
		
		actual = coord.YAbs(1.5);
		assertEquals(3, actual);
	}

	@Test
	public final void testXRel() {
		double actual = coord.XRel(0);
		assertEquals(-0.5, actual, 0.1);
		
		actual = coord.XRel(1);
		assertEquals(0.5, actual, 0.1);
	}

	@Test
	public final void testYRel() {
		double actual = coord.YRel(0);
		assertEquals(-1.5, actual, 0.1);
		
		actual = coord.YRel(1);
		assertEquals(-0.5, actual, 0.1);
		
		actual = coord.YRel(2);
		assertEquals(0.5, actual, 0.1);
		
		actual = coord.YRel(3);
		assertEquals(1.5, actual, 0.1);
	}

}
