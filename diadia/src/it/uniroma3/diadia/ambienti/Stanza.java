package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Stanza {

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private List<String> direzioniPossibili;
	private Map<String, Stanza> stanzeAdiacenti;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.direzioniPossibili = new LinkedList<>();
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

	public LinkedList<String> getDirezioni() {
		return (LinkedList<String>) direzioniPossibili;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		if(stanzeAdiacenti.isEmpty()) return null;
		return stanzeAdiacenti.get(direzione);
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		if (direzioniPossibili.contains(direzione)) {
			stanzeAdiacenti.put(direzione, stanza);
			return;
		}
		this.direzioniPossibili.add(direzione);
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	public boolean hasAttrezzo(Attrezzo attrezzo) {
		return attrezzi.get(attrezzo.getNome()) != null;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
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
		for (String direzione : this.direzioniPossibili)
			risultato.append("[" + direzione + "] ");
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attr : attrezzi.values())
			risultato.append(attr.toString() + " ");
		return risultato.toString();
	}

}
