package it.uniroma3.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private final String ATTREZZO = "attrezzoTest";
	private final int PESO = 1;
	private final int PESO_BORSA = 1;
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		borsa = new Borsa(PESO_BORSA);
		attrezzo = new Attrezzo(ATTREZZO, 1);
	}

	@Test
	public void testAddAttrezzoSingolo() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, borsa.getAttrezzo(ATTREZZO));
	}

	@Test
	public void testAddAttrezzoMaxPeso() {
		assertTrue(borsa.addAttrezzo(new Attrezzo(ATTREZZO, PESO)));
		assertFalse(borsa.addAttrezzo(new Attrezzo("troppoPesante", 1)));
	}

	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		Attrezzo attrezzoNonAggiunto= new Attrezzo("nonAggiunto", PESO);
		assertFalse(borsa.removeAttrezzo(attrezzoNonAggiunto));
	}

	@Test
	public void testRemoveAttrezzoSingolo() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.removeAttrezzo(attrezzo));
	}

	@Test
	public void testHasAttrezzo() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(attrezzo));
	}

	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(borsa.hasAttrezzo(attrezzo));
	}

	@Test
	public void testRemoveAttrezzoInesistente() {
		Attrezzo attrezzoDaRimuovere = new Attrezzo("nonAggiunto", PESO);
		borsa.addAttrezzo(attrezzo);
		assertFalse(borsa.removeAttrezzo(attrezzoDaRimuovere));
	}	
}
