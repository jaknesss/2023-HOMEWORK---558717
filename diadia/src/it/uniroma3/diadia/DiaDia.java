package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {	

	public IOConsole iOConsole;
	private Partita partita;
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "posa", "prendi" };
	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";



	public DiaDia() {
		this.partita = new Partita();
		this.iOConsole = new IOConsole();
	}

	public void gioca() {
		iOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione;
		do
			istruzione = iOConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando DaEseguire = new Comando(istruzione);
		if (DaEseguire.sconosciuto())
			iOConsole.mostraMessaggio("Inserisci un comando da eseguire");
		else if(!DaEseguire.hasComando(elencoComandi))
			iOConsole.mostraMessaggio("Comando Inesistente");
		else if (DaEseguire.getNome().equals("vai"))
			this.vai(DaEseguire.getParametro());
		else if (DaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (DaEseguire.getNome().equals("posa")) 
			this.posa(DaEseguire.getParametro());
		else if (DaEseguire.getNome().equals("prendi")) 
			this.prendi(DaEseguire.getParametro());
		else if (DaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		}
		if (this.partita.vinta()) {
			iOConsole.mostraMessaggio("---| HAI VINTO |---");
			return true;
		}else {
			iOConsole.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
			iOConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
			return false;			
		}
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */

	// Uso System.out.println() altrimenti formatta male i comandi di aiuto

	private void aiuto() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Comandi disponibili: ");
		for (int i = 0; i < elencoComandi.length; i++) {
			risultato.append(elencoComandi[i] + " ");
		}
		iOConsole.mostraMessaggio(risultato.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) iOConsole.mostraMessaggio("Dove vuoi andare ?");
		Labirinto lab = partita.getLabirinto();
		Giocatore giocatore = partita.getGiocatore();
		Stanza stanzaCorrente = lab.getStanzaCorrente();
		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza != null) {
			lab.setStanzaCorrente(prossimaStanza);
			giocatore.setCfu(giocatore.getCfu()-1);
			return;
		}
		iOConsole.mostraMessaggio("Non c'e' una stanza in quella direzione");
	}

	public void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) 
			iOConsole.mostraMessaggio("Cosa vuoi posare?");
		Borsa borsa = partita.getGiocatore().getBorsa(); 
		Stanza stanza = partita.getLabirinto().getStanzaCorrente();
		if (borsa.hasAttrezzo(nomeAttrezzo)){
			stanza.addAttrezzo(borsa.removeAttrezzo(nomeAttrezzo));
			return;
		}
		iOConsole.mostraMessaggio("Non hai questo oggetto nella borsa");			
	}

	public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo == null) iOConsole.mostraMessaggio("Cosa vuoi prendere");
		Borsa borsa = partita.getGiocatore().getBorsa(); 
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			stanzaCorrente.removeAttrezzo(attrezzo);
			borsa.addAttrezzo(attrezzo);
			return;
		}
		iOConsole.mostraMessaggio("Non c'è questo oggeto nella stanza");
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}