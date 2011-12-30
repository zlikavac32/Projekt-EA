package ea.de;

import ea.util.RandomGenerator;

public class EksponencijalniMutator extends Mutator<double[][]> {

	protected double tau;
	
	protected int maxX;
	
	public EksponencijalniMutator(double vjerojatnostMutacije, RandomGenerator generator, double tau) {
		super(vjerojatnostMutacije, generator);
		this.tau = tau;
		
		//Pretpostavka je da ako nam se generira P < 1-e6 da je to 0. Iz toga mozemo dobiti
		//maksimalan x koji nam se smije dogoditi
		maxX = (int) Math.ceil(- Math.log(1e-6) / tau);
	}

	@Override
	public void mutiraj(double[][] odrediste, double[][] donor) {
		
		
		//Racuna se 1 - E(x) < P gdje je E(x) = 1 - exp(-x * tau)
		for (int i = 0; i < odrediste.length; i++) {
			for (
				int j = 0; 
				j < odrediste[i].length && 
					Math.exp(- generator.vratiInt(maxX) * tau) < vjerojatnostMutacije; 
				j++
			) {
				odrediste[i][j] -= donor[i][j];
			}
		}
		
	}

}
