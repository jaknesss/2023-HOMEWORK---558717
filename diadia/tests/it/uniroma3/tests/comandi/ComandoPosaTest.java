package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {

	private final String COMANDO = "posa"; 
	private final String NOME_OGGETTO = "attrezzo"; 
	private final int PESO = 1;
	private FabbricaDiComandi factory;
	private Comando comando;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Borsa borsa;
	private Attrezzo attrezzo;
	private IO io;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		partita = new Partita(io);
		factory = new FabbricaDiComandiFisarmonica();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO);
		borsa = partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(attrezzo);
		stanzaCorrente = partita.getStanzaCorrente();
	}

	@Test
	void testPosaOggettoNonPresenteNellaBorsa() {
		assertTrue(borsa.hasAttrezzo(attrezzo));
		eseguiComando(COMANDO, "oggettoInesitstente");
		assertEquals(attrezzo, borsa.getAttrezzo(attrezzo.getNome()));
	}
	
	
	@Test
	void testPosaOggetto() {
		assertTrue(borsa.hasAttrezzo(attrezzo));
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertFalse(borsa.hasAttrezzo(attrezzo));
		assertTrue(stanzaCorrente.hasAttrezzo(attrezzo.getNome()));
	}
	
	
	private void eseguiComando(String nomeComando, String nomeOggetto) {
		comando = factory.costruisciComando(nomeComando + " " + nomeOggetto);
		comando.esegui(partita, io);
	}
	
}
