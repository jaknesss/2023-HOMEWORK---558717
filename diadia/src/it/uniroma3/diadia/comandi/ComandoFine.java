package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public class ComandoFine extends AbstractComando{

	private static final String NOME_COMANDO = "fine";

	public ComandoFine() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMsg("Grazie di aver giocato!");
	}
}
