package it.uniroma3.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoRegala extends AbstractComando{
	
	private String daRegalare;
	
	@Override
	public void esegui(Partita partita, IO io) {
		Giocatore g = partita.getGiocatore();
		if(g.getBorsa().hasAttrezzo(daRegalare)) {
			io.mostraMsg(partita.getStanzaCorrente().getPersonaggio()
				       			.riceviRegalo(g.getBorsa()
						        .getAttrezzo(daRegalare), partita));
			g.getBorsa().removeAttrezzo(daRegalare);
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.daRegalare = parametro;
	}
	
}
