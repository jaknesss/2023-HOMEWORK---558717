package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	private final String NOME_COMANDO = "aiuto";
	private final String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi"};
	
	
	@Override
	public void esegui(Partita partita, IO io) {
//		StringBuilder risultato = new StringBuilder();
//		risultato.append("Comandi disponibili: ");
//		for (int i = 0; i < elencoComandi.length; i++)
//			risultato.append("["+elencoComandi[i]+"] ");
//		io.mostraMessaggio(risultato.toString());
		Package pkg = this.getClass().getPackage();
		
	
	}
		

	@Override
	public String getNome() {
		return NOME_COMANDO;
	}
}
