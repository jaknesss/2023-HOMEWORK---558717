package it.uniroma3.personaggi;


import it.uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio{
	
	private static final String DESCRIZIONE = "Sono una strega dispettosa ti conviene salutarmi";
	
	private static final String MSG_NON_SALUTATA = "Ti avevo detto di salutarmi";
	private static final String MSG_SALUTATA = "Buona fortuna";
	
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		StringBuilder msg= new StringBuilder();
		if(this.haSalutato()) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteMaxOggetti());
			msg.append("Visto che sei stato gentile ti ho fatto un bel regalo\n");
			msg.append(MSG_SALUTATA);
		}else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteMinOggetti());
			msg.append("Sei stato alquanto sgarbato\n");
			msg.append(MSG_NON_SALUTATA);
		}
		return msg.toString();
	}
}
