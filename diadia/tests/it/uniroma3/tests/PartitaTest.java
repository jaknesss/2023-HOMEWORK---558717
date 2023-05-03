package it.uniroma3.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {

	private Partita partita;
	private Giocatore giocatore;
	private final String INGRESSO = "atrio";
	private final String USCITA = "biblioteca";
	private Labirinto lab;
	private IO io;

	@BeforeEach
	public void setUp() {
		io = new IOConsole();
		lab = new LabirintoBuilder()
				  .addStanzaIniziale(INGRESSO)
				  .addStanzaVincente(USCITA)
				  .addAdiacenza(INGRESSO, USCITA, "nord");
		partita = new Partita(lab, io);
		giocatore = partita.getGiocatore();
	}

	@Test
	public void testIsVinta() {
		lab.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(partita.isVinta());
	}

	@Test
	public void testNonAncoraVinta() {
		lab.setStanzaCorrente(lab.getStanzaIniziale());
		assertFalse(partita.isVinta());
	}
	
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(42);
		assertEquals(42, giocatore.getCfu());
	}

	@Test
	public void testIsFinita_CfuEsauriti() {
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
		Stanza vincente = lab.getStanzaVincente();
		System.out.println(vincente);
		lab.setStanzaCorrente(vincente);
		assertEquals(vincente, partita.getStanzaCorrente());
	}

	
	
}
