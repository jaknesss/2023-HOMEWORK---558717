package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static int CFU_INIZIALI = 10;
	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		return borsa.addAttrezzo(attrezzo);
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {
		return borsa.removeAttrezzo(nomeAttrezzo);
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("");
		s.append(getBorsa().toString());
		s.append("\nCFU: " + getCfu());
		return s.toString();
	}
	
}
