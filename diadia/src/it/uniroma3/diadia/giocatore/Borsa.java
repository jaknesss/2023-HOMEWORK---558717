package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce la borsa del giocatore 
 * 
 * @author fra
 *
 */

public class Borsa {
	private final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	/**
	 * Costruttore secondario della classe Borsa
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Costruttore primario della classe Borsa
	 * 
	 * @param pesoMax - peso massimo che la borsa può contenere
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Restituisce il peso massimo trasportabile della borsa
	 * 
	 * @return pesoMax - il peso massimo della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce il peso complessivo della borsa
	 * 
	 * @return peso - peso complessivo della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/**
	 * Cerca l'attrezzo per nome nella lista degli attrezzi della borsa
	 *
	 * @param nomeAttrezzo - da cercare all'interno della borsa
	 * @return attrezzo - l'oggetto dell'attrezzo cercato
	 */

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return attrezzi[i];
		return null;
	}

	/**
	 * Agginunge un attrezzo nella borsa
	 * 
	 * @param attrezzo - attrezzo da aggiungere
	 * @return vero - l'aggiunta è andata buon fine
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce Controlla che la borsa sia vuota
	 * 
	 * @return vero - se non ci sono attrezzi nella borsa
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Restituisce l'esistenza dell'attrezzo nella borsa
	 * 
	 * @param nomeAttrezzo - da cercare
	 * @return vero - l'attrezzo esiste nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Copia l'array del parametro in una copia escludendo l'attrezzo da rimuovere
	 * 
	 * @param attrezzi      - array di attrezzi
	 * @param indiceRimosso - indice dell'attrezzo da rimuovere
	 * @return riferimento all'array modificato
	 */
	private Attrezzo[] aggiustaArray(Attrezzo[] attrezzi, int indiceRimosso) {
		Attrezzo[] copia = new Attrezzo[attrezzi.length];
		System.arraycopy(attrezzi, 0, copia, 0, indiceRimosso);
		System.arraycopy(attrezzi, indiceRimosso+1, copia, indiceRimosso, copia.length-1-indiceRimosso);
		return copia;
	}

	/**
	 * Rimuove un attrezzo dalla borsa e lo restituisce
	 * 
	 * @param daPosare - attrezzo da rimuovere
	 */
	public void removeAttrezzo(Attrezzo daPosare) {
		for (int i = 0; i < numeroAttrezzi; i++)
			if (attrezzi[i] != null && daPosare.getNome().equals(attrezzi[i].getNome())) {
				attrezzi = this.aggiustaArray(attrezzi, i);
				numeroAttrezzi--;
				return;
			}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}