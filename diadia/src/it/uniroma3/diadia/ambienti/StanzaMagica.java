package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	public static final int SOGLIA_MAGICA_DEFAULT = 3;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
		this.contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.sogliaMagica = soglia;
		this.contatoreAttrezziPosati = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo, IO io) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati >= this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			io.mostraMessaggio("E' successo qualcosa di strano!\n");
		}
		return super.addAttrezzo(attrezzo, io);
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeDaInvertire = new StringBuilder(attrezzo.getNome());
		String nomeInvertito = nomeDaInvertire.reverse().toString();
		return new Attrezzo(nomeInvertito, attrezzo.getPeso() * 2);
	}
}
