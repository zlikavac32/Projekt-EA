/**
 * 
 */
package ea.ga;


/**
 * @author Zlikavac32
 *
 */
public class FenotipNepreklapajucaPopulacija extends NepreklapajucaPopulacija<RealniKrajolik> {

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
		int limit = jedinke.size();
		for (int i = 0; i < limit; i++) {
			FenotipJedinka jedinka = new FenotipJedinka(this);
			jedinka.inicijaliziraj();
			jedinke.set(i, jedinka);
		}
	}

}