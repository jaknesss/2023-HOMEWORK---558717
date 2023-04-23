package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il labirinto in cui il gioco si svolge
 * 
 * @author fra
 * @see Stanza
 */
public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	/**
	 * Inizializza il labirinto
	 */
	public Labirinto(IO io) {
		init(io);
	}

	/**
	 * Alloca e collega tutte  le stanza che compongono il labirinto, aggiunge
	 * attrezzi nelle stanze
	 */
	private void init(IO io) {
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo chiave = new Attrezzo("chiave", 3);
		Attrezzo bastone = new Attrezzo("bastone", 5);

		Stanza atrio = new StanzaBloccata("Atrio", "evaihc", "nord");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza aulaN10 = new StanzaBuia("Aula N10", "lanterna");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		atrio.setStanzaAdiacente("nord", biblioteca);
		atrio.setStanzaAdiacente("est", aulaN11);
		atrio.setStanzaAdiacente("sud", aulaN10);
		atrio.setStanzaAdiacente("ovest", laboratorio);
		aulaN11.setStanzaAdiacente("est", laboratorio);
		aulaN11.setStanzaAdiacente("ovest", atrio);
		aulaN10.setStanzaAdiacente("nord", atrio);
		aulaN10.setStanzaAdiacente("est", aulaN11);
		aulaN10.setStanzaAdiacente("ovest", laboratorio);
		laboratorio.setStanzaAdiacente("est", atrio);
		laboratorio.setStanzaAdiacente("ovest", aulaN11);
		biblioteca.setStanzaAdiacente("sud", atrio);

		aulaN10.addAttrezzo(chiave, io);
		atrio.addAttrezzo(bastone, io);
		atrio.addAttrezzo(osso, io);
		atrio.addAttrezzo(lanterna, io);

		this.stanzaIniziale = atrio;
		this.stanzaVincente = biblioteca;
	}

	/**
	 * Restituisce la stanza corrente in cui si trova il giocatore
	 * 
	 * @return stanzaCorrente - stanza corrente del giocatore
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	/**
	 * Restituisce la stanza veincente del labirinto
	 * 
	 * @return stanzaVincente - stanza viencente del labirinto
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
}
