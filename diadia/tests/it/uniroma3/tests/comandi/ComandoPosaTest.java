package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
	private LabirintoBuilder lab;
	private IO io;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		lab = new LabirintoBuilder().addStanzaIniziale("atrio");
		partita = new Partita(lab, io);
		stanzaCorrente = partita.getStanzaCorrente();
		factory = new FabbricaDiComandiRiflessiva();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO);
		borsa = partita.getGiocatore().getBorsa();
		borsa.addAttrezzo(attrezzo);
	}

	@Test
	void testPosaOggettoNonPresenteNellaBorsa() {
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
		eseguiComando(COMANDO, "oggettoInesitstente");
		assertEquals(attrezzo, borsa.getAttrezzo(attrezzo.getNome()));
	}
	
	
	@Test
	void testPosaOggetto() {
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
		eseguiComando(COMANDO, attrezzo.getNome());
		assertFalse(borsa.hasAttrezzo(attrezzo.getNome()));
		assertTrue(stanzaCorrente.hasAttrezzo(attrezzo.getNome()));
	}
	
	
	private void eseguiComando(String nomeComando, String nomeOggetto) {
		comando = factory.costruisciComando(nomeComando + " " + nomeOggetto);
		comando.esegui(partita, io);
	}
	
}
