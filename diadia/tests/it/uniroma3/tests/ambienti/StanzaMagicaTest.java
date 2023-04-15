package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private final int SOGLIA_MAGICA = 3;
	private final int PESO_OGGETTO = 1;
	private final String NOME_STANZA = "atrio";
	private final String NOME_ATTR = "chiave";
	private final String NOME_ATTR_INV = "evaihc";
	private Attrezzo attrDaTestare;
	private StanzaMagica stanza;
	private Attrezzo attrezzo;
	private IO io;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		stanza = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
		attrDaTestare = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
	}

	@Test
	void testPeso_NonRaddoppiato() {
		assertTrue(stanza.addAttrezzo(attrezzo, io));
		assertEquals(PESO_OGGETTO, stanza.getAttrezzo(NOME_ATTR).getPeso());
	}

	@Test
	void testNome_NonInvertito() {
		assertTrue(stanza.addAttrezzo(attrezzo, io));
		assertEquals(NOME_ATTR, stanza.getAttrezzo(NOME_ATTR).getNome());
	}

	@Test
	void testNome_Invertito() {
		raggiungiSoglia();
		assertTrue(stanza.addAttrezzo(attrDaTestare, io));
		System.out.println(attrDaTestare.getNome());
		assertEquals(NOME_ATTR_INV, stanza.getAttrezzo(NOME_ATTR_INV).getNome());
	}

	@Test
	void testPeso_Raddoppiato() {
		raggiungiSoglia();
		assertTrue(stanza.addAttrezzo(attrDaTestare, io));
		assertEquals(PESO_OGGETTO * 2, stanza.getAttrezzo(NOME_ATTR_INV).getPeso());
	}

	void raggiungiSoglia() {
		for (int i = 0; i < SOGLIA_MAGICA - 1; i++)
			assertTrue(stanza.addAttrezzo(attrezzo, io));
	}

}
