package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private Stanza ingresso;
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
		assertNull(ingresso.getDescrizione());
	}
	
	@Test
	void testStanzaContieneOggChiave() {
		ingresso.addAttrezzo(attrezzoChiave);
		System.out.println(ingresso.getDescrizione());
	}
	
	@Test
	void testStanzaContieneOggChiaveSbagliato() {
		Attrezzo attrSbagliato = new Attrezzo("sbagliato", PESO_ATTR);
		ingresso.addAttrezzo(attrSbagliato);
		assertNull(ingresso.getDescrizione());
	}
}
