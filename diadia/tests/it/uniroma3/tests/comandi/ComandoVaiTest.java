package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
	private Partita partita;
	private FabbricaDiComandi factory;
	private Comando comando;
	private Stanza stanzaCorrente;
	private Stanza stanzaIniziale;
	private final String COMANDO = "vai "; 
	private final String COMANDO_SCONOSCIUTO = "test "; 
	private final String DIREZIONE = "sud"; 
	private final String DIREZIONE_SCONOSCIUTA = "sud-est"; 
	
	@BeforeEach
	void setUp() {
		partita = new Partita();
		stanzaCorrente = partita.getStanzaCorrente();
		stanzaIniziale = partita.getLabirinto().getStanzaIniziale();
		factory = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testComandoNullo() {
		comando = factory.costruisciComando(null + DIREZIONE);
		comando.esegui(partita);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testComandoNonValido() {
		comando = factory.costruisciComando(COMANDO_SCONOSCIUTO + DIREZIONE);
		comando.esegui(partita);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testDirezioneNulla() {
		comando = factory.costruisciComando(COMANDO + null);
		comando.esegui(partita);
		assertEquals(stanzaIniziale.getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
	
	
	@Test
	void testDirezioneSconosciuta() {
		comando = factory.costruisciComando(COMANDO + DIREZIONE_SCONOSCIUTA);
		comando.esegui(partita);
		assertEquals(stanzaIniziale.getNome(), 
				 partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testComandoDirezioneValidi() {
		comando = factory.costruisciComando(COMANDO + DIREZIONE);
		comando.esegui(partita);
		assertEquals(stanzaIniziale.getStanzaAdiacente(DIREZIONE).getNome(), 
					 partita.getStanzaCorrente().getNome());
	}
}
