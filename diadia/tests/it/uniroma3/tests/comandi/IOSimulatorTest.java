package it.uniroma3.tests.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;

class IOSimulatorTest {
	
	private IO sim;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testNessunComando() {
		sim = new IOSimulator(null);
		assertNull(sim.leggiRiga());
	}
	
	@Test
	void testSingoloComando() {
		sim = new IOSimulator("guarda");
		assertEquals("guarda", sim.leggiRiga());
	}
	
	@Test
	void testSingoloComandoSbagliato() {
		sim = new IOSimulator("guarda");
		assertNotEquals("test", sim.leggiRiga());
	}
	
	
	@Test
	void testDoppioComando() {
		sim = new IOSimulator("guarda", "vai sud");
		assertEquals("guarda", sim.leggiRiga());
		assertEquals("vai sud", sim.leggiRiga());
	}
	
	@Test
	void testDoppioComandoSbagliato() {
		sim = new IOSimulator("guarda", "vai sud");
		assertEquals("guarda", sim.leggiRiga());
		assertNotEquals("vai nord", sim.leggiRiga());
	}
	
	@Test
	void testComandoOutOfBound() {
		sim = new IOSimulator("guarda", "vai sud");
		assertEquals("guarda", sim.leggiRiga());
		assertEquals("vai sud", sim.leggiRiga());
		assertNull(sim.leggiRiga());
	}
	

}
