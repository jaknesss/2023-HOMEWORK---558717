package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg(partita.getStanzaCorrente().getPersonaggio().saluta());
	}
	
}
