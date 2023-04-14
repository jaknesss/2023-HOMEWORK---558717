package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {

	private String nomeOggettoChiave;
	private String direzioneBloccata;

	public StanzaBloccata(String nome, String nomeOggettoChiave, String direzioneBloccata) {
		super(nome);
		this.nomeOggettoChiave = nomeOggettoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzioneBloccata.equals(direzione) && !hasAttrezzo(nomeOggettoChiave))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione(IO io) {
		if (!this.hasAttrezzo(nomeOggettoChiave))
			io.mostraMessaggio("\nLa stanza a [" + direzioneBloccata + 
							   "] è bloccata hai bisongo di [" + 
							   nomeOggettoChiave + "]");
		return super.getDescrizione(io);
	}

	// per i test
	public String getNomeAttrezzoChiave() {
		return nomeOggettoChiave;
	}
	// per i test
	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}

}
