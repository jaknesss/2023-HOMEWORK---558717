package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private final String nomeOggettoChiave;
	
	public StanzaBuia(String nome, String nomeAttrezzoChiave) {
		super(nome);
		this.nomeOggettoChiave = nomeAttrezzoChiave;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeOggettoChiave))
			return super.getDescrizione();
		System.out.println("\nQui c'è buio pesto! "
						 + "Cosa potresti usare?\n");
		return "???????????";
	}
	
	
	//per i test
	public String getNomeAttrezzoChiave() {
		return nomeOggettoChiave;
	}
}
