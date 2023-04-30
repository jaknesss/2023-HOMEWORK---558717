package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
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
	private IO io;
	
	@BeforeEach
	void setUp(){
		io = new IOConsole();
		stanza = new StanzaBuia(NOME_STANZA, attrezzoChiave);
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
		attrezzoGenerico = new Attrezzo("attrezzo", PESO_ATTR);
	}
	

	@Test
	void testStanzaNonContieneOggChiave() {
		stanza.addAttrezzo(attrezzoGenerico, io);
		assertFalse(stanza.hasAttrezzo(attrezzoChiave));
	}
	
	@Test
	void testStanzaContieneOggChiave() {
		stanza.addAttrezzo(attrezzoChiave, io);
		assertTrue(stanza.hasAttrezzo(attrezzoChiave));
		assertTrue(attrezzoChiave.getNome().equals(NOME_OGG_CHIAVE));
	}
}
