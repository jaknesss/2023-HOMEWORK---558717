package it.uniroma3.diadia.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.Cane;
import it.uniroma3.personaggi.Mago;
import it.uniroma3.personaggi.Strega;

public class CaricatoreLabirinto {

	private static final String STANZE_MARKER = "Stanze:";
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";
	private static final String STANZE_MAGICE_MARKER = "Magica:";
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";
	private static final String STANZE_BUIE_MARKER = "Buia:";
	private static final String PERSONAGGI_MAGO_MARKER = "Mago:";
	private static final String PERSONAGGI_STREGA_MARKER= "Strega:";
	private static final String PERSONAGGI_CANE_MARKER = "Cane:";
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String USCITE_MARKER = "Uscite:";
	private static final int PESO_ATTR_MAGO = 4;

	private LineNumberReader reader;
	private Set<String> stanze;
	private LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) {
		this.stanze = new HashSet<>();
		this.reader = new LineNumberReader(reader);
		this.builder = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
			this.stanze.add(nomeStanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICE_MARKER);
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanzaMagicaNoSoglia(nomeStanza);
			this.stanze.add(nomeStanza);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String rigaStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for (String stanza : separaStringheAlleVirgole(rigaStanze)) {

			try (Scanner scannerDiLinea = new Scanner(stanza)) {
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("la  stanza " + stanza + " non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),
							msgTerminazionePrecoce(
									"vi è stato qualche problema nella creazione dell'attrezzo per vedere la stanza "
											+ stanza + "\n"));
					String attrezzoPerVedere = scannerDiLinea.next();
					this.builder.addStanzaBuia(nomeStanza, attrezzoPerVedere);
					this.stanze.add(nomeStanza);
				}
			}
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+" non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  direzione della stanza"+ specifica+" non esiste\n"));
					String direzione = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per sbloccare la stanza "+specifica+"\n"));
					String attrezzoSbloccante = scannerDiLinea.next();
					builder.addStanzaBloccata(nomeStanza, direzione, attrezzoSbloccante);
					stanze.add(nomeStanza);
				}
			}
		} 
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MAGO_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il mago non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del mago ...\n"));
					String mago = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String attrezzo = scannerDiLinea.next();
					builder.getStanza(nomeStanza).setPersonaggio(new Mago(mago, Mago.DESCRIZIONE, new Attrezzo(attrezzo, PESO_ATTR_MAGO)));
				}
			}
		} 
	}
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_STREGA_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere la strega non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione della strega ...\n"));
					String strega = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione della strega\n"));
					String presentazione = scannerDiLinea.next();					
					builder.getStanza(nomeStanza).setPersonaggio(new Strega(strega, presentazione));
				}
			}
		} 
	}
	
	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_CANE_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il cane non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del cane ...\n"));
					String cane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del cane\n"));
					String presentazione = scannerDiLinea.next();					
					builder.getStanza(nomeStanza).setPersonaggio(new Cane(cane, presentazione));
				}
			}
		} 
	}
	
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		stanze.add(nomeStanzaIniziale);
		builder.addStanzaIniziale(nomeStanzaIniziale);
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		stanze.add(nomeStanzaVincente);
		builder.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.builder.getStanza(nomeStanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.stanze.contains(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce(
						"la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + dir);
		builder.addAdiacenza(stanzaDa, nomeA, dir);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}
