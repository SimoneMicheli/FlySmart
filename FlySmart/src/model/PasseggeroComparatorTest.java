package model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PasseggeroComparatorTest {
	private static Passeggero p1, p2 ,p3,p4;
	
	@BeforeClass
	public static void setUp() throws Exception {
		p1 = new Passeggero("mario", "rossi", 12, 3, 1960, Sesso.M);
		p1.setColonna(2);
		p1.setFila(5);
		
		p2 = new Passeggero("andrea", "bianchi", 12, 3, 1960, Sesso.M);
		p2.setColonna(1);
		p2.setFila(10);
		
		p3 = new Passeggero("andrea", "rossi", 12, 3, 1960, Sesso.M);
		p3.setColonna(5);
		p3.setFila(10);
		
		p4 = new Passeggero("stefania", "blu", 4,1, 1984, Sesso.F);
		p4.setColonna(1);
		p4.setFila(1);
	}

	@Test
	public final void testPostiComparator() {
		int cmp = PasseggeroComparator.POSTO_ORDER.compare(p1, p2);
		assertTrue("p1 prima di p2", cmp < 0);
		
		cmp = PasseggeroComparator.POSTO_ORDER.compare(p2, p1);
		assertTrue("p1 prima di p2", cmp > 0);
		
		cmp = PasseggeroComparator.POSTO_ORDER.compare(p2, p3);
		assertTrue("p2 prima di p3", cmp < 0);
		
		cmp = PasseggeroComparator.POSTO_ORDER.compare(p3, p2);
		assertTrue("p2 prima di p3", cmp > 0);
		
		cmp = PasseggeroComparator.POSTO_ORDER.compare(p1, p4);
		assertTrue("p1 prima di p4", cmp > 0);
	}
	
	@Test
	public final void testNomiComparator(){
		int cmp = PasseggeroComparator.NAME_ORDER.compare(p1, p2);
		assertTrue("p1 dopo di p2", cmp > 0);
		
		cmp = PasseggeroComparator.NAME_ORDER.compare(p2, p1);
		assertTrue("p1 dopo di p2", cmp < 0);
		
		cmp = PasseggeroComparator.NAME_ORDER.compare(p1, p3);
		assertTrue("p1 dopo di p3", cmp > 0);
		
		cmp = PasseggeroComparator.NAME_ORDER.compare(p3, p1);
		assertTrue("p1 dopo di p3", cmp < 0);
	}

}
