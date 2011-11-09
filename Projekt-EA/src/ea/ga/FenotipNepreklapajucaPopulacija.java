/**
 * 
 */
package ea.ga;


/**
 * @author Zlikavac32
 *
 */
public class FenotipNepreklapajucaPopulacija extends NepreklapajucaPopulacija {

	/**
	 * @param velicina
	 * @param brojDjece
	 */
	public FenotipNepreklapajucaPopulacija(int velicina, int brojDjece) {
		super(velicina, brojDjece);
	}

	/**
	 * @see ea.ga.Populacija#inicijaliziraj()
	 */
	@Override
	public void inicijaliziraj() {
		for (int i = 0; i < jedinke.length; i++) {
			jedinke[i] = new FenotipJedinka();
			jedinke[i].inicijaliziraj();
		}
	}

}
