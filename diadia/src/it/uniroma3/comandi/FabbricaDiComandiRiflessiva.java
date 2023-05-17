package it.uniroma3.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private Comando comando;
	String nomeComando;
	String parametro;

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione); 
		String nomeComando = null; 
		String parametro = null; 
		Comando comando = null;
		if (scannerDiParole.hasNext()) nomeComando = scannerDiParole.next();
		if (scannerDiParole.hasNext()) parametro = scannerDiParole.next(); 
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			nomeClasse.append(nomeComando.substring(1));
			comando = (Comando) Class.forName(nomeClasse.toString()).getDeclaredConstructor().newInstance();
			comando.setParametro(parametro);
		}catch(Exception e) {
			comando = new ComandoNonValido();
		}
		return comando;
	}

	public String getNome() {
		return nomeComando;
	}

	public String getParam() {
		return parametro;
	}

	public Comando getComando() {
		return comando;
	}
}
