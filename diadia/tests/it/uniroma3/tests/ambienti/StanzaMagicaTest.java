package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private final int PESO_OGGETTO = 1;
	private final String NOME_STANZA = "atrio";
	private final String NOME_ATTR = "chiave";
	private final String NOME_ATTR_INV = "evaihc";
	private Attrezzo attrDaTestare;
	private StanzaMagica stanzaMagica;
	private Attrezzo attrezzo;
	private IO io;

	@BeforeEach
	void setUp() {
		io = new IOConsole();
		stanzaMagica = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
		attrDaTestare = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
	}

	@Test
	void testPeso_Raddoppiato() {
		raggiungiSogliaMagica();
		assertTrue(stanzaMagica.addAttrezzo(attrDaTestare, io));
		assertEquals(PESO_OGGETTO * 2, stanzaMagica.getAttrezzo(NOME_ATTR_INV).getPeso());
	}
	
	@Test
	void testPeso_NonRaddoppiato() {
		assertTrue(stanzaMagica.addAttrezzo(attrezzo, io));
		assertEquals(PESO_OGGETTO, stanzaMagica.getAttrezzo(NOME_ATTR).getPeso());
	}

	@Test
	void testNome_NonInvertito() {
		assertTrue(stanzaMagica.addAttrezzo(attrezzo, io));
		assertEquals(NOME_ATTR, stanzaMagica.getAttrezzo(NOME_ATTR).getNome());
	}

	@Test
	void testNome_Invertito() {
		raggiungiSogliaMagica();
		assertTrue(stanzaMagica.addAttrezzo(attrDaTestare, io));
		assertEquals(NOME_ATTR_INV, stanzaMagica.getAttrezzo(NOME_ATTR_INV).getNome());
	}


	void raggiungiSogliaMagica() {
		for (int i = 0; i < StanzaMagica.SOGLIA_MAGICA_DEFAULT - 1; i++)
			assertTrue(stanzaMagica.addAttrezzo(attrezzo, io));
	}

}
