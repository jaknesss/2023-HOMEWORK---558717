package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private final String NOME_COMANDO = "posa";
	private String parametro;

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void esegui(Partita partita, IO io) {
		if (parametro == null) {
			io.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(parametro)) {
			Attrezzo daPosare = borsa.getAttrezzo(parametro);
			if (stanzaCorrente.addAttrezzo(daPosare, io))
				borsa.removeAttrezzo(daPosare);
			else
				io.mostraMessaggio("Stanza troppo piena! Oggetto non aggiunto");
		} else
			io.mostraMessaggio("Non hai questo oggetto nella borsa");
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParam() {
		return parametro;
	}

}
