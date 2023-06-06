package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.diadia.io.IOConsole;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

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

	public DiaDia(Labirinto lab, IO io) {
		this.partita = new Partita(lab, io);
	}

	public void gioca(IO io) {
		io.mostraMsg(MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = io.leggiRiga();
		} while (!processaIstruzione(istruzione, io));
	}

	private boolean processaIstruzione(String istruzione, IO io) {
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, io);
		if (this.partita.isVinta())
			io.mostraMsg("Hai vinto!");
		if (this.partita.giocatoreIsMorto())
			io.mostraMsg("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		Labirinto lab = Labirinto.newBuilder()
							.addStanzaIniziale("LabCampusOne")
							.addAttrezzo("bomba", 1)
							.addAttrezzo("osso", 1)
							.addMago("Mago Merlino", Mago.DESCRIZIONE, new Attrezzo("ascia", 3))
							.addStanza("Atrio")
							.addStrega("Morgana", Strega.DESCRIZIONE)
							.addStanza("N10")
							.addStanzaVincente("Biblioteca")
							.addAdiacenza("LabCampusOne", "Atrio", "ovest")
							.addAdiacenza("Atrio", "LabCampusOne", "est")
							.addAdiacenza("Atrio", "N10", "sud")
							.addAdiacenza("N10", "Atrio", "nord")
							.addAdiacenza("Atrio", "Biblioteca", "ovest")
							.getLabirinto();
		DiaDia gioco = new DiaDia(lab, io);
		gioco.gioca(io);
	}
}