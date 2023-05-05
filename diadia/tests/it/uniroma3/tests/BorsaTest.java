package it.uniroma3.tests;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private final String NOME_ATTREZZO = "attrezzoTest";
	private final int PESO_ATTR = 1;
	private final int PESO_BORSA = 10;
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() {
		borsa = new Borsa(PESO_BORSA);
		attrezzo = new Attrezzo(NOME_ATTREZZO, 1);
	}

	@Test
	public void testAddAttrezzoSingolo() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, borsa.getAttrezzo(NOME_ATTREZZO));
	}

	@Test
	public void testAddAttrezzoMaxPeso() {
		assertTrue(borsa.addAttrezzo(new Attrezzo(NOME_ATTREZZO, PESO_BORSA)));
		assertFalse(borsa.addAttrezzo(new Attrezzo("troppoPesante", 1)));
	}

	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		Attrezzo attrezzoNonAggiunto= new Attrezzo("nonAggiunto", PESO_ATTR);
		assertFalse(borsa.removeAttrezzo(attrezzoNonAggiunto.getNome()));
	}

	@Test
	public void testRemoveAttrezzoSingolo() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.removeAttrezzo(attrezzo.getNome()));
	}

	@Test
	public void testHasAttrezzo() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(borsa.hasAttrezzo(attrezzo.getNome()));
	}

	@Test
	public void testRemoveAttrezzoInesistente() {
		Attrezzo attrezzoDaRimuovere = new Attrezzo("nonAggiunto", PESO_ATTR);
		borsa.addAttrezzo(attrezzo);
		assertFalse(borsa.removeAttrezzo(attrezzoDaRimuovere.getNome()));
	}
	
	@Test
	public void testBorsaOrdinatePer_Peso() {
		Attrezzo primo = new Attrezzo("primo", 1);
		Attrezzo secondo = new Attrezzo("secondo", 2);
		assertTrue(borsa.addAttrezzo(secondo));
		assertTrue(borsa.addAttrezzo(primo));
		List<Attrezzo> expected = Arrays.asList(primo, secondo);
		assertEquals(expected, borsa.getContenutoOrdinatoPerPeso());	
	}
	
	@Test
	public void testBorsaOrdinatePerPeso_StessoPeso() {
		Attrezzo primo = new Attrezzo("primo", PESO_ATTR);
		Attrezzo secondo = new Attrezzo("secondo", PESO_ATTR);
		assertTrue(borsa.addAttrezzo(secondo));
		assertTrue(borsa.addAttrezzo(primo));
		List<Attrezzo> expected = Arrays.asList(primo, secondo);
		assertEquals(expected, borsa.getContenutoOrdinatoPerPeso());	
	}
	
	@Test
	public void testBorsaOrdinatoPer_Nome() {
		Attrezzo primo = new Attrezzo("primo", 1);
		Attrezzo secondo = new Attrezzo("secondo", 2);
		assertTrue(borsa.addAttrezzo(secondo));
		assertTrue(borsa.addAttrezzo(primo));
		SortedSet<Attrezzo> expected = new TreeSet<>();
		expected.add(primo);
		expected.add(secondo);
		assertEquals(expected, borsa.getContenutoOrdinatoPer_Nome());	
	}
	
	@Test
	public void testBorsaOrdinatoPer_NomePesoUguale() {
		Attrezzo primo = new Attrezzo("primo", PESO_ATTR);
		Attrezzo secondo = new Attrezzo("secondo", PESO_ATTR);
		assertTrue(borsa.addAttrezzo(secondo));
		assertTrue(borsa.addAttrezzo(primo));
		SortedSet<Attrezzo> expected = new TreeSet<>();
		expected.add(primo);
		expected.add(secondo);
		assertEquals(expected, borsa.getContenutoOrdinatoPer_Nome());	
	}
	
	@Test
	public void testBorsaRaggruppatoPerPeso() {
		Attrezzo primo = new Attrezzo("primo", PESO_ATTR);
		Attrezzo secondo = new Attrezzo("secondo", PESO_ATTR);
		Attrezzo terzo = new Attrezzo("terzo", 2);
		Map<Integer, Set<Attrezzo>> expected = new HashMap<>();
		Set<Attrezzo> set1 = new HashSet<>();
		Set<Attrezzo> set2 = new HashSet<>();
		set1.add(primo);
		set1.add(secondo);
		set2.add(terzo);
		expected.put(PESO_ATTR, set1);
		expected.put(2, set2);
		assertTrue(borsa.addAttrezzo(secondo));
		assertTrue(borsa.addAttrezzo(primo));
		assertTrue(borsa.addAttrezzo(terzo));
		assertEquals(expected, borsa.getContenutoRaggruppatoPerPeso());	
	}
	
}
