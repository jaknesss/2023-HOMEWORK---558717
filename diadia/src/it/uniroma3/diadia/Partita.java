package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto lab;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;

	public Partita(Labirinto lab, IO io) {
		this.lab = lab;
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = lab.getStanzaIniziale();
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Labirinto getLabirinto() {
		return this.lab;
	}

	public boolean isFinita() {
		return finita || isVinta() || giocatoreIsMorto();
	}

	public boolean isVinta() {
		return this.stanzaCorrente == lab.getStanzaVincente();
	}
	
	
	public boolean giocatoreIsMorto() {
		return giocatore.getCfu() == 0;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public void setLabirinto(Labirinto lab) {
		this.lab = lab;
	}
	
	public void setFinita() {
		this.finita = true;
	}

}
