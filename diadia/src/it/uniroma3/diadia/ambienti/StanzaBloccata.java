package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.io.IO;

public class StanzaBloccata extends Stanza {

	private String nomeOggettoChiave;
	private Direzione direzioneBloccata;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String nomeOggettoChiave) {
		super(nome);
		this.nomeOggettoChiave = nomeOggettoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getDescrizione(IO io) {
		if (!hasAttrezzo(getNomeAttrezzoChiave()))
			io.mostraMsg("\nLa stanza a [" + direzioneBloccata + 
			"] è bloccata, hai bisongo di [" + this.getNomeAttrezzoChiave() + "] !");
		return super.getDescrizione(io);
	}

	public String getNomeAttrezzoChiave() {
		return this.nomeOggettoChiave;
	}
	
	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if (direzioneBloccata.equals(Direzione.valueOf(dir.toUpperCase())) 
			&& !hasAttrezzo(getNomeAttrezzoChiave()))
			return this;
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean check = super.equals(obj);
		StanzaBloccata that = (StanzaBloccata) obj;
		return check && 
				this.getDirezioneBloccata().equals(that.getDirezioneBloccata()) && 
				this.getNomeAttrezzoChiave().equals(that.getNomeAttrezzoChiave());
	}
}
