package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Questa classe modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO
 * @version base
 */

public class Comando {

	private String nome;
	private String parametro;
	
	/**
	 * Memorizza il nome del comando e il parametro ad esso associato
	 * 
	 * @param istruzione - istruzione da memorizzare
	 */
	public Comando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next();
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
	}

	
	/**
	 * Restituisce il nome del comando 
	 * 
	 * @return nome - nome del comando
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il paramtro del comando
	 * 
	 * @return parametro - parametro del comando
	 */
	public String getParametro() {
		return this.parametro;
	}

	
	/**
	 * Restituisce se il node del comando è nullo
	 * 
	 * @return vero - il comando è sconosciuto
	 */
	public boolean sconosciuto() {
		return (this.nome == null);
	}

	
	/**
	 * Restituisce vero se il nome del comnado esiste o meno
	 * nell'elenco di comandi possibili
	 * 
	 * @param elencComandi - elenco dei comnadi possibili
	 * @return vero - esiste il nome del comando nell'elenco di quelli disponibili
	 */
	public boolean hasComando(String[] elencComandi) {
		for (int i = 0; i < elencComandi.length; i++)
			if (elencComandi[i] != null && this.nome.equals(elencComandi[i]))
				return true;
		return false;
	}
}