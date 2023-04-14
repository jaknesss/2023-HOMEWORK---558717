package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;

public class StanzaBuia extends Stanza{
	
	private final String nomeOggettoChiave;
	
	public StanzaBuia(String nome, String nomeAttrezzoChiave) {
		super(nome);
		this.nomeOggettoChiave = nomeAttrezzoChiave;
	}
	
	@Override
	public String getDescrizione(IO io) {
		if(this.hasAttrezzo(nomeOggettoChiave))
			return super.getDescrizione(io);
		return "\nQui c'è buio pesto!\nCosa potresti usare?\n\n" 
			 + "???????????";
	}
	
	
	//per i test
	public String getNomeAttrezzoChiave() {
		return nomeOggettoChiave;
	}
}
