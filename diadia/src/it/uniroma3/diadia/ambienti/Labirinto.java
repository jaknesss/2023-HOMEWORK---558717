package it.uniroma3.diadia.ambienti;

public interface Labirinto {
	
	public Stanza getStanzaIniziale();
	public Stanza getStanzaVincente();
	public Stanza getUltimaStanza();
	public Stanza getStanza(String nomeStanza);
	public boolean hasStanza(String nomeStanza);
}	