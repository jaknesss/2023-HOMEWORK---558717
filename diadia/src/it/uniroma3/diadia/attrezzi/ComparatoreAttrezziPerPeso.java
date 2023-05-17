package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo>{
	
	public int compare(Attrezzo attr1, Attrezzo attr2) {
		if(attr1.getPeso() == attr2.getPeso()) 
			return attr1.getNome().compareTo(attr2.getNome());
		return attr1.getPeso() - attr2.getPeso();
	}
}
