package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione(io) + 
						   partita.getGiocatore().toString());
	}
}
