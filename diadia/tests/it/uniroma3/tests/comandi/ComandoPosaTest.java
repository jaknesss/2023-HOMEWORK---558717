package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {
	private final int MAX_CAPIENZA_STANZA = 10;
	private final int PESO_OGGETTO = 1;
	private final String COMANDO = "posa"; 
	private final String NOME_OGGETTO = "OggettoTest"; 
	private FabbricaDiComandi factory;
	private Comando comando;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Borsa borsa;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() {
		partita = new Partita();
		factory = new FabbricaDiComandiFisarmonica();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO_OGGETTO);
		borsa = partita.getGiocatore().getBorsa();
		stanzaCorrente = partita.getStanzaCorrente();
	}

	@Test
	void testPosaNessunOggetto() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));
		eseguiComando(COMANDO, null);
		assertEquals(attrezzo, borsa.getAttrezzo(NOME_OGGETTO));
	}
	
	
	@Test
	void testPosaOggetto() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertFalse(borsa.hasAttrezzo(NOME_OGGETTO));
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
	}
	
	
	@Test
	void testPosaTroppiOggetti() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));	
		riempiStanzaDiOggetti();
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));		
	}
	
	private void riempiStanzaDiOggetti() {
		for(int i = 0; i < MAX_CAPIENZA_STANZA; i++) 
			stanzaCorrente.addAttrezzo(attrezzo);
	}
	
	private void eseguiComando(String COMANDO, String NOME_OGGETTO) {
		comando = factory.costruisciComando(COMANDO+" "+NOME_OGGETTO);
		comando.esegui(partita);
	}
	
}
