package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Attrezzo attrezzoChiave;
	private final String DIR_BLOCCATA = "nord";
	private final String NOME_STANZA = "Ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	
	@BeforeEach
	void setUp(){
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
		stanzaIniziale = new StanzaBloccata(NOME_STANZA, DIR_BLOCCATA, attrezzoChiave.getNome());
		stanzaVincente = new Stanza("Biblioteca");
		stanzaCorrente = stanzaIniziale;
		stanzaIniziale.setStanzaAdiacente(DIR_BLOCCATA, stanzaVincente);
	}

	@Test
	void testStanzaContieneOggChiave() {
		stanzaIniziale.addAttrezzo(attrezzoChiave);
		assertTrue(stanzaIniziale.hasAttrezzo(NOME_OGG_CHIAVE));
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanzaVincente, stanzaCorrente);
	}
	
	@Test
	void testStanzaContieneOggChiaveSbagliato() {
		Attrezzo attrSbagliato = new Attrezzo("sbagliato", PESO_ATTR);
		stanzaIniziale.addAttrezzo(attrSbagliato);
		assertTrue(stanzaIniziale.hasAttrezzo(attrSbagliato.getNome()));
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
