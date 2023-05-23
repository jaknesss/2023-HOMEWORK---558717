package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.io.IO;

public class ComandoAiuto extends AbstractComando{

	private static final String NOME_COMANDO = "aiuto";
	private final String[] elencoComandi = {"vai", "aiuto", "fine", "posa", "prendi", "saluta", "regala", "interagisci"};
	
	public ComandoAiuto() {
		super.setNome(NOME_COMANDO);
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Comandi disponibili: ");
		for (int i = 0; i < this.elencoComandi.length; i++)
			risultato.append("[" + this.elencoComandi[i] + "] ");
		io.mostraMsg(risultato.toString());
    }
		
}
		
