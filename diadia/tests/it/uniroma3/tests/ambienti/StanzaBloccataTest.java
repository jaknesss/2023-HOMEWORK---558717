package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	private Attrezzo attrezzoChiave;
	private final String DIR_BLOCCATA = "nord";
	private final String NOME_STANZA = "ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	
	@BeforeEach
	void setUp(){
		stanzaIniziale = new StanzaBloccata(NOME_STANZA, NOME_OGG_CHIAVE, DIR_BLOCCATA);
		stanzaVincente = new Stanza("Biblioteca");
		stanzaCorrente = stanzaIniziale;
		stanzaIniziale.setStanzaAdiacente(DIR_BLOCCATA, stanzaVincente);
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
	}

	@Test
	void testStanzaContieneOggChiave() {
		stanzaIniziale.addAttrezzo(attrezzoChiave);
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA); 
		assertEquals(stanzaVincente, stanzaCorrente);
	}
	
	@Test
	void testStanzaNonContieneOggChiave() {
		stanzaCorrente = stanzaIniziale.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanzaIniziale, stanzaCorrente);
	}

}
