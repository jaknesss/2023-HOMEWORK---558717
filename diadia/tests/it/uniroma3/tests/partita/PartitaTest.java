package it.uniroma3.tests.partita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.diadia.io.IOConsole;

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
		lab = Labirinto.newBuilder()
				  .addStanzaIniziale(INGRESSO)
				  .addStanzaVincente(USCITA)
				  .addAdiacenza(INGRESSO, USCITA, "nord");
		partita = new Partita(lab, io);
		giocatore = partita.getGiocatore();
	}

	@Test
	public void testIsVinta() {
		partita.setStanzaCorrente(lab.getStanzaVincente());
		assertTrue(partita.isVinta());
	}

	@Test
	public void testNonAncoraVinta() {
		partita.setStanzaCorrente(lab.getStanzaIniziale());
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
		partita.setStanzaCorrente(vincente);
		assertEquals(vincente, partita.getStanzaCorrente());
	}

	
	
}
