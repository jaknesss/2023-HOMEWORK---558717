package it.uniroma3.diadia.ambienti;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class Stanza implements Comparable<Stanza>{

	private String nome;
	public static final int MAX_STANZE_ADIACENTI = 4;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;
	
	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	public List<String> getDirezioni(){
		return new LinkedList<String>(stanzeAdiacenti.keySet()); 
	}

	
	public String getDescrizione(IO io) {
		return this.toString();
	}

	public Map<String, Attrezzo> getAttrezzi() {
		return attrezzi;
	}
	
	public List<Attrezzo> getAttrezziAsList() {
		return new LinkedList<Attrezzo>(attrezzi.values());
	}
	
	
	public Map<String, Stanza> getStanzeAdiacenti(){
		return stanzeAdiacenti;
	}
	
	public List<Stanza> getStanzeAdiacentiAsList(){
		return new LinkedList<Stanza>(stanzeAdiacenti.values());
	}
	
	public Stanza getStanzaAdiacente(String direzione) {
		if(stanzeAdiacenti.isEmpty()) return null;
		return stanzeAdiacenti.get(direzione);
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		if(stanzeAdiacenti.size() == MAX_STANZE_ADIACENTI) return;	
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

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public Stanza getStanzaAdiacenteMaxOggetti() {
		List<Stanza> tempList = this.getStanzeAdiacentiAsList();
		Collections.sort(tempList);
		return tempList.get(getStanzeAdiacenti().size()-1);
	}
	
	public Stanza getStanzaAdiacenteMinOggetti() {
		List<Stanza> tempList = this.getStanzeAdiacentiAsList();
		Collections.sort(tempList);
		return tempList.get(0);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Stanza that = (Stanza) obj;
		if(that == null || this.getClass() != that.getClass()) return false;
		return this.getNome().equals(that.getNome()) &&
			   this.getAttrezzi().equals(that.getAttrezzi()) &&
			   this.getDirezioni().equals(that.getDirezioni()) && 
			   this.getAttrezzi().size() == that.getAttrezzi().size();
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + 
			   this.getAttrezzi().hashCode() + 
			   this.getDirezioni().hashCode();	
	}
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\n" + this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : stanzeAdiacenti.keySet())
			risultato.append("[" + direzione + "] ");
		risultato.append("\nNPC: ");
		if(this.getPersonaggio() != null)
			risultato.append(this.getPersonaggio().getNome());
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attr : attrezzi.values())
			risultato.append(attr.toString() + " ");
		return risultato.toString();
	}
	
	@Override
	public int compareTo(Stanza that) {
		return this.getAttrezzi().size() - that.getAttrezzi().size();
	}
}
