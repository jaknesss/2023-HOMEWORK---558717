package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	
	public static final String NOME_COMANDO = "interagisci";
	public static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	
	
	
	public ComandoInteragisci() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			io.mostraMsg(MESSAGGIO_CON_CHI);
			return;
		}
		io.mostraMsg(personaggio.agisci(partita));
	}
}
