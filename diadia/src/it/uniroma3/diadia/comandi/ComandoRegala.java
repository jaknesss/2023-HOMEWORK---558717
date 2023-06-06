package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.io.IO;
import it.uniroma3.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String NOME_COMANDO = "regala";

	public ComandoRegala() {
		super.setNome(NOME_COMANDO);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		Giocatore g = partita.getGiocatore();
		AbstractPersonaggio p = partita.getStanzaCorrente().getPersonaggio();
		String attrDaRegalare = this.getParam();
		if (p == null) {
			io.mostraMsg("Ma qui non c'è nessuno!\n");
			return;
		}
		if (!g.getBorsa().hasAttrezzo(attrDaRegalare)) {
			io.mostraMsg("Non hai questo oggetto nella Borsa\n");
			return;
		}
		io.mostraMsg(p.riceviRegalo(g.getBorsa().getAttrezzo(attrDaRegalare), partita));
		g.getBorsa().removeAttrezzo(attrDaRegalare);

	}
}
