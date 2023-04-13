package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.IO;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandi factory;
	private Comando comando;
	private final String COMANDO = "vai";
	private final String PARAM = "nord";
		
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testNomeComandoCorretto() {
		eseguiComando(COMANDO, null);
		assertEquals(COMANDO, factory.getNome());
	}
	
	@Test
	void testParametroCorretto() {
		eseguiComando(null, PARAM);
		assertEquals(PARAM, factory.getParam());
	}
	
	@Test
	void testComandoEParametroCorretti() {
		eseguiComando(COMANDO, PARAM);
		assertEquals(COMANDO, factory.getNome());
		assertEquals(PARAM, factory.getParam());
	}

	private void eseguiComando(String COMANDO, String PARAM) {
		comando = factory.costruisciComando(COMANDO+" "+PARAM);
	}
}
