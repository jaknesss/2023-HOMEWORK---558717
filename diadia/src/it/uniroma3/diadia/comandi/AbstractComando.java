package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public abstract class AbstractComando implements Comando{

	private String nome;
	private String param;
	
	public abstract void esegui(Partita partita, IO io);
	
	public void setParametro(String parametro) {
		this.param = parametro;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getNome() {
		return this.nome;
	}
	public String getParam() {
		return this.param;
	}
}
