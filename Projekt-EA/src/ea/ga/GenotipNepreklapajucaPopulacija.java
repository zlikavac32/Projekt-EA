/**
 * 
 */
package ea.ga;


/**
 * @author Zlikavac32
 *
 */
public class GenotipNepreklapajucaPopulacija extends NepreklapajucaPopulacija {

	int brojBitova;
	
	/**
	 * @param velicina
	 * @param brojDjece
	 */
	public GenotipNepreklapajucaPopulacija(int velicina, int brojDjece, int brojBitova) {
		super(velicina, brojDjece);
		this.brojBitova = brojBitova;
	}

	/**
	 * @see ea.ga.Populacija#inicijaliziraj()
	 */
	@Override
	public void inicijaliziraj() {
		for (int i = 0; i < jedinke.length; i++) {
			jedinke[i] = new GenotipJedinka(brojBitova);
			jedinke[i].inicijaliziraj();
		}
	}

}
