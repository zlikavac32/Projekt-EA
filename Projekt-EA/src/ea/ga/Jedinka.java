/**
 * 
 */
package ea.ga;

/**
 * Predstavlja jednu jedinku, tj. jedno potencijalno rjesenje problema.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public interface Jedinka {
	
	/**
	 * Stvara novu kopiju jedinke.
	 * 
	 * @return Kopija jedinke
	 */
	public Jedinka kopiraj();
	
	/**
	 * Evoluira trenutnu jedinku.
	 * 
	 * @param krajolik Krajolik koji se koristi za evoluiranje
	 */
	public void evoluiraj(Krajolik krajolik);
	
	/**
	 * Racuna faktor dobrote trenutne jedinke.
	 * 
	 * @param krajolik Krajolik koji se pretrazuje
	 * @return Vrijednost faktora dobrote koji je izracunat
	 */
	public double vratiFaktorDobrote(Krajolik krajolik);
	
	/**
	 * Inicijalizira jedinku koja ce se koristiti dalje u pretrazivanju.
	 * 
	 * @param krajolik Krajolik koji se pretrazuje
	 */
	public void inicijaliziraj(Krajolik krajolik);
	
	/**
	 * Vraca trenutni genom jedinke.
	 * 
	 * @return Genom jedinke
	 */
	public Genom<?> vratiGenom();
	
	/**
	 * Vraca redni broj rodenja jedinke.
	 * 
	 * @return Redni broj kada je rodena
	 */
	public int vratiRedniBrojRodenja();
	
}
