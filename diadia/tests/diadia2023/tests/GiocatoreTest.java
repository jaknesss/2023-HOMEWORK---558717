package diadia2023.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private final int MAX_CFU = 20;
	private final int MAX_ATTREZZI = 10;
	private final String ATTREZZO = "attrezzoTest";
	private Giocatore giocatore;
	private Borsa borsa;
	
	@BeforeEach
	public void setUp() {
		giocatore = new Giocatore();
		borsa = giocatore.getBorsa();			
	}
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(giocatore.getCfu()-2);
		assertEquals(MAX_CFU-2, giocatore.getCfu());
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo1 = new Attrezzo(ATTREZZO, 1);
		giocatore.addAttrezzo(attrezzo1);
		assertEquals(attrezzo1, borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testAddAttrezzoMaxCapacita() {
		for(int i = 0; i < MAX_ATTREZZI; i++) {
			assertTrue(giocatore.addAttrezzo(new Attrezzo(ATTREZZO, 1)));			
		}
		assertFalse(giocatore.addAttrezzo(new Attrezzo(ATTREZZO, 1)));
	}
	
	@Test
	public void testAddAttrezzoMaxPeso() {
		for(int i = 0; i < MAX_ATTREZZI-1; i++) {
			assertTrue(giocatore.addAttrezzo(new Attrezzo(ATTREZZO, 1)));			
		}
		assertFalse(giocatore.addAttrezzo(new Attrezzo(ATTREZZO, 2)));
	}
	
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertFalse(giocatore.removeAttrezzo(null));
	}
	
	@Test
	public void testRemoveAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		giocatore.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(ATTREZZO));
		assertTrue(giocatore.removeAttrezzo(attrezzo));
	}
	
	@Test
	public void testRemoveAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		Attrezzo attrezzo1 = new Attrezzo("nonAggiunto", 1);
		giocatore.addAttrezzo(attrezzo);
		assertFalse(giocatore.removeAttrezzo(attrezzo1));
	}
	
}
