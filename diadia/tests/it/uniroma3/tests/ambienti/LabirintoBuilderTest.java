package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
	private LabirintoBuilder lab;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@BeforeEach
	public void setUp() throws Exception {
		lab = Labirinto.newBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = lab.addStanzaIniziale(nomeStanzaIniziale)
								  .addStanzaVincente(nomeStanzaIniziale)
								  .getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
	}

	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = lab.addStanzaIniziale(nomeStanzaIniziale)
								  .addAttrezzo("spada", 1)
								  .addStanzaVincente(nomeStanzaIniziale)
								  .addAttrezzo("spadina", 3).getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals("spada", monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina", monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}

	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = lab.addStanzaIniziale(nomeStanzaIniziale)
								  .addAttrezzo("spada", 1)
								  .addAttrezzo("spada", 1)
								  .getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size == 1);
		assertEquals(Arrays.asList(new Attrezzo("spada", 1)), 
					monolocale.getStanzaIniziale().getAttrezziAsList());
	}

	@Test
	public void testBilocale() {
		Labirinto bilocale = lab
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord").getLabirinto();
		assertEquals(bilocale.getStanzaVincente(), bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(Collections.singletonList(Direzione.NORD), bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList(Direzione.SUD), bilocale.getStanzaVincente().getDirezioni());
	}

	@Test
	public void testTrilocale() {
		Labirinto trilocale = lab.addStanzaIniziale(nomeStanzaIniziale)
								 .addAttrezzo("sedia", 1)
								 .addStanza("biblioteca")
								 .addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
								 .addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
								 .addAttrezzo("libro antico", 5)
								 .addStanzaVincente(nomeStanzaVincente)
								 .addAdiacenza("biblioteca", nomeStanzaVincente, "est")
								 .addAdiacenza(nomeStanzaVincente, "biblioteca", "ovest")
								 .getLabirinto();
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca", trilocale.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}

	@Test
	public void testTrilocaleConStanzaDuplicata() {
		lab.addStanzaIniziale(nomeStanzaIniziale)
			.addStanza("stanza generica")
			.addStanza("stanza generica")
			.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
			.getLabirinto();
		assertTrue(lab.getStanze().size() <= 2);
	}

	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = lab.addStanzaIniziale(nomeStanzaIniziale)
							.addStanza("stanza 1")
							.addStanza("stanza 2")
							.addStanza("stanza 3")
							.addStanza("stanza 4")
							.addStanza("stanza 5")
							.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
							.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
							.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud")
							.addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
							.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
							.getLabirinto();
		Stanza test = new Stanza("stanza 5");
		assertTrue(maze.getStanzaIniziale().getStanzeAdiacenti().size() <= 4);
		assertTrue(!maze.getStanzaIniziale().getStanzeAdiacenti().containsValue(test));
		Map<Direzione, Stanza> mappa = new HashMap<>();
		mappa.put(Direzione.NORD, new Stanza("stanza 1"));
		mappa.put(Direzione.SUD, new Stanza("stanza 2"));
		mappa.put(Direzione.EST, new Stanza("stanza 3"));
		mappa.put(Direzione.OVEST, new Stanza("stanza 4"));
		assertEquals(mappa.keySet(), maze.getStanzaIniziale().getStanzeAdiacenti().keySet());
	}

	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = lab.addStanzaIniziale(this.nomeStanzaIniziale)
							.addStanza("nuova iniziale")
							.addStanzaIniziale("nuova iniziale")
							.getLabirinto();
		assertEquals("nuova iniziale", maze.getStanzaIniziale().getNome());
	}

	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.lab.addStanzaIniziale(this.nomeStanzaIniziale)
								 .addAttrezzo(nomeAttrezzo, peso)
								 .getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		assertEquals(attrezzo, maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		lab.addStanzaIniziale(nomeStanzaIniziale)
		   .addStanza(nomeStanza)
		   .addAttrezzo(nomeAttrezzo, peso)
		   .getLabirinto();
		assertTrue(this.lab.getStanze().get(nomeStanza).getAttrezzi()
				.containsValue(new Attrezzo(nomeAttrezzo, peso)));
		assertEquals(new Attrezzo(nomeAttrezzo, peso),
				this.lab.getStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.lab.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		List<Attrezzo> attrezzi = lab.getStanze().get(nomeStanza).getAttrezziAsList();
		assertEquals(attrezzo, attrezzi.get(attrezzi.indexOf(attrezzo)));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.lab.addStanza(nomeStanza1).addAttrezzo(nomeAttrezzo1, peso1).addAttrezzo(nomeAttrezzo2,
				peso2);
		Map<String, Stanza> listaStanze = lab.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}

	@Test // verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.lab.addStanza(nomeStanza1)
				.addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lab.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.lab.addStanza(nomeStanza1)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lab.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.lab.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica) lab.getStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}

	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "primo 1";
		String nomeAttrezzo2 = "secondo 2";
		String nomeAttrezzo2Inv = "2 odnoces";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2 * 2;
		String nomeStanzaMagica = "Stanza Magica";
		this.lab.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = lab.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv, peso2_x2),
					listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), 
					listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}

	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.lab.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAttrezzo("chiave", 1)
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		Stanza stanzaNormale = new Stanza("Atrio");
		stanzaVincente.setStanzaAdiacente(Direzione.SUD, stanzaNormale);
		// Asserisce che in presenza di passepartout, invocato il metodo
		// getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente, lab.getStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}

	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.lab.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", Direzione.NORD, "chiave");
		Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		stanzaBloccata.setStanzaAdiacente(Direzione.SUD, stanzaIniziale);
		stanzaBloccata.setStanzaAdiacente(Direzione.NORD, stanzaVincente);
		assertEquals(stanzaBloccata, lab.getStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}

	@Test
	public void testLabirintoCompletoConTutteLeStanze() {

		Labirinto labCompleto = this.lab
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato", "nord", "chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia", "lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labCompleto.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals("corridoio", corridoio.getNome());
		Map<Direzione, Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzione.NORD, new Stanza("corridoio bloccato"));
		mapAdiacenti.put(Direzione.SUD, new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzione.EST, new StanzaMagica("stanza magica"));
		mapAdiacenti.put(Direzione.OVEST, new StanzaBuia("stanza buia", "lanterna"));
		assertEquals(mapAdiacenti.size(), corridoio.getStanzeAdiacenti().size());
		assertEquals(mapAdiacenti.keySet(), corridoio.getStanzeAdiacenti().keySet());
		Attrezzo a1 = new Attrezzo("chiave", 1);
		Attrezzo a2 = new Attrezzo("lanterna", 1);
		assertEquals(Arrays.asList(a1, a2), corridoio.getAttrezziAsList());
	}
}