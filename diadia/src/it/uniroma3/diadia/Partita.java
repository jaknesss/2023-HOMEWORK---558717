package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 */

public class Partita {

	private Labirinto lab;
	private Giocatore giocatore;
	private boolean finita;

	/**
	 * Inizializza le istanze di partita
	 */
	public Partita(){
		this.lab = new Labirinto();
		this.giocatore = new Giocatore();
		this.finita = false;
	}


	/**
	 * Restituisce il giocatore
	 * @return oggetto di tipo Giocatore del giocatore corrente
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}


	/**
	 * Restituisce il labirinto 
	 * @return oggetto di tipo Labirinto del labirinto corrente
	 */
	public Labirinto getLabirinto() {
		return this.lab;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || isVinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean isVinta() {
		return lab.getStanzaCorrente() == lab.getStanzaVincente();
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}


}






