package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia ingresso;
	private Attrezzo attrezzoChiave;
	private final String NOME_STANZA = "ingresso";
	private final String NOME_OGG_CHIAVE = "lanterna";
	private final int PESO_ATTR = 1;
	
	@BeforeEach
	void setUp(){
		ingresso = new StanzaBuia(NOME_STANZA, NOME_OGG_CHIAVE);
		attrezzoChiave = new Attrezzo(NOME_OGG_CHIAVE, PESO_ATTR);
	}
	

	@Test
	void testStanzaNonContieneOggChiave() {
		assertFalse(ingresso.hasAttrezzo(NOME_OGG_CHIAVE));
	}
	
	@Test
	void testStanzaContieneOggChiave() {
		ingresso.addAttrezzo(attrezzoChiave);
		assertTrue(ingresso.hasAttrezzo(NOME_OGG_CHIAVE));
		assertTrue(attrezzoChiave.getNome().equals(ingresso.getNomeAttrezzoChiave()));
	}
	
	@Test
	void testStanzaContieneOggChiaveSbagliato() {
		Attrezzo attrSbagliato = new Attrezzo("sbagliato", PESO_ATTR);
		ingresso.addAttrezzo(attrSbagliato);
		assertTrue(ingresso.hasAttrezzo(attrSbagliato.getNome()));
		assertFalse(attrSbagliato.getNome().equals(ingresso.getNomeAttrezzoChiave()));
	}
}
