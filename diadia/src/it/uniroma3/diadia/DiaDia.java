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

	public DiaDia(IO io) {
		this.partita = new Partita();
	}

	public void gioca(IO io) {
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = io.leggiRiga();
		}while (!processaIstruzione(istruzione, io));
	}

	private boolean processaIstruzione(String istruzione, IO io) {
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, io);
		if (this.partita.isVinta())
			io.mostraMessaggio("Hai vinto!");
		if (this.partita.giocatoreIsMorto())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}


	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca(io);
	}
}