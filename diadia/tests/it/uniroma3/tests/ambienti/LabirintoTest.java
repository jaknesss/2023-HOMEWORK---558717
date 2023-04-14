package it.uniroma3.tests.ambienti;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {

	private Labirinto lab;
	private IO io;
	
	@BeforeEach
	void setUp() {
		io = new IOConsole();
		lab = new Labirinto(io);
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", lab.getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaIngresso() {
		assertEquals("Atrio", lab.getStanzaIniziale().getNome());
	}

}
