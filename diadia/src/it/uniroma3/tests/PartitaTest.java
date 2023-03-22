package it.uniroma3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

public class PartitaTest {

	Partita partita = new Partita();
	Labirinto lab = partita.getLabirinto();
	Giocatore giocatore = partita.getGiocatore();
	
	@Test
	public void testVinta() {
		lab.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testNonAncoraVinta() {
		lab.setStanzaCorrente(null);
		assertFalse(partita.vinta());
	}

	@Test
	public void testSetCfu() {
		giocatore.setCfu(42);
		assertEquals(42, giocatore.getCfu());
	}

	@Test
	public void testIsFinita() {
		giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}
}
