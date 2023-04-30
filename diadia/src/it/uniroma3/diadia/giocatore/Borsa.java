package it.uniroma3.diadia.giocatore;

import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Set<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<>();
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo attr : attrezzi)
			peso += attr.getPeso();
		return peso;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attr : attrezzi)
			if (nomeAttrezzo.equals(attr.getNome()))
				return attr;
		return null;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return attrezzi.add(attrezzo);
	}

	public boolean isEmpty() {
		return attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(Attrezzo attrezzo) {
		return attrezzi.contains(attrezzo);
	}
	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return attrezzi.remove(attrezzo);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("\nContenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (Attrezzo attr : attrezzi)
				s.append(attr.toString() + " ");
		}else s.append("\nBorsa vuota");
		return s.toString();
	}
}