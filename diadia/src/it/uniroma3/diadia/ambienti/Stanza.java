package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Stanza {

	private String nome;
	private final int MAX_NUMERO_STANZE_ADIACENTI = 4;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private List<String> direzioni;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
		this.direzioni = new LinkedList<>();
	}

	public String getNome() {
		return nome;
	}

	public LinkedList<String> getDirezioni(){
		direzioni.addAll(stanzeAdiacenti.keySet());
		return (LinkedList<String>) direzioni;
	}

	
	public String getDescrizione(IO io) {
		return this.toString();
	}

	public HashMap<String, Attrezzo> getAttrezzi() {
		return (HashMap<String, Attrezzo>) attrezzi;
	}
	
	public LinkedList<Attrezzo> getAttrezziAsList() {
		LinkedList<Attrezzo> attrezziList = new LinkedList<>(attrezzi.values());
		return attrezziList;
	}
	
	
	public HashMap<String, Stanza> getStanzeAdiacenti(){
		return (HashMap<String, Stanza>) stanzeAdiacenti;
	}
	
	public LinkedList<Stanza> getStanzeAdiacentiAsList(){
		LinkedList<Stanza> stanzeAdiacentiList = new LinkedList<>(stanzeAdiacenti.values());
		return stanzeAdiacentiList;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		if(stanzeAdiacenti.isEmpty()) return null;
		return stanzeAdiacenti.get(direzione);
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		if(stanzeAdiacenti.size() == MAX_NUMERO_STANZE_ADIACENTI) return;	
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

	public boolean addAttrezzo(Attrezzo attrezzo) {
		return attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzi.isEmpty()) return false;
		return attrezzi.remove(attrezzo.getNome()) == null;
	}

	@Override
	public boolean equals(Object obj) {
		Stanza that = (Stanza) obj;
		if(that == null) return false;
		return this.getNome().equals(that.getNome()) &&
				this.getAttrezzi().equals(that.getAttrezzi()) &&
				this.getStanzeAdiacenti().equals(that.getStanzeAdiacenti());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + MAX_NUMERO_STANZE_ADIACENTI;
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
