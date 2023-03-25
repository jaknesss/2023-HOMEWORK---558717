package diadia2023.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	Stanza stanza = new Stanza("atrio");
	Stanza adiacenteSud = new Stanza("aulaSud");
	Attrezzo attrezzo = new Attrezzo("osso", 2);
	
	
	@Test
	public void testAddAttrezzo() {
		for(int i = 0; i < 10; i++) {
			stanza.addAttrezzo(null);
		}
		assertFalse(stanza.addAttrezzo(null));
	}
	
	@Test
	public void testGetStanzaAdiacenteEsistente() {
		stanza.setStanzaAdiacente("sud", adiacenteSud);
		assertEquals(adiacenteSud, stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteNonEsistente() {
		stanza.setStanzaAdiacente("sud", adiacenteSud);
		assertEquals(null, stanza.getStanzaAdiacente("nord"));	
	}
	
	
	@Test
	public void testImpostaStanzaAdiacente() {
		Stanza stanzaCheRimpiazza = new Stanza("test");
		stanza.setStanzaAdiacente("sud", adiacenteSud);
		stanza.setStanzaAdiacente("sud", stanzaCheRimpiazza);
		assertEquals(stanzaCheRimpiazza, stanza.getStanzaAdiacente("sud"));		
	}

}
