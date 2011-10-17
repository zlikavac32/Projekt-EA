/**
 * 
 */
package ea.ga;

/**
 * Predstavlja genom.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 * @param <T> Tip genoma koji se koristi
 *
 */
public interface Genom<T> { 
	
	/**
	 * Kopira trenutni genom i vraca kopiju.
	 * 
	 * @return Genom kopiju
	 */
	public Genom<?> kopiraj();
	
	/**
	 * Inicijalizira genom na neku vrijednost.
	 * 
	 * @param krajolik Krajolik koji sluzi za inicijalizaciju
	 */
	public void inicijaliziraj(Krajolik krajolik);
	
	/**
	 * Vraca trenutnu vrijednost genoma.
	 * 
	 * @return Vrijednost genoma
	 */
	public T vratiVrijednost();
}
