package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private final String NOME_COMANDO = "posa";
	private String parametro;
	private Attrezzo daPosare;

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
		daPosare = borsa.getAttrezzo(parametro);
		if (daPosare != null) {
			stanzaCorrente.addAttrezzo(daPosare);
			borsa.removeAttrezzo(daPosare.getNome());
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
