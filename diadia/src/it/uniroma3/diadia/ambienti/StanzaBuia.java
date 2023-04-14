package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private final String nomeAttrezzoChiave;
	
	public StanzaBuia(String nome, String nomeAttrezzoChiave) {
		super(nome);
		this.nomeAttrezzoChiave = nomeAttrezzoChiave;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeAttrezzoChiave))
			return super.getDescrizione();
		System.out.println("\nQui c'è buio pesto! "
						 + "Cosa potresti usare?\n");
		return "???????????";
	}
}
