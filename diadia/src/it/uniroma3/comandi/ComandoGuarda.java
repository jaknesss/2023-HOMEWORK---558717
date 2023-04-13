package it.uniroma3.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda implements Comando{
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public void esegui(Partita partita) {
		System.out.println(partita.getStanzaCorrente().getDescrizione() + 
						   partita.getGiocatore().toString());
	}
}
