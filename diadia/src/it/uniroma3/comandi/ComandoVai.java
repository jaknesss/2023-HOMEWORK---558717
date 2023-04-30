package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private final String NOME_COMANDO = "vai";
	private String direzione;

	@Override
	public void setParametro(String direzione) {
		this.direzione = direzione;
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

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParam() {
		return direzione;
	}
}
