package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.io.IO;

public class ComandoPrendi extends AbstractComando {

	private static final String NOME_COMANDO = "prendi";

	public ComandoPrendi() {
		super.setNome(NOME_COMANDO);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		if (this.getParam() == null) {
			io.mostraMsg("Cosa vuoi prendere?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (!stanzaCorrente.hasAttrezzo(this.getParam())) {
			io.mostraMsg("Non c'è questo oggetto nella stanza");
			return;
		}
		Attrezzo daPrendere = stanzaCorrente.getAttrezzo(this.getParam());
		if (borsa.addAttrezzo(daPrendere))
			stanzaCorrente.removeAttrezzo(daPrendere);
		else io.mostraMsg("Borsa troppo pesante!");
	}

}
