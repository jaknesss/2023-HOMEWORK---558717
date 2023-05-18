package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String param;
	private String nome;
	
	public abstract void esegui(Partita partita, IO io);
	
	public void setParametro(String parametro) {
		this.param = parametro;
	}
	
	public String getNome() {
		return this.nome;
	}
	public String getParam() {
		return this.param;
	}
}
