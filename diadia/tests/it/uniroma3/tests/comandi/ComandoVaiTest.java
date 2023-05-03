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
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private final String NOME_COMANDO = "vai";
	private final String NOME_COMANDO_SCONOSCIUTO = "test";
	private final String DIREZIONE = "sud";
	private final String DIREZIONE_SCONOSCIUTA = "sud-est";
	private FabbricaDiComandi factory;
	private Comando comando;
	private IO io;
	private Partita partita;
	private Stanza stanzaIniziale;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		partita = new Partita(new LabirintoBuilder(), io);
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
		eseguiComando(NOME_COMANDO_SCONOSCIUTO, DIREZIONE);
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
		System.out.println(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome());
		eseguiComando(NOME_COMANDO, DIREZIONE);
		System.out.println(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome());
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome(), partita.getStanzaCorrente().getNome());
	}

	private void eseguiComando(String nomeComando, String parametro) {
		System.out.println(nomeComando);
		System.out.println(parametro);
		comando = factory.costruisciComando(nomeComando + " " + parametro);
		comando.esegui(partita, io);
	}
}
