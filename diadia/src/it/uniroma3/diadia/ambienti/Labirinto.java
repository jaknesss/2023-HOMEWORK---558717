package it.uniroma3.diadia.ambienti;


public interface Labirinto {
	
	
//	public Labirinto(String nomeFile) throws FileNotFoundException {
//		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
//		c.carica();
//		
//	}
//	
	public Stanza getStanzaIniziale();
	public Stanza getStanzaVincente();
	public Stanza getUltimaStanza();
	public Stanza getStanza(String nomeStanza);
	public boolean hasStanza(String nomeStanza);
	
	
	
	
}	