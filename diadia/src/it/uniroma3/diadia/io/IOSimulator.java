package it.uniroma3.diadia.io;

public class IOSimulator implements IO{

	private String[] comandiLetti;
	private int indiceProssimoComando;
	
	public IOSimulator (String...comandiLetti) {
		this.comandiLetti = comandiLetti;
		this.indiceProssimoComando = 0;
	}
	
	@Override
	public void mostraMsg(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		if(comandiLetti == null) return null;
		if(indiceProssimoComando >= comandiLetti.length) return null;
		return comandiLetti[indiceProssimoComando++];
	} 
}
