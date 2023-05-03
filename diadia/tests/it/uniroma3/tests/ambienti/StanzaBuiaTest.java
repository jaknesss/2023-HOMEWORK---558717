package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzoChiave;
	private Attrezzo attrezzoGenerico;
	private final String NOME_STANZA = "ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	@BeforeEach
	void setUp(){
		stanza = new StanzaBuia(NOME_STANZA, attrezzoChiave.getNome());
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
		attrezzoGenerico = new Attrezzo("attrezzoGenerico", PESO_ATTR);
	}
	

	@Test
	void testStanzaNonContieneOggChiave() {
		stanza.addAttrezzo(attrezzoGenerico);
		assertFalse(stanza.hasAttrezzo(attrezzoChiave.getNome()));
	}
	
	@Test
	void testStanzaContieneOggChiave() {
		assertTrue(stanza.addAttrezzo(attrezzoChiave));
		assertTrue(stanza.hasAttrezzo(attrezzoChiave.getNome()));
		assertEquals(attrezzoChiave.getNome(),
				 	 stanza.getAttrezzi().get(attrezzoChiave.getNome()));
	}
}
