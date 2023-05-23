package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.io.IO;

public class StanzaBuia extends Stanza{
	
	private String nomeOggettoChiave;
	
	public StanzaBuia(String nome, String nomeOggettoChiave) {
		super(nome);
		this.nomeOggettoChiave = nomeOggettoChiave;
	}
	
	public String getDescrizione(IO io) {
		if(this.hasAttrezzo(getNomeAttrezzoChiave()))
			return super.toString();
		return "\nQui c'è buio pesto!\nCosa potresti usare?\n\n" 
			 + "???????????";
	}
	
	public String getNomeAttrezzoChiave() {
		return this.nomeOggettoChiave;
	}
}
