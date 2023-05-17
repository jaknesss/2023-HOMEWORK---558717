	package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
		return this.pesoCorrente;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		pesoCorrente += attrezzo.getPeso();
		return attrezzi.put(attrezzo.getNome(), attrezzo) == null;
	}

	public boolean isEmpty() {
		return attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) return false;
		return attrezzi.containsKey(nomeAttrezzo);
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(attrezzi.containsKey(nomeAttrezzo))
			pesoCorrente -= attrezzi.get(nomeAttrezzo).getPeso();
		return attrezzi.remove(nomeAttrezzo) != null;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> list = new LinkedList<>(attrezzi.values());
		Collections.sort(list, new ComparatoreAttrezziPerPeso());
		return list;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPer_Nome(){
		return new TreeSet<Attrezzo>(attrezzi.values());
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPer_Peso(){
		 SortedSet<Attrezzo> attrezziPerPeso = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		 attrezziPerPeso.addAll(attrezzi.values());
		 return attrezziPerPeso;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappaPerPeso = new HashMap<>();
		for(Attrezzo attr : attrezzi.values()) {
			Set<Attrezzo> setPerPeso = mappaPerPeso.get(attr.getPeso());
			if(setPerPeso == null)
				setPerPeso = new HashSet<>();
			setPerPeso.add(attr);
			mappaPerPeso.put(attr.getPeso(), setPerPeso);
		}
		return mappaPerPeso;
	}
	
	public String getDescrizione() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("\nContenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			s.append(getContenutoOrdinatoPerPeso().toString());
		} else
			s.append("\nBorsa vuota");
		return s.toString();
	}
	
	
}