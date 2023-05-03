package it.uniroma3.diadia.attrezzi;

public class Attrezzo implements Comparable<Attrezzo> {

	private String nome;
	private int peso;

	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeso() {
		return this.peso;
	}

	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		Attrezzo that = (Attrezzo) obj;
		return this.getNome().equals(that.getNome()) && this.getPeso() == that.getPeso();
	}

	@Override
	public int compareTo(Attrezzo that) {
		int res = this.getNome().compareTo(that.getNome());
		if (res != 0) return res;
		return this.getPeso() - that.getPeso();
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getPeso();
	}

}