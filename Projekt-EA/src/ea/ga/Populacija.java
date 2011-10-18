/**
 * 
 */
package ea.ga;

/**
 * Predstavlja populaciju.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public abstract class Populacija {

	protected Jedinka[] generacija;
	
	protected int velicina;
	
	/**
	 * Postavlja velicinu populacije.
	 * 
	 * @param velicina Velicina populacije
	 */
	public void postaviVelicinu(int velicina) { this.velicina = velicina; }
	
	/**
	 * Vraca velicinu populacije.
	 * 
	 * @return Velicina populacije
	 */
	public int vratiVelicinu() { return velicina; }
	
	/**
	 * Stvara pocetnu generaciju na temelju krajolika.
	 * 
	 * @param krajolik Krajolik koji se pretrazuje
	 */
	public abstract void stvori(Krajolik krajolik);
	
	/**
	 * Vraca najbolju jedinku unutar populacije.
	 * @param krajolik 
	 * 
	 * @return Najbolja jedinka
	 */
	public Jedinka vratiNajboljuJedinku(Krajolik krajolik) { 
		if (generacija == null || generacija.length < 1) { return null; }
		Jedinka najbolji = generacija[0];
		double najboljiFaktorDobrote = najbolji.vratiFaktorDobrote(krajolik);
		
		for (int i = 1; i < generacija.length; i++) { 
			double trenFaktorDobrote = generacija[i].vratiFaktorDobrote(krajolik);
			if (najboljiFaktorDobrote < trenFaktorDobrote) {
				najboljiFaktorDobrote = trenFaktorDobrote;
				najbolji = generacija[i];
			}
		}
		
		return najbolji;
	}
	
	/**
	 * Evoluira generaciju u sljedecu.
	 * 
	 * @param krajolik Krajolik koji se koristi
	 */
	public abstract void evoluiraj(Krajolik krajolik);
}
