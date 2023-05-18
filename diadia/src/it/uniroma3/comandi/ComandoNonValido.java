package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	private final String NOME_COMANDO = "nonValido";
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMsg("Comando Sconosciuto\n" +
	                 "Inserisci un nuovo comando da eseguire");
	}
	
	@Override
	public String getNome() {
		return this.NOME_COMANDO;
	}
	
}
