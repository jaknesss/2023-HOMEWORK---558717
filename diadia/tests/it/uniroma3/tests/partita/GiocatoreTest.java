package it.uniroma3.tests.partita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private final int MAX_CFU = 10;
	private final int PESO = 1;
	private final String ATTREZZO = "attrezzoTest";
	private Giocatore giocatore;
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		giocatore = new Giocatore();
		attrezzo = new Attrezzo(ATTREZZO, PESO);
		borsa = giocatore.getBorsa();			
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(giocatore.getCfu()-2);
		assertEquals(MAX_CFU-2, giocatore.getCfu());
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		giocatore.addAttrezzo(attrezzo);
		assertEquals(attrezzo, borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		Attrezzo attrezzoNonAggiunto = new Attrezzo("nonAggiunto", PESO);
		assertFalse(giocatore.removeAttrezzo(attrezzoNonAggiunto.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzoSingolo() {
		giocatore.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
		assertTrue(giocatore.removeAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzoInesistente() {
		Attrezzo attrezzoNonAggiunto = new Attrezzo("nonAggiunto", 1);
		giocatore.addAttrezzo(attrezzo);
		assertFalse(giocatore.removeAttrezzo(attrezzoNonAggiunto.getNome()));
	}
	
}
