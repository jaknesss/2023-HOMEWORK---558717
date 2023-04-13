package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	String nomeOggetto;

	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
	}

	@Override
	public void esegui(Partita partita) {
		if (nomeOggetto == null) {
			System.out.println("Cosa vuoi posare?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (partita.getGiocatore().getBorsa().hasAttrezzo(nomeOggetto)) {
			Attrezzo daPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeOggetto);
			if (stanzaCorrente.addAttrezzo(daPosare))
				partita.getGiocatore().removeAttrezzo(daPosare);
		} else
			System.out.println("Non hai questo oggetto nella borsa");
	}
}
