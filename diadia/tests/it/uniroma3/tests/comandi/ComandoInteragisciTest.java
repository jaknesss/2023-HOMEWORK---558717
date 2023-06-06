package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.diadia.io.IOConsole;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Mago;

class ComandoInteragisciTest {

	private final String COMANDO = "interagisci";
	private Labirinto lab;
	private FabbricaDiComandi factory;
	private Comando comando;
	private IO io;
	private Giocatore giocatore;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoMago;
	private Partita partita;
	private AbstractPersonaggio personaggio;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		lab = Labirinto.newBuilder()
			  .addStanzaIniziale("iniziale");
		io = new IOConsole();
		partita = new Partita(lab, io);
		attrezzo = new Attrezzo("ascia",1);
		attrezzoMago = new Attrezzo("bastone", 10);
		personaggio = new Mago("Merlino", Mago.DESCRIZIONE, attrezzoMago);
		factory = new FabbricaDiComandiRiflessiva();
		giocatore = partita.getGiocatore();
		giocatore.addAttrezzo(attrezzo);
	}
	@Test
	void testNonCiSonoNPCconCuiInteragire() {
		assertNull(partita.getStanzaCorrente().getPersonaggio());
		eseguiComando(COMANDO);
	}
	
	@Test
	void testInteragiscoConMago() {
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		assertEquals(0, partita.getStanzaCorrente().getAttrezzi().size());
		eseguiComando(COMANDO);
		assertEquals(attrezzoMago, partita.getStanzaCorrente().getAttrezzi().get(attrezzoMago.getNome()));
	}
	
	@Test
	void testInteragiscoDueVolteConMago() {
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		assertEquals(0, partita.getStanzaCorrente().getAttrezzi().size());
		eseguiComando(COMANDO);
		assertEquals(1, partita.getStanzaCorrente().getAttrezzi().size());
		assertEquals(attrezzoMago, partita.getStanzaCorrente().getAttrezzi().get(attrezzoMago.getNome()));
		eseguiComando(COMANDO);
		assertNull(personaggio.getAttrezzo());
		assertEquals(1, partita.getStanzaCorrente().getAttrezzi().size());
	}

	private void eseguiComando(String nomeComando) {
		comando = factory.costruisciComando(nomeComando);
		comando.esegui(partita, io);
	}
}
