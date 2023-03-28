package diadia2023.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {

	private Labirinto lab;
	
	@BeforeEach
	void setUp() {
		lab = new Labirinto();
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("biblioteca", lab.getStanzaVincente());
	}

	@Test
	public void testGetStanzaIngresso() {
		assertEquals("ingresso", lab.getStanzaVincente());
	}

}
