package diadia2023.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private final int MAX_ATTREZZI = 10;
	private final int MAX_DIREZIONI = 4;
	private final String ATTREZZO = "AttrezzoTest";
	private final String STANZA = "StanzaTest";
	private final String STANZA_ADIACENTE = "StanzaAdiacente";
	protected Stanza stanza;
 	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new Stanza(STANZA);
	}

	@Test
	public void testSetStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, STANZA_ADIACENTE, "nord");
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testCambioStanzaAdiacente() {
		creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", "nord");
		Stanza adiacente = creaStanzaEdImpostaAdiacente(this.stanza, "daCambiare", "nord");
		assertEquals(adiacente, stanza.getStanzaAdiacente("nord"));
	}
	
	
	@Test 
	public void testStanzaAdiacenteNonEsistente() {
		assertNull(stanza.getStanzaAdiacente("nord"));
	}
	
	
	@Test 
	public void testStanzaAdiacenteDirezioneNonEsistente() {
		creaStanzaEdImpostaAdiacente(this.stanza, STANZA_ADIACENTE, "nord");
		assertNull(stanza.getStanzaAdiacente("nord-est"));
	}
	
	@Test 
	public void testGetDirezioniTutteVuote() {
		assertArrayEquals(new String[MAX_DIREZIONI], stanza.getDirezioni());
	}
	
	@Test 
	public void testGetDirezioneSingola() {
		creaStanzaEdImpostaAdiacente(this.stanza, STANZA_ADIACENTE, "nord");
		String[] direzioni = new String[MAX_DIREZIONI];
		direzioni[0] = "nord";
		assertArrayEquals(direzioni, stanza.getDirezioni());
	}
	
	
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(attrezzo, stanza.getAttrezzo(ATTREZZO));
	}
	
	
	@Test
	public void testAddAttrezzoMaxCapienza() {
		for(int i = 0; i < MAX_ATTREZZI; i++) {
			Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);			
			assertTrue(stanza.addAttrezzo(attrezzo));
		}
		Attrezzo diTroppo = new Attrezzo("diTroppo", 2);
		assertFalse(stanza.addAttrezzo(diTroppo));
	}
	
	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(stanza.hasAttrezzo(ATTREZZO));
	}
	
	
	@Test
	public void testHasAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		stanza.addAttrezzo(attrezzo);
		assertFalse(stanza.hasAttrezzo("nonEsiste"));
	}
	
	@Test
	private Stanza creaStanzaEdImpostaAdiacente(Stanza diPartenza, String nomeStanza, String direzione){
		Stanza adiacente = new Stanza(nomeStanza);
		diPartenza.setStanzaAdiacente(direzione, adiacente);
		return adiacente;
	}		
		
}




