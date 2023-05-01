package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Stanza {

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione(IO io) {
		return this.toString();
	}

	public HashMap<String, Attrezzo> getAttrezzi() {
		return (HashMap<String, Attrezzo>) attrezzi;
	}
	
	public HashMap<String, Stanza> getStanzeAdiacenti(){
		return (HashMap<String, Stanza>) stanzeAdiacenti;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		if(stanzeAdiacenti.isEmpty()) return null;
		return stanzeAdiacenti.get(direzione);
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(attrezzi.isEmpty()) return false;
		if(nomeAttrezzo == null) return false;
		return attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(attrezzi.isEmpty()) return null;
		return attrezzi.get(nomeAttrezzo);
	}

	public boolean addAttrezzo(Attrezzo attrezzo, IO io) {
		return attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzi.isEmpty()) return false;
		return attrezzi.remove(attrezzo.getNome()) == null;
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\n" + this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : stanzeAdiacenti.keySet())
			risultato.append("[" + direzione + "] ");
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attr : attrezzi.values())
			risultato.append(attr.toString() + " ");
		return risultato.toString();
	}

}
