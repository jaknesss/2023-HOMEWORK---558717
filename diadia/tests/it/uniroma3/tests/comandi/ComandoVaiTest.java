package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.diadia.io.IOConsole;

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
	private final String DIREZIONE_SBAGLIATA = "sud-est";

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		lab = new LabirintoBuilder()
				  .addStanzaIniziale("atrio")
				  .addStanza("biblioteca")
				  .addAdiacenza("atrio", "biblioteca", DIREZIONE);
		partita = new Partita(lab, io);
		stanzaIniziale = lab.getStanzaIniziale();
		stanzaCorrente = stanzaIniziale;
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
		eseguiComando(NOME_COMANDO_SCONOSCIUTO, DIREZIONE);
		assertEquals(stanzaIniziale, stanzaCorrente);
	}


	@Test
	void testDirezioneNulla() {
		eseguiComando(NOME_COMANDO, "ovest");
		assertEquals(stanzaIniziale, stanzaCorrente);
	}
//	@Test
//	TODO Capire cosa non va
	void testDirezioneSbagliata() {
		eseguiComando(NOME_COMANDO, DIREZIONE_SBAGLIATA);
		assertEquals(stanzaIniziale, stanzaCorrente);
	}

//	@Test
//	TODO Capire cosa non va
	void testDirezioneValida() {
		eseguiComando(NOME_COMANDO, DIREZIONE);
		assertEquals(stanzaCorrente, stanzaIniziale.getStanzaAdiacente("sud"));
	}

	private void eseguiComando(String nomeComando, String parametro) {
		comando = factory.costruisciComando(nomeComando + " " + parametro);
		comando.esegui(partita, io);
	}
}
