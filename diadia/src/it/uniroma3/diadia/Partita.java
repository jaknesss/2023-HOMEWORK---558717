package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.io.IO;

public class Partita {

	private Labirinto lab;
	private Giocatore giocatore;
	private Stanza corrente;
	private boolean finita;

	public Partita(Labirinto lab, IO io) {
		this.lab = lab;
		this.giocatore = new Giocatore();
		this.corrente = lab.getStanzaIniziale();
		this.finita = false;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return this.corrente;
	}

	public Labirinto getLabirinto() {
		return this.lab;
	}

	public boolean isFinita() {
		return finita || isVinta() || giocatoreIsMorto();
	}

	public boolean isVinta() {
		return this.getStanzaCorrente().getNome().equals(lab.getStanzaVincente().getNome());
	}
	
	
	public boolean giocatoreIsMorto() {
		return giocatore.getCfu() == 0;
	}
	
	public void setLabirinto(Labirinto lab) {
		this.lab = lab;
	}
	
	public void setFinita() {
		this.finita = true;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		if(lab.hasStanza(stanzaCorrente.getNome())) 
			corrente = lab.getStanza(stanzaCorrente.getNome());
	}
}
