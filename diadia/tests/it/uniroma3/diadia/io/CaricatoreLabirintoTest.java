package it.uniroma3.diadia.io;


import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;
import it.uniroma3.personaggi.Mago;

class CaricatoreLabirintoTest {

	private CaricatoreLabirinto cl;	
	
	private final String monolocale =
			"Stanze:\n"+
			"Inizio:N11\n"+
			"Vincente:N11\n"+
			"Magica:\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi:\n"+
			"Uscite:\n";
	
	
	private final String bilocale =
			"Stanze:\n"+
			"Inizio:N11\n"+
			"Vincente:N10\n"+
			"Magica:\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi:\n"+
			"Uscite:N11 nord N10\n";
	
	private final String bilocaleConAttrezzi =
			"Stanze:\n"+
			"Inizio:N11\n"+
			"Vincente:N10\n"+
			"Magica:\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi: osso 1 N11, ascia 123 N10\n"+
			"Uscite:N11 nord N10\n";
	
	private final String bilocaleConMago =
			"Stanze:\n"+
			"Inizio:N11\n"+
			"Vincente:N10\n"+
			"Magica:\n"+
			"Bloccata:\n"+
			"Buia:\n"+
			"Mago: N11 Merlino ascia\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi: osso 1 N11, ascia 123 N10\n"+
			"Uscite:N11 nord N10\n";
	
	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(monolocale));
		cl.carica();
		Labirinto lab = cl.getLabirinto();
		assertEquals("N11", lab.getStanzaIniziale().getNome());
		assertEquals("N11", lab.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		Labirinto lab = cl.getLabirinto();
		assertEquals("N11", lab.getStanzaIniziale().getNome());
		assertEquals("N10", lab.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocaleConAttrezzi() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocaleConAttrezzi));
		cl.carica();
		Labirinto lab = cl.getLabirinto();
		assertEquals(new Attrezzo("osso", 1), lab.getStanzaIniziale().getAttrezzo("osso"));
		assertEquals(new Attrezzo("ascia", 123), lab.getStanzaVincente().getAttrezzo("ascia"));
	}
	
	@Test
	public void testBilocaleConMago() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocaleConMago));
		cl.carica();
		Labirinto lab = cl.getLabirinto();
		AbstractPersonaggio mago = new Mago("Merlino", Mago.DESCRIZIONE, new Attrezzo("ascia", 4));
		assertEquals(mago, lab.getStanzaIniziale().getPersonaggio());
	}
}
