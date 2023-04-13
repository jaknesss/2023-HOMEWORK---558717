package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	//implementato qui temporaneamente
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", 
													"posa", "prendi" };
	
	@Override
	public void esegui(Partita partita) {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Comandi disponibili: ");
		for (int i = 0; i < elencoComandi.length; i++)
			risultato.append(elencoComandi[i] + " ");
		System.out.println(risultato.toString());
	}
	
	@Override
	public void setParametro(String parametro) {}
}
