package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
	public void esegui(Partita partita, IO io);
	public void setParametro(String parametro);
	
}
