package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Cane;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> stanze;

	public Labirinto() {
		this.stanze = new HashMap<>();
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}

	public void setUltimaStanza(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
	}

	public boolean hasStanza(String nomeStanza) {
		return stanze.containsKey(nomeStanza);
	}

	public Stanza getStanza(String nomeStanza) {
		return stanze.get(nomeStanza);
	}

	public Stanza getUltimaStanza() {
		return this.ultimaStanzaAggiunta;
	}

	public static class LabirintoBuilder extends Labirinto {

		private Stanza stanzaIniziale;
		private Stanza stanzaVincente;
		private Stanza stanzaACuiAggiungereAd;
		private Stanza ultimaStanzaAggiunta;
		private Labirinto lab;

		public LabirintoBuilder() {
			lab = new Labirinto();
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			stanzaIniziale = new Stanza(nomeStanza);
			lab.stanze.put(nomeStanza, stanzaIniziale);
			this.lab.setStanzaIniziale(stanzaIniziale);
			ultimaStanzaAggiunta = stanzaIniziale;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			stanzaVincente = new Stanza(nomeStanza);
			lab.stanze.put(nomeStanza, stanzaVincente);
			this.lab.setStanzaVincente(stanzaVincente);
			ultimaStanzaAggiunta = stanzaVincente;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza nuova = new Stanza(nomeStanza);
			lab.stanze.put(nomeStanza, nuova);
			ultimaStanzaAggiunta = nuova;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanzaCorr, String stanzaAdiacente, String dir) {
			stanzaACuiAggiungereAd = lab.stanze.get(stanzaCorr);
			if (stanzaACuiAggiungereAd.getStanzeAdiacenti().size() == Stanza.MAX_STANZE_ADIACENTI) return this;
			Stanza adiacente = lab.stanze.get(stanzaAdiacente);
			stanzaACuiAggiungereAd.setStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()), adiacente);
			adiacente.setStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()).opposta(), stanzaACuiAggiungereAd);
			return this;
		}

		public Labirinto getLabirinto() {
			return this.lab;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
			Stanza magica = new StanzaMagica(nomeStanza, sogliaMagica);
			lab.stanze.put(nomeStanza, magica);
			ultimaStanzaAggiunta = magica;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaMagicaNoSoglia(String nomeStanza) {
			Stanza magica = new StanzaMagica(nomeStanza);
			lab.stanze.put(nomeStanza, magica);
			ultimaStanzaAggiunta = magica;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanza, String dir, String nomeOggetto) {
			Stanza bloccata = new StanzaBloccata(nomeStanza, Direzione.valueOf(dir.toUpperCase()), nomeOggetto);
			lab.stanze.put(nomeStanza, bloccata);
			ultimaStanzaAggiunta = bloccata;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeOggetto) {
			Stanza buia = new StanzaBuia(nomeStanza, nomeOggetto);
			lab.stanze.put(nomeStanza, buia);
			ultimaStanzaAggiunta = buia;
			setUltimaStanza(ultimaStanzaAggiunta);
			return this;
		}

		public LabirintoBuilder addStrega(String nomePers, String desc) {
			AbstractPersonaggio strega = new Strega(nomePers, desc);
			ultimaStanzaAggiunta.setPersonaggio(strega);
			;
			return this;
		}

		public LabirintoBuilder addMago(String nomePers, String desc, Attrezzo attr) {
			AbstractPersonaggio strega = new Mago(nomePers, desc, attr);
			ultimaStanzaAggiunta.setPersonaggio(strega);
			;
			return this;
		}

		public LabirintoBuilder addCane(String nomePers, String desc) {
			AbstractPersonaggio strega = new Cane(nomePers, desc);
			ultimaStanzaAggiunta.setPersonaggio(strega);
			;
			return this;
		}

		public Stanza getUltimaStanza() {
			return this.ultimaStanzaAggiunta;
		}

		public Map<String, Stanza> getStanze() {
			return lab.stanze;
		}

		public Stanza getStanza(String nomeStanza) {
			return this.getStanze().get(nomeStanza);
		}

		public boolean hasStanza(String nomeStanza) {
			return this.getStanze().containsKey(nomeStanza);
		}

		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}

		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}
	}
}