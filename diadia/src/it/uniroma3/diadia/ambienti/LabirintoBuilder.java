package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Cane;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

public class LabirintoBuilder implements Labirinto {

	private Map<String, Stanza> stanze;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Stanza stanzaACuiAggiungereAdiacenza;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		stanze = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		stanzaIniziale = new Stanza(nomeStanza);
		stanze.put(nomeStanza, stanzaIniziale);
		ultimaStanzaAggiunta = stanzaIniziale;
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		stanzaVincente = new Stanza(nomeStanza);
		stanze.put(nomeStanza, stanzaVincente);
		ultimaStanzaAggiunta = stanzaVincente;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		return this;
	}

	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza nuova = new Stanza(nomeStanza);
		stanze.put(nomeStanza, nuova);
		ultimaStanzaAggiunta = nuova;
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorr, String stanzaAdiacente, String dir) {
		stanzaACuiAggiungereAdiacenza = stanze.get(stanzaCorr);
		if (stanzaACuiAggiungereAdiacenza.getStanzeAdiacenti().size() == Stanza.MAX_STANZE_ADIACENTI)
			return this;
		stanzaACuiAggiungereAdiacenza.setStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()), stanze.get(stanzaAdiacente));
		return this;
	}

	public LabirintoBuilder getLabirinto() {
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		Stanza magica = new StanzaMagica(nomeStanza, sogliaMagica);
		stanze.put(nomeStanza, magica);
		ultimaStanzaAggiunta = magica;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagicaNoSoglia(String nomeStanza) {
		Stanza magica = new StanzaMagica(nomeStanza);
		stanze.put(nomeStanza, magica);
		ultimaStanzaAggiunta = magica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String dir, String nomeOggetto) {
		Stanza bloccata = new StanzaBloccata(nomeStanza, Direzione.valueOf(dir.toUpperCase()), nomeOggetto);
		stanze.put(nomeStanza, bloccata);
		ultimaStanzaAggiunta = bloccata;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeOggetto) {
		Stanza buia = new StanzaBuia(nomeStanza, nomeOggetto);
		stanze.put(nomeStanza, buia);
		ultimaStanzaAggiunta = buia;
		return this;
	}
	
	public LabirintoBuilder addStrega(String nomePers, String desc) {
		AbstractPersonaggio strega = new Strega(nomePers, desc);
		ultimaStanzaAggiunta.setPersonaggio(strega);;
		return this;
	}
	public LabirintoBuilder addMago(String nomePers, String desc, Attrezzo attr) {
		AbstractPersonaggio strega = new Mago(nomePers, desc, attr);
		ultimaStanzaAggiunta.setPersonaggio(strega);;
		return this;
	}
	public LabirintoBuilder addCane(String nomePers, String desc) {
		AbstractPersonaggio strega = new Cane(nomePers, desc);
		ultimaStanzaAggiunta.setPersonaggio(strega);;
		return this;
	}
	public Stanza getUltimaStanza() {
		return this.ultimaStanzaAggiunta;
	}
	
	public HashMap<String, Stanza> getStanze(){
		return (HashMap<String, Stanza>) stanze;
	}
	
	public Stanza getStanza(String nomeStanza){
		return this.getStanze().get(nomeStanza);
	}
	
	public boolean hasStanza(String nomeStanza) {
		return this.getStanze().containsKey(nomeStanza);
	}
	
	@Override
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	@Override
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
