package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{
	
	private String daRegalare;
	
	@Override
	public void esegui(Partita partita, IO io) {
		Giocatore g = partita.getGiocatore();
		AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
		if(p != null && g.getBorsa().hasAttrezzo(daRegalare)) {
			io.mostraMsg(p.riceviRegalo(g.getBorsa().getAttrezzo(daRegalare), partita));
			g.getBorsa().removeAttrezzo(daRegalare);
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.daRegalare = parametro;
	}
	
}
