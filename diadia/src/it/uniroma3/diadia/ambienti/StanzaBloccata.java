package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private Attrezzo oggettoChiave;
	private String direzioneBloccata;

	public StanzaBloccata(String nome, Attrezzo oggettoChiave, String direzioneBloccata) {
		super(nome);
		this.oggettoChiave = oggettoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getDescrizione(IO io) {
		if (!hasAttrezzo(getNomeAttrezzoChiave()))
			io.mostraMessaggio("\nLa stanza a [" + direzioneBloccata + 
			"] è bloccata, hai bisongo di [" + oggettoChiave.getNome() + "] !");
		return super.getDescrizione(io);
	}

	public String getNomeAttrezzoChiave() {
		return oggettoChiave.getNome();
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
