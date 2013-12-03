package xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.Aeroporto;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import util.Options;

public class XMLTest {

	private static List<Aeroporto> elencoAeroporti;
	@BeforeClass
	public static void setUp() throws Exception {
		//preparo lista di aeroporti da salvare su file
		elencoAeroporti = new LinkedList<Aeroporto>();
		elencoAeroporti.add(new Aeroporto(1, "Ancona"));
		elencoAeroporti.add(new Aeroporto(2, "Bari"));
		elencoAeroporti.add(new Aeroporto(3, "Bergamo"));
		elencoAeroporti.add(new Aeroporto(4, "Bologna"));
		elencoAeroporti.add(new Aeroporto(5, "Bolzano"));
		elencoAeroporti.add(new Aeroporto(6, "Cagliari"));
		elencoAeroporti.add(new Aeroporto(7, "Catania"));
		elencoAeroporti.add(new Aeroporto(8, "Catanzaro"));
		elencoAeroporti.add(new Aeroporto(9, "Firenze"));
		elencoAeroporti.add(new Aeroporto(10, "Foggia"));

		//inizializzo opzioni
		Options.initOptions();
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

	@Test
	public final void testCreateFlySmartDocumentListOfE() {
		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreate<Aeroporto>();
		
		assertNotNull("Impossibile creare l'oggetto XML Creator", XMLAeroporti);
		
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		
		assertNotNull("Impossibile creare DOM XML", d);
	}

	@Test
	public final void testPrintDocumentAndRead() {
		XMLCreate<Aeroporto> XMLAeroporti = new XMLCreate<Aeroporto>();
		Document d = XMLAeroporti.createFlySmartDocument(elencoAeroporti);
		
		try {
			XMLAeroporti.printDocument(d,"FileElencoAeroporti.xml");
		} catch (IOException e) {
			fail("Can't write XML to file");
		}
		
		//check if file exixts
		File f = new File(Options.aeroportiFileName);
		assertTrue("File XML Aeroporti non creato", !f.exists());
		
		//leggi da file e controlla
		List<Aeroporto> aeroporti = new LinkedList<Aeroporto>();
		XMLToObj<Aeroporto> parserXML = new XMLToObj<Aeroporto>(Aeroporto.class);
		aeroporti = parserXML.readObj("FileElencoAeroporti.xml");
		
		assertArrayEquals(elencoAeroporti.toArray(), aeroporti.toArray());
	}

}
