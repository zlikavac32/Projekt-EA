package ea.de;

import ea.util.Krajolik;
import ea.util.RandomGenerator;

public abstract class Vektor<T, E extends Krajolik<T>> implements Comparable<Vektor<T, E>> {

	protected T krajolik;
	
	public Vektor(T krajolik) {
		this.krajolik = krajolik;
	}
	
	public abstract double racunajFaktorDobrote();
	
	public abstract E vratiVrijednost();
	
	public abstract void inicijaliziraj(RandomGenerator generator);
	
	public abstract void postaviVrijednost(E vrijednost);
	
	@Override
	public int compareTo(Vektor<T, E> drugi) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public abstract Vektor<T, E> kopiraj();

}
