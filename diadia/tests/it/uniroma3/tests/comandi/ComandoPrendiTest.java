package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {

	private final int PESO_OGGETTO = 1;
	private final int PESO_OGGETTO_MAX = 10;
	private final String NOME_STANZA = "atrio";
	private final String COMANDO = "prendi";
	private final String NOME_OGGETTO = "OggettoTest";
	private FabbricaDiComandi factory;
	private Comando comando;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Borsa borsa;
	private Attrezzo attrezzo;
	private LabirintoBuilder lab;
	private IO io;

	@BeforeEach
	void setUp() throws Exception {
		io = new IOConsole();
		lab = new LabirintoBuilder().addStanzaIniziale(NOME_STANZA);
		partita = new Partita(lab, io);
		factory = new FabbricaDiComandiRiflessiva();
		attrezzo = new Attrezzo(NOME_OGGETTO, PESO_OGGETTO);
		borsa = partita.getGiocatore().getBorsa();
		stanzaCorrente = lab.getUltimaStanza();
		stanzaCorrente.addAttrezzo(attrezzo);
	}

	@Test
	void testPrendiOggetto() {
		assertTrue(stanzaCorrente.hasAttrezzo(attrezzo.getNome()));
		eseguiComando(COMANDO, NOME_OGGETTO);
		assertFalse(stanzaCorrente.hasAttrezzo(attrezzo.getNome()));
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	void testPrendiOggettoConBorsaPiena() {
		Attrezzo troppoPesante = new Attrezzo("troppoPesante", PESO_OGGETTO_MAX);
		assertTrue(borsa.addAttrezzo(attrezzo));
		assertTrue(stanzaCorrente.addAttrezzo(troppoPesante));
		eseguiComando(COMANDO, troppoPesante.getNome());
		assertTrue(stanzaCorrente.hasAttrezzo(troppoPesante.getNome()));
		assertFalse(borsa.hasAttrezzo(troppoPesante.getNome()));
	}

	private void eseguiComando(String COMANDO, String NOME_OGGETTO) {
		comando = factory.costruisciComando(COMANDO + " " + NOME_OGGETTO);
		comando.esegui(partita, io);
	}

}
