package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public class ComandoNonValido extends AbstractComando{
	
	private static final String NOME_COMANDO = "nonValido";
	
	public ComandoNonValido() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg("Comando Sconosciuto\n" +
	                 "Inserisci un nuovo comando da eseguire");
	}
	
}
