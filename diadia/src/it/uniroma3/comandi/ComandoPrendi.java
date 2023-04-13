package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	String nomeOggetto;

	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
	}

	@Override
	public void esegui(Partita partita) {
		if (nomeOggetto == null) {
			System.out.println("Cosa vuoi prendere?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeOggetto)) {
			Attrezzo daPrendere = stanzaCorrente.getAttrezzo(nomeOggetto);
			if (partita.getGiocatore().addAttrezzo(daPrendere))
				stanzaCorrente.removeAttrezzo(daPrendere);
			else
				System.out.println("Borsa troppo pesante!");
		} else
			System.out.println("Non c'è questo oggetto nella stanza");
	}
}
