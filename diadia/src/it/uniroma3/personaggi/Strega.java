package it.uniroma3.personaggi;

import java.util.Collection;
import java.util.Collections;

import it.uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio{
	
	private static final String DESCRIZIONE = "Sono una strega dispettosa ti conviene salutarmi";
	
	private static final String MSG_NON_SALUTATA = "Ti avevo detto di salutarmi";
	private static final String MSG_SALUTATA = "Buona fortuna";
	
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	
	
	 
	 
//	@Override
//	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
//		
//		return null;
//	}



	@Override
	public String agisci(Partita partita) {
		if(haSalutato()) {
			
		}
		return null;
	}
	
}
