package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String nomeOggettoChiave;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome, String nomeOggettoChiave, String direzioneBloccata) {
		super(nome);
		this.nomeOggettoChiave = nomeOggettoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.hasAttrezzo(nomeOggettoChiave))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeOggettoChiave))
			return super.getDescrizione();
		else
			System.out.println("\nNon puoi andare verso [" + direzioneBloccata
				         	+ "] hai bisongo di [" + nomeOggettoChiave + "]");
		return super.getDescrizione();
	}
	
}
