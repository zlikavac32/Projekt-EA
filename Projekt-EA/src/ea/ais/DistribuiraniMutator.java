package ea.ais;

import ea.util.RandomGenerator;

public class DistribuiraniMutator implements Mutator<byte[]> {

	protected Populacija<?, ?, ?> populacija;
	
	protected double najmanji = 0;

	protected double ro;

	protected RandomGenerator generator;
	
	public DistribuiraniMutator(double ro, RandomGenerator generator) {
		this.ro = ro;
		this.generator = generator;
	}
	
	public void postaviPopulaciju(Populacija<?, ?, ?> populacija) {
		this.populacija = populacija;
	}
	
	@Override
	public byte[] mutiraj(AntiTijelo<byte[], ?, ?> antiTijelo) {
		byte[] podatci = antiTijelo.vratiReprezentaciju();
		
		double p = Math.exp(- ro * (antiTijelo.racunajFaktorDobrote() - najmanji));
				
		for (int i = 0; i < podatci.length; i++) {
			if (generator.vratiDouble() < p) {
				podatci[i] = (byte) (1 - podatci[i]);
			}
		}
		
		return podatci;
	}

	@Override
	public void inicijaliziraj() {
		najmanji = populacija.vratiLokalnoNajgore().racunajFaktorDobrote();
	}

}
