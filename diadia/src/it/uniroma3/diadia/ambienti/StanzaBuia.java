package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{
	
	private Attrezzo oggettoChiave;
	
	public StanzaBuia(String nome, Attrezzo oggettoChiave) {
		super(nome);
		this.oggettoChiave = oggettoChiave;
	}
	
	public String getDescrizione(IO io) {
		if(this.hasAttrezzo(oggettoChiave.getNome()))
			return super.toString();
		return "\nQui c'è buio pesto!\nCosa potresti usare?\n\n" 
			 + "???????????";
	}
	
	public String getNomeAttrezzoChiave() {
		return oggettoChiave.getNome();
	}
}
