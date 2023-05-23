package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private Attrezzo attrPosseduto;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setAttrezzo(Attrezzo ricevuto) {
		this.attrPosseduto = ricevuto;
	}
	public Attrezzo getAttrezzo() {
		return this.attrPosseduto;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("\n----------------------------------\n");
		if (!haSalutato) {
			risposta.append("<<Ciao, io sono "+ this.getNome() + ".\n");
			risposta.append(this.toString());
		}else risposta.append("<<Ci siamo gia' presentati!>>");
		risposta.append("\n----------------------------------\n");
		this.haSalutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || this.getClass() != obj.getClass()) return false;
		AbstractPersonaggio that = (AbstractPersonaggio) obj; 
		return this.getNome().equals(that.getNome());
	}
	
}
