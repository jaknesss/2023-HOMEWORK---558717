package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, NOME_STANZA_ADIACENTE, DIR_NORD);
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testCambioStanzaAdiacente() {
		creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", DIR_NORD);
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", DIR_NORD);
		assertEquals(adiacente, stanza.getStanzaAdiacente(DIR_NORD));
	}
	
	
	@Test 
	public void testStanzaAdiacenteNonEsistente() {
		assertNull(stanza.getStanzaAdiacente(DIR_NORD));
	}
	
	
	@Test 
	public void testStanzaAdiacenteDirezioneNonEsistente() {
		creaStanzaEdImpostaAdiacente(this.stanza, NOME_STANZA_ADIACENTE, DIR_NORD);
		assertNull(stanza.getStanzaAdiacente("nord-est"));
	}
	
	@Test 
	public void testGetDirezioniTutteVuote() {
		for(String dir : stanza.getStanzeAdiacenti().keySet())
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
	
	private Stanza creaStanzaEdImpostaAdiacente(Stanza diPartenza, String nomeStanza, String direzione){
		Stanza adiacente = new Stanza(nomeStanza);
		diPartenza.setStanzaAdiacente(direzione, adiacente);
		return adiacente;
	}		
		
}




