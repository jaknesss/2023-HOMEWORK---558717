package diadia2023.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

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
