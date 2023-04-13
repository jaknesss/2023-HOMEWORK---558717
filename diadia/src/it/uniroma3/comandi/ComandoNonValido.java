package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{

	@Override
	public void esegui(Partita partita) {
		System.out.println("Comando Sconosciuto. Inserisci un nuovo comando da eseguire");
	}
	
	@Override
	public void setParametro(String parametro) {}
}
