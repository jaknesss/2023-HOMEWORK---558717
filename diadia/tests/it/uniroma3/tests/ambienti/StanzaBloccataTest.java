package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Attrezzo attrezzoChiave;
	private IO io;
	private final String DIR_BLOCCATA = "nord";
	private final String NOME_STANZA = "Ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	
	@BeforeEach
	void setUp(){
		stanzaIniziale = new StanzaBloccata(NOME_STANZA, NOME_OGG_CHIAVE, DIR_BLOCCATA);
		stanzaVincente = new Stanza("Biblioteca");
		stanzaCorrente = stanzaIniziale;
		stanzaIniziale.setStanzaAdiacente(DIR_BLOCCATA, stanzaVincente);
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
		io = new IOConsole();
	}

	@Test
	void testStanzaContieneOggChiave() {
		stanzaIniziale.addAttrezzo(attrezzoChiave, io);
		assertTrue(stanzaIniziale.hasAttrezzo(NOME_OGG_CHIAVE));
		assertTrue(attrezzoChiave.getNome().equals(stanzaIniziale.getNomeAttrezzoChiave()));
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanzaVincente, stanzaCorrente);
	}
	
	@Test
	void testStanzaContieneOggChiaveSbagliato() {
		Attrezzo attrSbagliato = new Attrezzo("sbagliato", PESO_ATTR);
		stanzaIniziale.addAttrezzo(attrSbagliato, io);
		assertTrue(stanzaIniziale.hasAttrezzo(attrSbagliato.getNome()));
		assertFalse(attrSbagliato.getNome().equals(stanzaIniziale.getNomeAttrezzoChiave()));
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanzaIniziale, stanzaCorrente);
	}
	
	@Test
	void testStanzaNonContieneOggChiave() {
		assertFalse(stanzaIniziale.hasAttrezzo(NOME_OGG_CHIAVE));
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanzaIniziale, stanzaCorrente);
	}

}
