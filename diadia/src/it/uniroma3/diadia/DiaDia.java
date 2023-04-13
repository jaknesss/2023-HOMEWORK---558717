package it.uniroma3.diadia;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiFisarmonica;

public class DiaDia {

	private Partita partita;
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
	}

	public void gioca(IOConsole iOConsole) {
		iOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = iOConsole.leggiRiga();
		}while (!processaIstruzione(istruzione));
	}

	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.isVinta())
			System.out.println("Hai vinto!");
		if (this.partita.giocatoreIsMorto())
			System.out.println("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole iOConsole = new IOConsole();
		gioco.gioca(iOConsole);
	}
}