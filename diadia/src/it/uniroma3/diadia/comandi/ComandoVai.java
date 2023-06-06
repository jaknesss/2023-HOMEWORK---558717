package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.io.IO;

public class ComandoVai extends AbstractComando{

	private static final String NOME_COMANDO = "vai";

	public ComandoVai() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		String direzione = this.getParam();
		if (direzione == null) {
			io.mostraMsg("Dove vuoi andare? Specifica la direzione");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMsg("Non c'e' una stanza in quella direzione!");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
}
