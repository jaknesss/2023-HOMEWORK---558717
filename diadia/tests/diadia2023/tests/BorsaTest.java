package diadia2023.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private final int MAX_ATTREZZI = 10;
	private final String ATTREZZO = "attrezzoTest";
	private Borsa borsa;

	@BeforeEach
	void setUp() {
		borsa = new Borsa();
	}

	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo1 = new Attrezzo(ATTREZZO, 1);
		borsa.addAttrezzo(attrezzo1);
		assertEquals(attrezzo1, borsa.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testAddAttrezzoMaxCapacita() {
		for (int i = 0; i < MAX_ATTREZZI; i++) {
			assertTrue(borsa.addAttrezzo(new Attrezzo(ATTREZZO, 1)));
		}
		assertFalse(borsa.addAttrezzo(new Attrezzo(ATTREZZO, 1)));
		
	}

	@Test
	public void testAddAttrezzoMaxPeso() {
		for (int i = 0; i < MAX_ATTREZZI - 1; i++) {
			assertTrue(borsa.addAttrezzo(new Attrezzo(ATTREZZO, 1)));
		}
		assertFalse(borsa.addAttrezzo(new Attrezzo(ATTREZZO, 2)));
	}

	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertFalse(borsa.removeAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.removeAttrezzo(attrezzo));
	}

	@Test
	public void testHasAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(ATTREZZO));
	}

	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(borsa.hasAttrezzo(ATTREZZO));
	}

	@Test
	public void testRemoveAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		Attrezzo attrezzo1 = new Attrezzo("nonAggiunto", 1);
		borsa.addAttrezzo(attrezzo);
		assertFalse(borsa.removeAttrezzo(attrezzo1));
	}	
}
