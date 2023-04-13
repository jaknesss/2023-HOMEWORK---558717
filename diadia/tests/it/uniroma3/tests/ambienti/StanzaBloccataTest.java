package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza corrente;
	private Attrezzo attrezzoChiave;
	private final String DIR_BLOCCATA = "nord";
	private final String NOME_STANZA = "ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	
	@BeforeEach
	void setUp(){
		stanza1 = new StanzaBloccata(NOME_STANZA, NOME_OGG_CHIAVE, DIR_BLOCCATA);
		stanza2 = new Stanza("Biblioteca");
		corrente = stanza1;
		stanza1.setStanzaAdiacente(DIR_BLOCCATA, stanza2);
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
	}

	@Test
	void testStanzaContieneOggChiave() {
		stanza1.addAttrezzo(attrezzoChiave);
		corrente = stanza1.getStanzaAdiacente(DIR_BLOCCATA); 
		assertEquals(stanza2, corrente);
	}
	
	@Test
	void testStanzaNonContieneOggChiave() {
		corrente = stanza1.getStanzaAdiacente(DIR_BLOCCATA);
		assertEquals(stanza1, corrente);
	}

}
