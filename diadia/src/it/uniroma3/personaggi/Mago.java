package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	public static final String DESCRIZIONE = "  Sono un mago generoso,\n  hai bisongo di qualcosa?>>";
	private static final String MESSAGGIO_DONO = 
									  "<<Sei un vero simpaticone,\n"
									+ "  con una mia magica azione,\n"
									+ "  troverai un nuovo oggetto\n" 
									+ "  per il tuo borsone!>>";
	private static final String MESSAGGIO_SCUSE = "<<Mi spiace, ma non ho piu' nulla...>>";

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.setAttrezzo(attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		if (this.getAttrezzo() == null) return MESSAGGIO_SCUSE;
		partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
		this.setAttrezzo(null);
		return MESSAGGIO_DONO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo attrModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrModificato);
		return "<<Ecco a te!>>\n";
	}
	
	
	@Override
	public String toString() {
		StringBuilder desc = new StringBuilder();
		desc.append(Mago.DESCRIZIONE);
		desc.append("\n- Ha con se: ");
		if(this.getAttrezzo() != null) 
			desc.append(this.getAttrezzo().toString());
		return desc.toString();
	}
	@Override
	public boolean equals(Object obj) {
		Mago that = (Mago) obj;
		return super.equals(obj) && this.getAttrezzo().equals(that.getAttrezzo());
	}
	
	
}
