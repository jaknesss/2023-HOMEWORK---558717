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
	private StringBuilder nomeDaInvertire;
	private String nomeInvertito;
	private Attrezzo attrDaTestare;
	
	
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	
	@BeforeEach
	void setUp(){
		stanza = new StanzaMagica(NOME_STANZA);
		attrezzo = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
		nomeDaInvertire = new StringBuilder(NOME_ATTR);
		nomeInvertito = nomeDaInvertire.reverse().toString();
		attrDaTestare = new Attrezzo(NOME_ATTR, PESO_OGGETTO);
	}

	@Test
	void testPeso_NonRaddoppiato() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(PESO_OGGETTO, stanza.getAttrezzo(NOME_ATTR).getPeso() ); 
	}

	@Test
	void testNome_NonInvertito() {
		assertTrue(stanza.addAttrezzo(attrezzo));
		assertEquals(NOME_ATTR, stanza.getAttrezzo(NOME_ATTR).getNome()); 
	}
	
	@Test
	void testPeso_Raddoppiato() {
		raggiungiSoglia();
		stanza.addAttrezzo(attrDaTestare);
		assertEquals(NOME_ATTR_INV, stanza.getAttrezzo(nomeInvertito).getNome()); 
	}
	
	
	
	void raggiungiSoglia() {
		for(int i = 0; i < SOGLIA_MAGICA-1; i++)
			assertTrue(stanza.addAttrezzo(attrezzo));	
	}
	
}
