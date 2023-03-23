package it.uniroma3.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {

	Giocatore giocatore = new Giocatore();
	Borsa borsa = giocatore.getBorsa();	
	
	@Test
	public void testSetCfu() {
		giocatore.setCfu(giocatore.getCfu()-2);
		assertEquals(18, giocatore.getCfu());
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("test", 2);
		giocatore.addAttrezzo(attrezzo1);
		assertEquals(borsa.getAttrezzo("test"), attrezzo1);
	}
	
	@Test
	public void testRemoveAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("test", 2);
		giocatore.addAttrezzo(attrezzo1);
		giocatore.removeAttrezzo(attrezzo1);
		assertFalse(borsa.hasAttrezzo(attrezzo1.getNome()));
	}	
}
