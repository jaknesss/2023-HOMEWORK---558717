package it.uniroma3.tests.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.comandi.FabbricaDiComandi;
import it.uniroma3.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.IO;

class testDiAccettazione {

	private FabbricaDiComandi factory;
	private IO sim;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new FabbricaDiComandiRiflessiva();
	}
}
