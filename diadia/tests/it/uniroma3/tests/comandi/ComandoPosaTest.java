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
	private FabbricaDiComandi factory;
	private Comando comando;
	private Partita partita;
	private Borsa borsa;
	private Attrezzo attrezzo;
	private final int MAX_CAPIENZA_STANZA = 10;
	private final int PESO = 1;
	private final String COMANDO = "posa "; 
	private final String COMANDO_SCONOSCIUTO = "test "; 
	private final String NOME_OGGETTO = "OggettoTest"; 
	
	@BeforeEach
	void setUp() {
		partita = new Partita();
		factory = new FabbricaDiComandiFisarmonica();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO);
		borsa = partita.getGiocatore().getBorsa();
	}

	@Test
	void testPosaNessunOggetto() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));
		comando = factory.costruisciComando(COMANDO + null);
		comando.esegui(partita);
		assertEquals(attrezzo, borsa.getAttrezzo(NOME_OGGETTO));
	}
	
	
	@Test
	void testPosaOggetto() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));
		comando = factory.costruisciComando(COMANDO + NOME_OGGETTO);
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo(NOME_OGGETTO));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(NOME_OGGETTO));
	}
	
	
	@Test
	void testPosaTroppiOggetti() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));	
		riempiStanzaDiOggetti();
		comando = factory.costruisciComando(COMANDO + NOME_OGGETTO);
		comando.esegui(partita);
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));		
	}
	
	private void riempiStanzaDiOggetti() {
		for(int i = 0; i < MAX_CAPIENZA_STANZA; i++) 
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}
	
}
