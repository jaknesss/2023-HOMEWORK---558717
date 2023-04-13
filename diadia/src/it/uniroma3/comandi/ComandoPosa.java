package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

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
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeOggetto)) {
			Attrezzo daPosare = borsa.getAttrezzo(nomeOggetto);
			if (stanzaCorrente.addAttrezzo(daPosare))
				borsa.removeAttrezzo(daPosare);
			else 
				System.out.println("Stanza troppo piena! Oggetto non aggiunto");
		} else
			System.out.println("Non hai questo oggetto nella borsa");
	}
}
