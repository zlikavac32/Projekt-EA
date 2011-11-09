package ea.ga;

import java.util.Arrays;

import ea.Simulator;
import ea.util.RandomGenerator;

public class GenotipJedinka implements Jedinka {
	

	/**
	 * Oznaka mutacije okretom bita.
	 */
	public static final int OKRET_BITA_MUTACIJA = 1;
	
	/**
	 * Oznaka rekombinacije sa jednim cvorom.
	 */
	public static final int JEDAN_CVOR_REKOMBINACIJA = 1;
	
	/**
	 * Oznaka rekombinacije sa dva cvora.
	 */
	public static final int DVA_CVORA_REKOMBINACIJA = 2;
	
	protected byte[] bitovi;
	
	protected int brojBitova;
	
	protected double faktorDobrote;
	
	public GenotipJedinka(int brojBitova) { 
		if (brojBitova < 1 || brojBitova > 63) { 
			throw new IllegalArgumentException("Broj bitova mora biti u rasponu [0,63]");
		}
		this.brojBitova = brojBitova; 
	}
	

	@SuppressWarnings("unchecked")
	protected double racunajFaktorDobrote(double vrijednost) { 
		return ((Krajolik<Double>) Simulator.vratiKrajolik()).racunajFaktorDobrote(vrijednost);
	}
	
	@Override
	public double racunajFaktorDobrote() { return faktorDobrote; }

	@Override
	public GenotipJedinka kopiraj() {
		GenotipJedinka novaJedinka = new GenotipJedinka(brojBitova);
		if (bitovi != null) {
			novaJedinka.bitovi = Arrays.copyOf(bitovi, bitovi.length);
		}
		novaJedinka.faktorDobrote = faktorDobrote;
		return novaJedinka;
	}

	@Override
	public void mutiraj(int mutator, double vjerojatnostMutacije) { 
		switch (mutator) {
			case OKRET_BITA_MUTACIJA:
				okretBitaMutacija(vjerojatnostMutacije);
				break;
			default:
				throw new IllegalArgumentException("Mutator " + mutator + " nije valjan za binarnu jedinku");
		}
		faktorDobrote = racunajFaktorDobrote(dekodiraj(bitovi));
	}

	@Override
	public void mutiraj(int mutator, Object delta, double vjerojatnostMutacije) {
		mutiraj(mutator, vjerojatnostMutacije);
	}
	
	private void okretBitaMutacija(double vjerojatnostMutacije) {
		if (brojBitova < 0 || brojBitova > 63) { 
			throw new IllegalArgumentException("Broj bitova mora biti u rasponu [0,63]");
		}
		RandomGenerator randomGenerator = Simulator.vratiRandomGenerator();
		for (int i = 0; i < bitovi.length; i++) {
			if (randomGenerator.vratiDouble() > vjerojatnostMutacije) { continue; }
			bitovi[i] = (byte) (1 - bitovi[i]);
		}
	}

	@Override
	public String toString() {
		return "Binarna jedinka sa vrijednoscu [" + vratiBitove(bitovi) + "; stvarna vrijednost " + dekodiraj(bitovi) + "]";
	}

	protected String vratiBitove(byte[] bitovi) {
		StringBuilder graditelj = new StringBuilder(bitovi.length);
		for (int i = bitovi.length - 1; i >= 0; i--) { graditelj.append(bitovi[i]); }
		return graditelj.toString();
	}

	public void inicijaliziraj() { 
		RandomGenerator randomGenerator = Simulator.vratiRandomGenerator();
		bitovi = new byte[brojBitova];
		for (int i = 0; i < brojBitova; i++) {
			bitovi[i] = (byte) (randomGenerator.vratiDouble() < .5 ? 1 : 0);
		}
		faktorDobrote = racunajFaktorDobrote(dekodiraj(bitovi));
 	}
	
	protected double dekodiraj(byte[] bitovi) {
		double vrijednost = 0.;
		long potencija = 1;
		for (int i = 0; i < bitovi.length; i++) {
			if (bitovi[i] == 1) { vrijednost += potencija; }
			potencija <<= 1;
		}
		FunkcijaKrajolik krajolik = (FunkcijaKrajolik) Simulator.vratiKrajolik();
		return vrijednost / ((1L << brojBitova) - 1) * (krajolik.vratiGornjuGranicu() - krajolik.vratiDonjuGranicu()) 
			+ krajolik.vratiDonjuGranicu();
	}

	@Override
	public int compareTo(Jedinka strani) {
		double mojFaktorDobrote = racunajFaktorDobrote();
		double straniFaktorDobrote = strani.racunajFaktorDobrote();
		//Smatramo da su dva broja jednaka ako im je razlika manja od 1e-9
		return (Math.abs(mojFaktorDobrote - straniFaktorDobrote) < 1e-9) ? 0 : ((mojFaktorDobrote > straniFaktorDobrote) ? -1 : 1);
	}

	@Override
	public void rekombiniraj(int rekombinator, Jedinka partner) { 
		switch (rekombinator) {
			case JEDAN_CVOR_REKOMBINACIJA :
				jedanCvorRekombinacija(((GenotipJedinka) partner).bitovi);
				break;
			case DVA_CVORA_REKOMBINACIJA :
				dvaCvoraRekombinacija(((GenotipJedinka) partner).bitovi);
				break;
			default:
				throw new IllegalArgumentException("Rekombinator " + rekombinator + " nije valjan za binarnu jedinku");
		}
		faktorDobrote = racunajFaktorDobrote(dekodiraj(bitovi));
	}

	private void dvaCvoraRekombinacija(byte[] partner) {
		if (partner.length != brojBitova) { 
			throw new IllegalArgumentException("Broj bitova oba partnera mora biti isti");
		}
		int prva = Simulator.vratiRandomGenerator().vratiInt(brojBitova);
		int druga = Simulator.vratiRandomGenerator().vratiInt(brojBitova);
		byte tempBitovi[] = Arrays.copyOf(partner, 0);
		if (prva > druga) {
			int temp = prva;
			prva = druga;
			druga = temp;
		}
		for (int i = 0; i < prva; i++) { tempBitovi[i] = bitovi[i]; }
		for (int i = prva; i < druga; i++) { bitovi[i] = tempBitovi[i]; }
		for (int i = druga; i < bitovi.length; i++) { tempBitovi[i] = bitovi[i]; }
		if (racunajFaktorDobrote(dekodiraj(tempBitovi)) > racunajFaktorDobrote(dekodiraj(bitovi))) {
			bitovi = tempBitovi;
		}
	}

	private void jedanCvorRekombinacija(byte[] partner) {
		if (partner.length != brojBitova) { 
			throw new IllegalArgumentException("Broj bitova oba partnera mora biti isti");
		}
		int lokacija = Simulator.vratiRandomGenerator().vratiInt(brojBitova);
		byte tempBitovi[] = Arrays.copyOf(partner, 0);
		for (int i = 0; i < lokacija; i++) { tempBitovi[i] = bitovi[i]; }
		for (int i = lokacija; i < partner.length; i++) { bitovi[i] = tempBitovi[i]; }
		if (racunajFaktorDobrote(dekodiraj(tempBitovi)) > racunajFaktorDobrote(dekodiraj(bitovi))) {
			bitovi = tempBitovi;
		}
	}

	@Override
	public Double vratiVrijednost() { return dekodiraj(bitovi); }
	
	@Override
	public double racunajVrijednost() { return racunajFaktorDobrote(); }
	
}
