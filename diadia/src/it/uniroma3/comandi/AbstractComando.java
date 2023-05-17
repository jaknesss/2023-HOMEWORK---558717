package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	private String param;
	
	public abstract void esegui(Partita partita, IO io);
	
	public void setParametro(String parametro) {
		this.param = parametro;
	}
	
	public String getNome() {
		return this.getNome();
	}
	public String getParam() {
		return this.getParam();
	}
}
