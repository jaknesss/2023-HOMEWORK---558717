package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public class ComandoSaluta extends AbstractComando {

	private static final String NOME_COMANDO = "saluta";

	public ComandoSaluta() {
		super.setNome(NOME_COMANDO);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg(partita.getStanzaCorrente().getPersonaggio().saluta());
	}

}
