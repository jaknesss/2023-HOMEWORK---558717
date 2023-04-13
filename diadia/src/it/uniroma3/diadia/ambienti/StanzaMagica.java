package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private static final int SOGLIA_MAGICA_DEFAULT = 3;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
		contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.sogliaMagica = soglia;
		this.contatoreAttrezziPosati = 0;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(contatoreAttrezziPosati >= sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			System.out.println("E' successo qualcosa di strano all'oggetto!");
		}	
		return super.addAttrezzo(attrezzo);
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeDaInvertire = new StringBuilder(attrezzo.getNome());
		int pesoDoppio = attrezzo.getPeso() * 2;
		nomeDaInvertire = nomeDaInvertire.reverse();
		Attrezzo attrModificato = new Attrezzo(nomeDaInvertire.toString(), pesoDoppio);
		return attrModificato;
	}
	
	public int getSogliaMagica(){
		return this.sogliaMagica;
	}
	
}
