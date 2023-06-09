package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private final int PESO = 1;
	private final String NOME_STANZA = "atrio";
	private final String NOME_ATTR = "chiave";
	private final String NOME_ATTR_INV = "evaihc";
	private Attrezzo attrDaTestare;
	private StanzaMagica stanzaMagica;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() {
		stanzaMagica = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO);
		attrDaTestare = new Attrezzo(NOME_ATTR, PESO);
	}

	@Test
	void testPeso_Raddoppiato() {
		raggiungiSogliaMagica();
		assertTrue(stanzaMagica.addAttrezzo(attrDaTestare));
		assertEquals(PESO * 2, stanzaMagica.getAttrezzo(NOME_ATTR_INV).getPeso());
	}
	
	@Test
	void testPeso_NonRaddoppiato() {
		assertTrue(stanzaMagica.addAttrezzo(attrezzo));
		assertEquals(PESO, stanzaMagica.getAttrezzo(NOME_ATTR).getPeso());
	}

	@Test
	void testNome_NonInvertito() {
		assertTrue(stanzaMagica.addAttrezzo(attrezzo));
		assertEquals(NOME_ATTR, stanzaMagica.getAttrezzo(NOME_ATTR).getNome());
	}

	@Test
	void testNome_Invertito() {
		raggiungiSogliaMagica();
		assertTrue(stanzaMagica.addAttrezzo(attrDaTestare));
		assertEquals(NOME_ATTR_INV, stanzaMagica.getAttrezzo(NOME_ATTR_INV).getNome());
	}

	private void raggiungiSogliaMagica() {
		for (int i = 0; i < StanzaMagica.SOGLIA_MAGICA_DEFAULT; i++)
			stanzaMagica.addAttrezzo(attrezzo);
	}

}
