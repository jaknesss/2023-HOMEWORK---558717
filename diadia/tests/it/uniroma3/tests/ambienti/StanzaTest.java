package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private final String NOME_ATTREZZO = "AttrezzoTest";
	private final String NOME_STANZA = "StanzaTest";
	private final String NOME_STANZA_ADIACENTE = "StanzaAdiacente";
	private final String DIR_NORD = "nord";
	private Attrezzo attrezzo;
	private Stanza stanza;
 	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new Stanza(NOME_STANZA);
		this.attrezzo = new Attrezzo(NOME_ATTREZZO, 1);
	}

	@Test
	public void testSetStanzaAdiacenteSingola() {
		Stanza adiacente = new Stanza(NOME_STANZA_ADIACENTE);
		stanza.setStanzaAdiacente(Direzione.NORD, adiacente);
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testCambioStanzaAdiacente() {
		Stanza daCambiare = new Stanza("daCambiare");
		stanza.setStanzaAdiacente(Direzione.NORD, daCambiare);
		Stanza adiacente = new Stanza("nuovaAdiacente");
		stanza.setStanzaAdiacente(Direzione.NORD, adiacente);
		assertEquals(adiacente, stanza.getStanzaAdiacente(DIR_NORD));
	}
	
	
	@Test 
	public void testStanzaAdiacenteNonEsistente() {
		assertNull(stanza.getStanzaAdiacente(DIR_NORD));
	}
	
	
	@Test 
	public void testGetDirezioniTutteVuote() {
		for(Direzione dir : stanza.getStanzeAdiacenti().keySet())
			assertNull(dir);
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(attrezzo, stanza.getAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(stanza.hasAttrezzo(NOME_ATTREZZO));
	}
	
	
	@Test
	public void testHasAttrezzo() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	public void testHasAttrezzoInesistente() {
		Attrezzo attrezzoDaCercare = new Attrezzo("nonEsiste", 1);
		stanza.addAttrezzo(attrezzo);
		assertFalse(stanza.hasAttrezzo(attrezzoDaCercare.getNome()));
	}
	
	@Test
	public void testStanzaAdiacenteMaxAttrezzi() {
		Stanza ad1 = new Stanza("adiacente");
		stanza.setStanzaAdiacente(Direzione.NORD, ad1);
		ad1.addAttrezzo(attrezzo);
		ad1.addAttrezzo(new Attrezzo("ascia", 2));
		assertEquals(ad1, stanza.getStanzaAdiacenteMaxOggetti());
	}
	
	@Test
	public void testStanzaAdiacenteMinAttrezzi() {
		Stanza ad1 = new Stanza(NOME_STANZA_ADIACENTE);
		Stanza ad2 = new Stanza("secondaAd");
		stanza.setStanzaAdiacente(Direzione.NORD, ad1);
		stanza.setStanzaAdiacente(Direzione.EST, ad2);
		ad1.setStanzaAdiacente(Direzione.SUD, stanza);
		ad1.addAttrezzo(attrezzo);
		ad1.addAttrezzo(new Attrezzo("ascia", 1));
		assertEquals(ad2, stanza.getStanzaAdiacenteMinOggetti());
	}
	
}




