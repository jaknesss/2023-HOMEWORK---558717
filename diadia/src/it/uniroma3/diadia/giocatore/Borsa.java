package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	private int pesoCorrente;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
		this.pesoCorrente = 0;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public int getPeso() {
		pesoCorrente = 0;
		for (Attrezzo attr : attrezzi.values())
			pesoCorrente += attr.getPeso();
		return pesoCorrente;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	public boolean isEmpty() {
		return attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(Attrezzo attrezzo) {
		if(this.isEmpty()) return false;
		if(attrezzo == null) return false;
		return attrezzi.containsValue(attrezzo);
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(this.isEmpty()) return false;
		if(attrezzo == null) return false;
		return attrezzi.remove(attrezzo.getNome()) != null;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> list = new LinkedList<>(attrezzi.values());
		Collections.sort(list, new ComparatoreAttrezziPerPeso());
		return list;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(attrezzi.values());
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("\nContenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (Attrezzo attr : attrezzi.values())
				s.append(attr.toString() + " ");
		} else
			s.append("\nBorsa vuota");
		return s.toString();
	}
}