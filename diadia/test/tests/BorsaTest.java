package it.uniroma3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	Borsa borsa = new Borsa();
	Attrezzo attrezzoTest = new Attrezzo("osso", 3);
	Attrezzo attrezzoTest2 = new Attrezzo("lanterna", 2);

	@Test
	public void testAddAttrezzoPesoMassimo() {
		for(int i = 0; i < 3; i++)  
			borsa.addAttrezzo(attrezzoTest);
		assertFalse(borsa.addAttrezzo(attrezzoTest));
	}
	
	@Test
	public void testgetAttrezzoEsistente() {
		borsa.addAttrezzo(attrezzoTest);
		assertTrue(borsa.hasAttrezzo(attrezzoTest.getNome()));
	}
	
	@Test
	public void testGetAttrezzoNonEsistente() {
		borsa.addAttrezzo(attrezzoTest);
		assertFalse(borsa.hasAttrezzo("check"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		borsa.addAttrezzo(attrezzoTest);
		borsa.addAttrezzo(attrezzoTest2);
		borsa.removeAttrezzo(attrezzoTest);
		assertFalse(borsa.hasAttrezzo(attrezzoTest.getNome()));
	}	
	
}





















