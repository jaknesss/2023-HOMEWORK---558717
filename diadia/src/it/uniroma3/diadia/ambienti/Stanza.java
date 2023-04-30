package it.uniroma3.diadia.ambienti;

import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Stanza {

	private String nome;
	private Set<Attrezzo> attrezzi;
	private Set<Stanza> stanzeAdiacenti;
	private Set<String> direzioniPossibili;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashSet<>();
		this.direzioniPossibili = new HashSet<>();
		this.stanzeAdiacenti = new HashSet<>();
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione(IO io) {
		return this.toString();
	}

	public HashSet<Attrezzo> getAttrezzi() {
		return (HashSet<Attrezzo>) this.attrezzi;
	}

	public HashSet<String> getDirezioni() {
		return (HashSet<String>) this.direzioniPossibili;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		for (Stanza stanza : stanzeAdiacenti)
			if(direzioniPossibili.contains(direzione))
				return stanza;
		return this;
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		if (direzioniPossibili.contains(direzione)) {
			stanzeAdiacenti.add(stanza);
			return;
		}
		this.direzioniPossibili.add(direzione);
		this.stanzeAdiacenti.add(stanza);
	}

	public boolean hasAttrezzo(Attrezzo attrezzo) {
		return attrezzi.contains(attrezzo);
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attr : attrezzi)
			if (nomeAttrezzo.equals(attr.getNome()))
				return attr;
		return null;
	}

	public boolean addAttrezzo(Attrezzo attrezzo, IO io) {
		return attrezzi.add(attrezzo);
	}
	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo);
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\n" + this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioniPossibili)
			risultato.append("[" + direzione + "] ");
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi)
			risultato.append(attrezzo.toString() + " ");
		return risultato.toString();
	}

}
