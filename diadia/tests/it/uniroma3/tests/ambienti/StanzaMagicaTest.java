package it.uniroma3.tests.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private final int SOGLIA_MAGICA= 3;
	private final int PESO_OGGETTO = 2;
	private final String NOME_STANZA = "atrio";
	private final String NOME_ATTR = "chiave";
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	
	@BeforeEach
	void setUp(){
		stanza = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
	}

	@Test
	void testAddAttrezzoSingoloPesoSogliaDefault() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(PESO_OGGETTO, stanza.getAttrezzo(NOME_ATTR).getPeso() ); 
	}
	
	@Test
	void testAddAttrezziOltreSogliaDefault() {
		StringBuilder nomeDaInvertire = new StringBuilder("nome");
		Attrezzo attrDaTestare = new Attrezzo(nomeDaInvertire.toString(), PESO_OGGETTO);
		for(int i = 0; i < SOGLIA_MAGICA-1; i++)
			assertTrue(stanza.addAttrezzo(attrezzo));	
		stanza.addAttrezzo(attrDaTestare);
		assertEquals(PESO_OGGETTO*2, stanza.getAttrezzo(nomeDaInvertire.reverse().toString()).getPeso()); 
	}
}
