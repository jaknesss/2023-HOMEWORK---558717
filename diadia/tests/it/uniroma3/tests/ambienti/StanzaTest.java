package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private final String ATTREZZO = "AttrezzoTest";
	private final String STANZA = "StanzaTest";
	private final String STANZA_ADIACENTE = "StanzaAdiacente";
	private final String DIR_NORD = "nord";
	private Attrezzo attrezzo;
	private Stanza stanza;
	private IO io;
 	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new Stanza(STANZA);
		this.attrezzo = new Attrezzo(ATTREZZO, 1);
		this.io = new IOConsole();
	}

	@Test
	public void testSetStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, STANZA_ADIACENTE, DIR_NORD);
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testCambioStanzaAdiacente() {
		creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", "nord");
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", DIR_NORD);
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	
	@Test 
	public void testStanzaAdiacenteNonEsistente() {
		assertNull(stanza.getStanzaAdiacente(DIR_NORD));
	}
	
	
	@Test 
	public void testStanzaAdiacenteDirezioneNonEsistente() {
		creaStanzaEdImpostaAdiacente(this.stanza, STANZA_ADIACENTE, DIR_NORD);
		assertNull(stanza.getStanzaAdiacente("nord-est"));
	}
	
	@Test 
	public void testGetDirezioniTutteVuote() {
		for(String dir : this.stanza.getDirezioni())
			assertNull(dir);
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(attrezzo, io));
		assertEquals(attrezzo, stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(stanza.hasAttrezzo(attrezzo));
	}
	
	
	@Test
	public void testHasAttrezzo() {
		stanza.addAttrezzo(attrezzo, io);
		assertTrue(stanza.hasAttrezzo(attrezzo));
	}
	
	@Test
	public void testHasAttrezzoInesistente() {
		Attrezzo attrezzoDaCercare = new Attrezzo("nonEsiste", 1);
		stanza.addAttrezzo(attrezzo, io);
		assertFalse(stanza.hasAttrezzo(attrezzoDaCercare));
	}
	
	private Stanza creaStanzaEdImpostaAdiacente(Stanza diPartenza, String nomeStanza, String direzione){
		Stanza adiacente = new Stanza(nomeStanza);
		diPartenza.setStanzaAdiacente(direzione, adiacente);
		return adiacente;
	}		
		
}




