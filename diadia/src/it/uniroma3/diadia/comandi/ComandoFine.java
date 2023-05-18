package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{

	private final String NOME_COMANDO = "fine";

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg("Grazie di aver giocato!");
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

}
