package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 */

public class Partita {

	private Labirinto lab;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;

	/**
	 * Inizializza le istanze di partita
	 */
	public Partita() {
		this.lab = new Labirinto();
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = lab.getStanzaIniziale();
	}

	/**
	 * Restituisce il giocatore
	 * 
	 * @return oggetto di tipo Giocatore del giocatore corrente
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Restituisce la corrente stanza del giocatore
	 * 
	 * @return stanzaCorrente - stanza corrente del giocatore
	 */

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce il labirinto
	 * 
	 * @return oggetto di tipo Labirinto del labirinto corrente
	 */
	public Labirinto getLabirinto() {
		return this.lab;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || isVinta() || giocatoreIsMorto();
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean isVinta() {
		return this.stanzaCorrente == lab.getStanzaVincente();
	}
	
	
	public boolean giocatoreIsMorto() {
		return giocatore.getCfu() == 0;
	}
	

	/**
	 * Imposta la stanza corrente del giocaotore
	 * 
	 * @param stanzaCorrente - stanza corrente del giocatore
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

}
