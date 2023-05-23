package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public class ComandoGuarda extends AbstractComando{

	private static final String NOME_COMANDO = "guarda";

	public ComandoGuarda() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg(partita.getStanzaCorrente().getDescrizione(io) + 
						   partita.getGiocatore().toString());
	}
	
}
