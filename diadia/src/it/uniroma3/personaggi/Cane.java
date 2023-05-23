package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio{
	
	public static final String DESCRIZIONE = "  WOOOF WOOOF *non ti avvicinare* WOOOF WOOOF>>";
	public static final String FURIOSO = "<<WOOOOF WOOOOF *t'ammazzo* WOOOOOF WOOOOOOF>>";
	public static final String CONTENTO = "<<WOOOF WOOOF *scodinzola* WOOOF WOOOF>>";
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		if(this.getAttrezzo() == null || !this.getAttrezzo().getNome().equals("osso")) {
			this.azzanna(partita.getGiocatore());
			return Cane.FURIOSO;
		}
		return Cane.CONTENTO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals("osso")) {
			this.setAttrezzo(attrezzo);
			return Cane.CONTENTO;
		}
		this.azzanna(partita.getGiocatore());		
		return Cane.FURIOSO;
	}
	
	private void azzanna(Giocatore g) {
		g.setCfu(g.getCfu()-1);
	}
	
	@Override
	public String toString() {
		StringBuilder desc = new StringBuilder();
		desc.append(Cane.DESCRIZIONE);
		if(this.getAttrezzo() != null) 
			desc.append("- Ha con se: " + this.getAttrezzo().toString());
		return desc.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
