package it.uniroma3.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {

	private Partita partita;
	private Giocatore giocatore;
	private Labirinto lab;

	@BeforeEach
	public void setUp() {
		partita = new Partita();
		giocatore = partita.getGiocatore();
		lab = partita.getLabirinto();
	}

	@Test
	public void testVinta() {
		partita.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(partita.isVinta());
	}

	@Test
	public void testNonAncoraVinta() {
		assertFalse(partita.isVinta());
	}
	
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(42);
		assertEquals(42, giocatore.getCfu());
	}

	@Test
	public void testIsFinitaCfuEsauriti() {
		giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testIsFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza test = new Stanza("stanzaTest");
		partita.setStanzaCorrente(test);
		assertEquals(test, partita.getStanzaCorrente());
	}

	
	
}