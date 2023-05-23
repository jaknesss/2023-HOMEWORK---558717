package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.io.IO;

public class ComandoPosa extends AbstractComando {

	private static final String NOME_COMANDO = "posa";

	public ComandoPosa() {
		super.setNome(NOME_COMANDO);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		if (this.getParam() == null) {
			io.mostraMsg("Cosa vuoi posare?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo daPosare = borsa.getAttrezzo(this.getParam());
		if (daPosare == null) {
			io.mostraMsg("Non hai questo oggetto nella borsa");
			return;
		}
		stanzaCorrente.addAttrezzo(daPosare);
		borsa.removeAttrezzo(daPosare.getNome());
	}
}
