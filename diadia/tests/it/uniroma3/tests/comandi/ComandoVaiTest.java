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

class ComandoVaiTest {

	private Partita partita;
	private FabbricaDiComandi factory;
	private Comando comando;
	private Stanza stanzaIniziale;
	private final String NOME_COMANDO = "vai";
	private final String NOME_OMANDO_SCONOSCIUTO = "test";
	private final String DIREZIONE = "sud";
	private final String DIREZIONE_SCONOSCIUTA = "sud-est";
	private IO io;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		partita = new Partita(io);
		stanzaIniziale = partita.getLabirinto().getStanzaIniziale();
		factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testNomeComandoCorretto() {
		eseguiComando(NOME_COMANDO, DIREZIONE);
		assertEquals(NOME_COMANDO, comando.getNome());
	}

	@Test
	void testParametroCorretto() {
		eseguiComando(NOME_COMANDO, DIREZIONE);
		assertEquals(DIREZIONE, comando.getParam());
	}

	@Test
	void testComandoNullo() {
		eseguiComando(null, DIREZIONE);
		assertEquals(stanzaIniziale.getNome(), partita.getStanzaCorrente().getNome());
	}

	@Test
	void testComandoNonValido() {
		eseguiComando(NOME_OMANDO_SCONOSCIUTO, DIREZIONE);
		assertEquals(stanzaIniziale.getNome(), partita.getStanzaCorrente().getNome());
	}

	@Test
	void testDirezioneNulla() {
		eseguiComando(NOME_COMANDO, null);
		assertEquals(stanzaIniziale.getNome(), partita.getStanzaCorrente().getNome());
	}

	@Test
	void testDirezioneSconosciuta() {
		eseguiComando(NOME_COMANDO, DIREZIONE_SCONOSCIUTA);
		assertEquals(stanzaIniziale.getNome(), partita.getStanzaCorrente().getNome());
	}

	@Test
	void testDirezioneValida() {
		eseguiComando(NOME_COMANDO, DIREZIONE);
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome(), partita.getStanzaCorrente().getNome());
	}

	private void eseguiComando(String nomeComando, String parametro) {
		comando = factory.costruisciComando(nomeComando + " " + parametro);
		comando.esegui(partita, io);
	}
}
