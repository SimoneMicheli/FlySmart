package util;

import static org.junit.Assert.*;

import model.TipoAereo;

import org.junit.Before;
import org.junit.Test;

/**
 */
public class CoordinataPasseggeroTest {
	
	Coordinata coord ;

	/**
	 * Method setUpBefore.
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		coord = new CoordinataPasseggero(TipoAereo.S);
	}

	@Test
	public final void testXAbs() {
		int actual = coord.XAbs(-2.5);
		assertEquals(0, actual);
		
		actual = coord.XAbs(-0.5);
		assertEquals(2, actual);
		
		actual = coord.XAbs(0.5);
		assertEquals(3, actual);
		
		actual = coord.XAbs(2.5);
		assertEquals(5, actual);
	}

	@Test
	public final void testYAbs() {
		int actual = coord.YAbs(5.5);
		assertEquals(11, actual);
		
		actual = coord.YAbs(0.5);
		assertEquals(6, actual);
		
		actual = coord.YAbs(-0.5);
		assertEquals(5, actual);
		
		actual = coord.YAbs(-5.5);
		assertEquals(0, actual);
	}

	@Test
	public final void testXRel() {
		double actual = coord.XRel(0);
		assertEquals(-2.5, actual, 0.1);
		
		actual = coord.XRel(2);
		assertEquals(-0.5, actual, 0.1);
		
		actual = coord.XRel(3);
		assertEquals(0.5, actual, 0.1);
		
		actual = coord.XRel(5);
		assertEquals(2.5, actual, 0.1);
	}

	@Test
	public final void testYRel() {
		double actual = coord.YRel(0);
		assertEquals(-5.5, actual, 0.1);
		
		actual = coord.YRel(5);
		assertEquals(-0.5, actual, 0.1);
		
		actual = coord.YRel(6);
		assertEquals(0.5, actual, 0.1);
		
		actual = coord.YRel(11);
		assertEquals(5.5, actual, 0.1);
	}

}
