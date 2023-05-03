package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {

	private String nomeOggettoChiave;
	private String direzioneBloccata;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeOggettoChiave) {
		super(nome);
		this.nomeOggettoChiave = nomeOggettoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getDescrizione(IO io) {
		if (!hasAttrezzo(getNomeAttrezzoChiave()))
			io.mostraMessaggio("\nLa stanza a [" + direzioneBloccata + 
			"] è bloccata, hai bisongo di [" + this.getNomeAttrezzoChiave() + "] !");
		return super.getDescrizione(io);
	}

	public String getNomeAttrezzoChiave() {
		return this.nomeOggettoChiave;
	}
	
	public String getDirezioneBloccata() {
		return direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzioneBloccata.equals(direzione) && !hasAttrezzo(getNomeAttrezzoChiave()))
			return this;
		return super.getStanzaAdiacente(direzione);
	}
}
