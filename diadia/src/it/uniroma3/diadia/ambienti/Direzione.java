package it.uniroma3.diadia.ambienti;

public enum Direzione {
	   NORD {
		@Override public Direzione opposta() { return SUD; }
	}, EST {
		@Override public Direzione opposta() { return OVEST; }
	}, SUD {
		@Override public Direzione opposta() { return NORD; }
	}, OVEST {
		@Override public Direzione opposta() { return EST; }
	};
	
	public abstract Direzione opposta();
}
