package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	//implementato qui temporaneamente
	private final String[] elencoComandi = { "vai", "aiuto", "fine", 
													"posa", "prendi" };
	
	@Override
	public void esegui(Partita partita, IO io) {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Comandi disponibili: ");
		for (int i = 0; i < elencoComandi.length; i++)
			risultato.append(elencoComandi[i] + " ");
		io.mostraMessaggio(risultato.toString());
	}
	
	@Override
	public void setParametro(String parametro) {}
}