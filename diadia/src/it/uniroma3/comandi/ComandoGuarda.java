package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private final String NOME_COMANDO = "guarda";

	@Override
	public void setParametro(String parametro) {}

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione(io) + 
						   partita.getGiocatore().toString());
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
