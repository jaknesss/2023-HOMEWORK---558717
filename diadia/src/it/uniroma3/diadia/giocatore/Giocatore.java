package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il giocatore che interagisce con la borsa
 * 
 * @author fra
 * @see Borsa
 */

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;

	/**
	 * Inizializza il numero di CFU del giocatore e crea l'oggeto borsa
	 * 
	 * @see Borsa
	 */
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	/**
	 * Restituisce i cfu rimanenti del giocatore
	 * 
	 * @return cfu - cfu del giocatore
	 */
	public int getCfu() {
		return cfu;
	}

	/**
	 * Imposta un nuovo valore a cfu
	 *
	 * @param cfu - cfu da impostare come correnti
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * Aggiunge un attrezzo dentro la borsa del giocatore
	 * 
	 * @param attrezzo - attrezzo da voler aggiungere alla borsa
	 * @return vero - l'agginuta è andata a buon fine
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return borsa.addAttrezzo(attrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla borsa del giocatore
	 * 
	 * @param attrezzo - attrezzo da voler rimuovere dalla borsa
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return borsa.removeAttrezzo(attrezzo);
	}

	/**
	 * Restituisce la borsa del giocatore
	 * 
	 * @return borsa - borsa del giocatore
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}

}
