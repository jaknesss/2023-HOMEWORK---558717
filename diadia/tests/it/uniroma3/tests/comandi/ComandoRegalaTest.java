package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Strega;

class ComandoRegalaTest {
	
	private final String COMANDO = "regala";
	private Labirinto lab;
	private FabbricaDiComandi factory;
	private Comando comando;
	private IO io;
	private Giocatore giocatore;
	private Attrezzo attrezzo;
	private Partita partita;
	private AbstractPersonaggio personaggio;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		lab = new LabirintoBuilder().addStanzaIniziale("iniziale");
		io = new IOConsole();
		partita = new Partita(lab, io);
		personaggio = new Strega("Morgana", Strega.DESCRIZIONE);
		factory = new FabbricaDiComandiRiflessiva();
		attrezzo = new Attrezzo("ascia",1);
		giocatore = partita.getGiocatore();
		giocatore.addAttrezzo(attrezzo);
	}

	@Test
	void testNonHoOggettiNonRegaloNulla() {
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
		eseguiComando(COMANDO, "nonEsiste");
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
	}
	
	@Test
	void testRegaloUnOggettoMaNonCiSonoNPC() {
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
		eseguiComando(COMANDO, attrezzo.getNome());
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
	}
	
	@Test
	void testRegaloUnOggetto() {
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
		eseguiComando(COMANDO, attrezzo.getNome());
		assertEquals(0, giocatore.getBorsa().getAttrezzi().size());
		assertEquals(attrezzo, personaggio.getAttrezzo());
	}
	
	private void eseguiComando(String nomeComando, String nomeOggetto) {
		comando = factory.costruisciComando(nomeComando + " " + nomeOggetto);
		comando.esegui(partita, io);
	}

}
