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

class ComandoPrendiTest {
	private final int MAX_CAPIENZA_BORSA = 10;
	private final int PESO_OGGETTO = 1;
	private final String COMANDO = "prendi";
	private final String NOME_OGGETTO = "OggettoTest";
	private FabbricaDiComandi factory;
	private Comando comando;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Borsa borsa;
	private Attrezzo attrezzo;
	private IO io;

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		partita = new Partita(io);
		factory = new FabbricaDiComandiFisarmonica();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO_OGGETTO);
		borsa = partita.getGiocatore().getBorsa();
		stanzaCorrente = partita.getStanzaCorrente();
		stanzaCorrente.addAttrezzo(attrezzo, io);
	}

	@Test
	void testNonPrendiOggetto() {
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		eseguiComando(COMANDO, null);
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		assertFalse(borsa.hasAttrezzo(NOME_OGGETTO));
	}

	@Test
	void testPrendiOggetto() {
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertFalse(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		assertTrue(borsa.hasAttrezzo(NOME_OGGETTO));
	}

	@Test
	void testPrendiOggettoConBorsaPiena() {
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		riempiBorsa();
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertTrue(stanzaCorrente.hasAttrezzo(NOME_OGGETTO));
		assertFalse(borsa.hasAttrezzo(NOME_OGGETTO));
	}

	private void riempiBorsa() {
		Attrezzo fill = new Attrezzo("filler", PESO_OGGETTO);
		for (int i = 0; i < MAX_CAPIENZA_BORSA; i++)
			borsa.addAttrezzo(fill);
	}

	private void eseguiComando(String COMANDO, String NOME_OGGETTO) {
		comando = factory.costruisciComando(COMANDO + " " + NOME_OGGETTO);
		comando.esegui(partita, io);
	}

}
