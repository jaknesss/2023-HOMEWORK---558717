package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = 
									  "Sei un vero simpaticone, "
									+ "con una mia magica azione, troverai un nuovo oggetto " 
									+ "per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if (this.attrezzo == null) return MESSAGGIO_SCUSE;
		partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.attrezzo = null;
		return MESSAGGIO_DONO;
	}


}
