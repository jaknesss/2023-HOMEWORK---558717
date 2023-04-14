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
	private final String NOME_ATTR_INV = "evaihc";
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	
	@BeforeEach
	void setUp(){
		stanza = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
	}

	@Test
	void testPeso_NonRaddoppiato() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(PESO_OGGETTO, stanza.getAttrezzo(NOME_ATTR).getPeso() ); 
	}

	
	@Test
	void testPeso_Raddoppiato() {
		StringBuilder nomeDaInvertire = new StringBuilder("nome");
		Attrezzo attrDaTestare = new Attrezzo(nomeDaInvertire.toString(), PESO_OGGETTO);
		raggiungiSoglia();
		stanza.addAttrezzo(attrDaTestare);
		assertEquals(PESO_OGGETTO*2, stanza.getAttrezzo(nomeDaInvertire.reverse().toString()).getPeso()); 
	}
	
	@Test
	void testNome_NonInvertito() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(NOME_ATTR, stanza.getAttrezzo(NOME_ATTR).getNome()); 
	}
	
	@Test
	void testNome_Invertito() {
		StringBuilder nomeDaInvertire = new StringBuilder(NOME_ATTR);
		Attrezzo attrDaTestare = new Attrezzo(nomeDaInvertire.toString(), PESO_OGGETTO);
		raggiungiSoglia();
		stanza.addAttrezzo(attrDaTestare);
		assertEquals(NOME_ATTR_INV, stanza.getAttrezzo(nomeDaInvertire.reverse().toString()).getNome()); 
	}
	
	
	
	void raggiungiSoglia() {
		for(int i = 0; i < SOGLIA_MAGICA-1; i++)
			assertTrue(stanza.addAttrezzo(attrezzo));	
	}
	
}
