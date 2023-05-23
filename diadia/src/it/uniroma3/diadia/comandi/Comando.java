package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public interface Comando {
	public void esegui(Partita partita, IO io);
	public void setParametro(String parametro);
	public String getNome();
	public String getParam();
}
