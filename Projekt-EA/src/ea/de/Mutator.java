package ea.de;

import ea.util.RandomGenerator;

public abstract class Mutator<T> {

	protected T donor;
	
	protected double vjerojatnostMutacije;
	
	protected RandomGenerator generator;
	
	public Mutator(double vjerojatnostMutacije, RandomGenerator generator) {
		this.vjerojatnostMutacije = vjerojatnostMutacije;
		this.generator = generator;
	}
	
	public void postaviDonora(T donor) {
		this.donor = donor;
	}
	
	public abstract void mutiraj(T odrediste);
	
}
