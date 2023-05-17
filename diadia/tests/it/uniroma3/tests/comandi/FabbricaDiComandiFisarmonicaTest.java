package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.Comando;
import it.uniroma3.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiRiflessiva factory;
	private Comando comando;
	private final String COMANDO = "vai";
	private final String PARAM = "nord";
		
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new FabbricaDiComandiRiflessiva();
	}

	@Test
	void testNomeComandoCorretto() {
		creaComando(COMANDO, null);
		assertEquals(factory.getNome(), factory.getComando().getNome());
	}
	
	@Test
	void testParametroCorrettoComandoNonCreato() {
		creaComando(null, PARAM);
		assertNull(factory.getComando().getParam());
	}
	
	@Test
	void testNomeComandoEParametroCorretti() {
		creaComando(COMANDO, PARAM);
		assertEquals(factory.getNome(), factory.getComando().getNome());
		assertEquals(factory.getParam(), factory.getComando().getParam());
	}

	private void creaComando(String COMANDO, String PARAM) {
		comando = factory.costruisciComando(COMANDO+ " " +PARAM);
	}
}
