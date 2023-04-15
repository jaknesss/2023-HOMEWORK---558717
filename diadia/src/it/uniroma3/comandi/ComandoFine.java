package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

	private final String NOME_COMANDO = "fine";

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Grazie di aver giocato!");
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}

	@Override
	public String getParam() {
		return null;
	}

}
