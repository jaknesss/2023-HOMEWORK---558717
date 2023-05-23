package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.io.IO;

public class StanzaProtected {

	protected static final int NUMERO_MASSIMO_DIREZIONI = 4;
	protected static final int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	protected Attrezzo[] attrezzi;
	protected int numeroAttrezzi;
	protected Stanza[] stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected String[] direzioniPossibili;

	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioniPossibili = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	public String[] getDirezioni() {
		return this.direzioniPossibili;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (direzione.equals(this.direzioniPossibili[i]))
				return this.stanzeAdiacenti[i];
		return null;
	}

	public void setStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioniPossibili.length; i++)
			if (direzione.equals(this.direzioniPossibili[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioniPossibili[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (nomeAttrezzo.equals(this.attrezzi[i].getNome()))
				return attrezzi[i];
		return null;
	}

	public boolean addAttrezzo(Attrezzo attrezzo, IO io) {
		if (this.numeroAttrezzi >= NUMERO_MASSIMO_ATTREZZI)
			return false;
		this.attrezzi[numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for (int i = 0; i < numeroAttrezzi; i++)
			if (attrezzo.getNome().equals(attrezzi[i].getNome())) {
				attrezzi = this.aggiustaArray(attrezzi, i);
				numeroAttrezzi--;
				return true;
			}
		return false;
	}

	private Attrezzo[] aggiustaArray(Attrezzo[] attrezzi, int indiceRimosso) {
		Attrezzo[] copia = new Attrezzo[attrezzi.length];
		for (int i = 0, j = 0; i < attrezzi.length; i++)
			if (i != indiceRimosso) {
				copia[j] = attrezzi[i];
				j++;
			}
		return copia;
	}

	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\n" + this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioniPossibili)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null)
				risultato.append(attrezzo.toString() + " ");
		return risultato.toString();
	}
}
