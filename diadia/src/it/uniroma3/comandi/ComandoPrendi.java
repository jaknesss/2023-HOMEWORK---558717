package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando {

	private final String NOME_COMANDO = "prendi";
	private Attrezzo oggetto;
	private String nomeOggetto;

	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
	}

	@Override
	public void esegui(Partita partita, IO io) {
		if (nomeOggetto == null) {
			io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		oggetto = stanzaCorrente.getAttrezzo(nomeOggetto);
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (stanzaCorrente.hasAttrezzo(oggetto)) {
			Attrezzo daPrendere = stanzaCorrente.getAttrezzo(nomeOggetto);
			if (borsa.addAttrezzo(daPrendere))
				stanzaCorrente.removeAttrezzo(daPrendere);
			else
				io.mostraMessaggio("Borsa troppo pesante!");
		} else
			io.mostraMessaggio("Non c'è questo oggetto nella stanza");
	}
	
	@Override
	public String getNome() {
		return NOME_COMANDO;
	}
	@Override
	public String getParam() {
		return nomeOggetto;
	}
	
}
