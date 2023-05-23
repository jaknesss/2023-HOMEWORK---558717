package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.diadia.io.IOConsole;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Mago;
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
	private AbstractPersonaggio persStrega;
	private AbstractPersonaggio persMago;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		lab = new LabirintoBuilder().addStanzaIniziale("iniziale");
		io = new IOConsole();
		partita = new Partita(lab, io);
		factory = new FabbricaDiComandiRiflessiva();
		attrezzo = new Attrezzo("ascia",1);
		persStrega = new Strega("Morgana", Strega.DESCRIZIONE);
		persMago = new Mago("Merlino", Mago.DESCRIZIONE, new Attrezzo("osso", 1));
		giocatore = partita.getGiocatore();
		giocatore.addAttrezzo(attrezzo);
	}

	@Test
	void testNonHoOggettiNonRegaloNulla() {
		partita.getStanzaCorrente().setPersonaggio(persStrega);
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
	void testRegaloUnOggetto_Strega() {
		partita.getStanzaCorrente().setPersonaggio(persStrega);
		assertEquals(1, giocatore.getBorsa().getAttrezzi().size());
		eseguiComando(COMANDO, attrezzo.getNome());
		assertEquals(0, giocatore.getBorsa().getAttrezzi().size());
		assertEquals(attrezzo, persStrega.getAttrezzo());
	}
	
	@Test
	void testRegaloUnOggetto_Mago() {
		partita.getStanzaCorrente().setPersonaggio(persMago);
		assertTrue(giocatore.getBorsa().hasAttrezzo(attrezzo.getNome()));
		eseguiComando(COMANDO, attrezzo.getNome());
		assertFalse(giocatore.getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertEquals(attrezzo.getPeso()/2, partita.getStanzaCorrente().getAttrezzo(attrezzo.getNome()).getPeso());
	}
	
	private void eseguiComando(String nomeComando, String nomeOggetto) {
		comando = factory.costruisciComando(nomeComando + " " + nomeOggetto);
		comando.esegui(partita, io);
	}

}
