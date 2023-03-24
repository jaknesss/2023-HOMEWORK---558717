package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Classe che gestisce l'I/O dell'intero pregetto limitando l'uso di
 * System.out/in
 * 
 * @author fra
 */
public class IOConsole {

	/**
	 * Stampa il messaggio di input
	 * 
	 * @param msg - messaggio da stampare
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	/**
	 * Restituisce la riga di input letta dallo scanner
	 * 
	 * @return riga - riga letta dallo scanner
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
//		scannerDiLinee.close();
		return riga;
	}
}
