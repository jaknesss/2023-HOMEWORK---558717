package it.uniroma3.diadia;


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

	private Partita partita;
	private Labirinto lab;
	private Giocatore giocatore;
	private Borsa borsa;
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
		this.lab = partita.getLabirinto();
		this.giocatore = partita.getGiocatore();
		this.borsa = giocatore.getBorsa();
	}

	public void gioca(IOConsole iOConsole) {
		iOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione;
		do
			istruzione = iOConsole.leggiRiga();
		while (!processaIstruzione(istruzione, iOConsole));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole iOConsole) {
		Comando daEseguire = new Comando(istruzione);
		if (daEseguire.sconosciuto())
			iOConsole.mostraMessaggio("Inserisci un comando da eseguire");
		else if (!daEseguire.hasComando(elencoComandi))
			iOConsole.mostraMessaggio("Comando Inesistente");
		else if (daEseguire.getNome().equals("vai"))
			this.vai(daEseguire.getParametro(), iOConsole);
		else if (daEseguire.getNome().equals("aiuto"))
			this.aiuto(iOConsole);
		else if (daEseguire.getNome().equals("posa"))
			this.posa(daEseguire.getParametro(), iOConsole);
		else if (daEseguire.getNome().equals("prendi"))
			this.prendi(daEseguire.getParametro(),iOConsole);
		else if (daEseguire.getNome().equals("fine")) {
			this.fine(iOConsole);
			return true;
		}
		if (this.partita.isVinta()) {
			iOConsole.mostraMessaggio("---| HAI VINTO |---");
			return true;
		} else {
			iOConsole.mostraMessaggio(lab.getStanzaCorrente().getDescrizione());
			iOConsole.mostraMessaggio(borsa.toString());
			return false;
		}
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */

	// Uso System.out.println() altrimenti formatta male i comandi di aiuto

	private void aiuto(IOConsole iOConsole) {
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
	private void vai(String direzione, IOConsole iOConsole) {
		if (direzione == null)
			iOConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza stanzaCorrente = lab.getStanzaCorrente();
		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza != null) {
			lab.setStanzaCorrente(prossimaStanza);
			giocatore.setCfu(giocatore.getCfu() - 1);
			return;
		}
		iOConsole.mostraMessaggio("Non c'e' una stanza in quella direzione");
	}

	public void posa(String nomeAttrezzo, IOConsole iOConsole) {
		if (nomeAttrezzo == null)
			iOConsole.mostraMessaggio("Cosa vuoi posare?");
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		if (borsa.hasAttrezzo(nomeAttrezzo))
			stanzaCorrente.addAttrezzo(borsa.removeAttrezzo(nomeAttrezzo));
		else
			iOConsole.mostraMessaggio("Non hai questo oggetto nella borsa");
	}

	public void prendi(String nomeAttrezzo, IOConsole iOConsole) {
		if (nomeAttrezzo == null)
			iOConsole.mostraMessaggio("Cosa vuoi prendere");
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo daPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			if(borsa.addAttrezzo(daPrendere))
				stanzaCorrente.removeAttrezzo(daPrendere);				
		}else
			iOConsole.mostraMessaggio("Non c'è questo oggeto nella stanza");
	}

	/**
	 * Comando "Fine".
	 */
	private void fine(IOConsole iOConsole) {
		iOConsole.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole iOConsole = new IOConsole();
		gioco.gioca(iOConsole);
	}
}