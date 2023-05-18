package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			io.mostraMsg(this.messaggio);
		} else io.mostraMsg(MESSAGGIO_CON_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}

}
