package it.uniroma3.diadia.ambienti;

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
	public String getDescrizione() {
		if (!this.hasAttrezzo(nomeOggettoChiave))
			System.out.println(
					"\nLa stanza a [" + direzioneBloccata + "] è bloccata hai bisongo di [" + nomeOggettoChiave + "]");
		return super.getDescrizione();
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
