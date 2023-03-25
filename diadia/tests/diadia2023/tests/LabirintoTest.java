package diadia2023.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {

Labirinto lab = new Labirinto();
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza stanza = new Stanza("test");
		lab.setStanzaCorrente(stanza);
		assertEquals(stanza, lab.getStanzaCorrente());
	}

	@Test
	public void testGetStanzaVincente() {
		Stanza stanza = lab.getStanzaVincente();
		assertEquals(stanza, lab.getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaCorrenteNull() {
		lab.setStanzaCorrente(null);
		assertNull(lab.getStanzaCorrente());
	}

}
