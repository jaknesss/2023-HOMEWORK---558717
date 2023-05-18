package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private Comando comando;
	String nomeComando;
	String parametro;

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione); 
		if (scannerDiParole.hasNext()) nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext()) parametro = scannerDiParole.next(); 
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (Comando)Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
	        comando.setParametro(parametro);
		}catch(Exception e) {
			comando = (AbstractComando) new ComandoNonValido();
		}
		return comando;
	}

	public String getNome() {
		return nomeComando;
	}

	public String getParam() {
		return parametro;
	}
	@Override
	public Comando getComando() {
		return comando;
	}
}
