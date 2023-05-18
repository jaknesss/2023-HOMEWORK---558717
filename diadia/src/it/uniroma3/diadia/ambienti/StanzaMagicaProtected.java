package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private static final int SOGLIA_MAGICA_DEFAULT = 3;
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
		contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.sogliaMagica = soglia;
		this.contatoreAttrezziPosati = 0;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo, IO io) {	
		this.contatoreAttrezziPosati++;
		if(contatoreAttrezziPosati >= sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			io.mostraMsg("E' successo qulacosa di strano!\n");
		}
		if (this.numeroAttrezzi >= attrezzi.length)
			return false;
		this.attrezzi[numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
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
