package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

class ComandoVaiTest {

	private FabbricaDiComandi factory;
	private Comando comando;
	private IO io;
	private LabirintoBuilder lab;
	private Partita partita;
	private Stanza stanzaCorrente;
	private Stanza stanzaIniziale;
	private final String NOME_COMANDO = "vai";
	private final String NOME_COMANDO_SCONOSCIUTO = "test";
	private final String DIREZIONE = "sud";
	private final String DIREZIONE_SCONOSCIUTA = "sud-est";

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		lab = new LabirintoBuilder()
				  .addStanzaIniziale("atrio")
				  .addStanza("biblioteca")
				  .addAdiacenza("atrio", "biblioteca", DIREZIONE);
		partita = new Partita(lab, io);
		stanzaIniziale = lab.getStanzaIniziale();
		stanzaCorrente = lab.getUltimaStanza();
		factory = new FabbricaDiComandiRiflessiva();
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
		eseguiComando("nullo", DIREZIONE);
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testComandoNonValido() {
		eseguiComando(NOME_COMANDO_SCONOSCIUTO, DIREZIONE);
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testDirezioneNulla() {
		eseguiComando(NOME_COMANDO, "ovest");
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testDirezioneSconosciuta() {
		eseguiComando(NOME_COMANDO, DIREZIONE_SCONOSCIUTA);
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente(DIREZIONE));
	}

	@Test
	void testDirezioneValida() {
		eseguiComando(NOME_COMANDO, DIREZIONE);
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente(DIREZIONE));
	}

	private void eseguiComando(String nomeComando, String parametro) {
		comando = factory.costruisciComando(nomeComando + " " + parametro);
		comando.esegui(partita, io);
	}
}
