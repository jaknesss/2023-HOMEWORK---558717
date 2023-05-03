package it.uniroma3.diadia.ambienti;

public interface Labirinto {
	
	public Stanza getStanzaIniziale();
	public Stanza getStanzaVincente();
	public Stanza getUltimaStanza();
	public void setStanzaCorrente(Stanza stanza);

}	