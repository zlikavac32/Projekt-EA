package ea.ais;

import ea.util.Krajolik;
import ea.util.RandomGenerator;

public abstract class AntiTijelo<R, E, T extends Krajolik<E>> implements Comparable<AntiTijelo<R, E, T>> {

	protected T krajolik;
	
	public AntiTijelo(T krajolik) {
		this.krajolik = krajolik;
	}
	
	public abstract double racunajFaktorDobrote();
	
	public abstract E vratiVrijednost();
	
	public abstract R vratiReprezentaciju();
	
	public abstract void inicijaliziraj(RandomGenerator generator);
	
	public abstract void mutiraj(Mutator<R> mutator);
	
	public abstract AntiTijelo<R, E, T> kopiraj();
	
	@Override
	public int compareTo(AntiTijelo<R, E, T> strani) {
		if (strani == null) { return -1; }
		double mojFaktorDobrote = racunajFaktorDobrote();
		double straniFaktorDobrote = strani.racunajFaktorDobrote();
		if (mojFaktorDobrote > straniFaktorDobrote) { return -1; }
		else if (mojFaktorDobrote < straniFaktorDobrote) { return 1; }
		return 0;
	}

}
