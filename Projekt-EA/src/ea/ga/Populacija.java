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
public interface Populacija {

	/**
	 * Postavlja velicinu populacije.
	 * 
	 * @param velicina Velicina populacije
	 */
	public void postaviVelicinu(int velicina);
	
	/**
	 * Vraca velicinu populacije.
	 * 
	 * @return Velicina populacije
	 */
	public int vratiVelicinu();
	
	/**
	 * Stvara pocetnu generaciju na temelju krajolika.
	 * 
	 * @param krajolik Krajolik koji se pretrazuje
	 */
	public void stvori(Krajolik krajolik);
	
	/**
	 * Vraca najbolju jedinku unutar populacije.
	 * 
	 * @return Najbolja jedinka
	 */
	public Jedinka vratiNajboljuJedinku();
	
	/**
	 * Evoluira generaciju u sljedecu.
	 * 
	 * @param krajolik Krajolik koji se koristi
	 */
	public void evoluiraj(Krajolik krajolik);
	
}
