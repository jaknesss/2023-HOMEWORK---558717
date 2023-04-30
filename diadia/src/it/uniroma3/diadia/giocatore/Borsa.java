package it.uniroma3.diadia.giocatore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo attr : attrezzi.values())
			peso += attr.getPeso();
		return peso;
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
		return attrezzi.get(attrezzo.getNome()) != null;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo.getNome()) != null;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
	}
	
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