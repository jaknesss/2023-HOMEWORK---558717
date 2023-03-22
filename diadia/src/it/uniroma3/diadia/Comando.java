package it.uniroma3.diadia;


import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {

    private String nome;
    private String parametro;

    
    //Integrare IOConsole --  come spezzare la riga in nome e in paramtro
    public Comando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		if (scannerDiParole.hasNext()) this.nome = scannerDiParole.next(); 
		if (scannerDiParole.hasNext()) this.parametro = scannerDiParole.next();
    }

    public String getNome() {
        return this.nome;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {
        return (this.nome == null);
    }
    
    public boolean hasComando(String[] elencComandi) {
    	for(int i = 0; i < elencComandi.length; i++) {
    		if(elencComandi[i] != null && this.nome.equals(elencComandi[i])) {
    			return true;
    		}
    	}
    	return false;
    }
}