/**
 * 
 */
package ea.ga;

import ea.RandomGenerator;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class RealnaVrijednostGenom implements Genom<Double> {
	
	private double vrijednost;
	
	/**
	 * @author Marijan Šuflaj <msufflaj32@gmail.com>
	 *
	 */
	public static enum VrstaMutacije {
		
		/**
		 * 
		 */
		DELTA_MUTACIJA { 
			
			@Override
			public void mutiraj(RealnaVrijednostGenom genom, double delta) {
				if (RandomGenerator.vratiDouble() < 0.5f) { genom.vrijednost += delta; }
				else { genom.vrijednost -= delta; }
			}
		},
		
		/**
		 * 
		 */
		GAUS_MUTACIJA { 
			
			@Override
			public void mutiraj(RealnaVrijednostGenom genom, double delta) { genom.vrijednost += delta * RandomGenerator.vratiGauss(); }
		};
		
		abstract void mutiraj(RealnaVrijednostGenom genom, double delta);
		
	}

	/**
	 * Kopira genom
	 * 
	 * @see ea.ga.Genom#kopiraj()
	 */
	@Override
	public RealnaVrijednostGenom kopiraj() {
		RealnaVrijednostGenom novi = new RealnaVrijednostGenom();
		
		novi.vrijednost = vrijednost;
		
		return novi;
	}

	/**
	 * Inicijalizira genom na realnu vrijednost. Krajolik mora biti tipa RealnaVrijednostKrajolik
	 * 
	 * @see ea.ga.Genom#inicijaliziraj(ea.ga.Krajolik)
	 */
	@Override
	public void inicijaliziraj(Krajolik krajolik) {
		if (!(krajolik instanceof RealnaVrijednostKrajolik)) { throw new IllegalArgumentException("Krajolik mora biti tipa RealnaVrijednostKrajolik"); }
	}

	/**
	 * Vraca realnu vrijednost genoma.
	 * 
	 * @see ea.ga.Genom#vratiVrijednost()
	 */
	@Override
	public Double vratiVrijednost() { return vrijednost; }
	
	/**
	 * @param krajolik
	 * @param vrsta
	 * @param delta
	 */
	public void mutiraj(Krajolik krajolik, VrstaMutacije vrsta, double delta) { 
		double vrijednost = this.vrijednost;
		vrsta.mutiraj(this, delta);
		if (!krajolik.jeValjanGenom(this)) { this.vrijednost = vrijednost; }
	}

}
