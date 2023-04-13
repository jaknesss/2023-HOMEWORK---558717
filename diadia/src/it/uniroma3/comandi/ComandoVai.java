package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	String direzione;

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public void esegui(Partita partita, IO io) {
		if (direzione == null) {
			io.mostraMessaggio("Dove vuoi andare? Specifica la direzione");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Non c'e' una stanza in quella direzione!");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}
