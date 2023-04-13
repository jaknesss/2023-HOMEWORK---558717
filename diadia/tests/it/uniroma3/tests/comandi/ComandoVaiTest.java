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
	private final String COMANDO = "vai "; 
	private final String COMANDO_SCONOSCIUTO = "test "; 
	private final String DIREZIONE = "sud"; 
	private final String DIREZIONE_SCONOSCIUTA = "sud-est"; 
	private IO io;
	
	@BeforeEach
	void setUp() {
		partita = new Partita();
		stanzaIniziale = partita.getLabirinto().getStanzaIniziale();
		factory = new FabbricaDiComandiFisarmonica();
		io = new IOConsole();
	}
	
	@Test
	void testComandoNullo() {
		eseguiComando(null, DIREZIONE);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testComandoNonValido() {
		eseguiComando(COMANDO_SCONOSCIUTO, DIREZIONE);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testDirezioneNulla() {
		eseguiComando(COMANDO, null);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	
	@Test
	void testDirezioneSconosciuta() {
		eseguiComando(COMANDO, DIREZIONE_SCONOSCIUTA);
		assertEquals(stanzaIniziale.getNome(), 
				 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testComandoDirezioneValidi() {
		eseguiComando(COMANDO, DIREZIONE);
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	private void eseguiComando(String COMANDO, String NOME_OGGETTO) {
		comando = factory.costruisciComando(COMANDO+" "+NOME_OGGETTO);
		comando.esegui(partita, io);
	}
}
