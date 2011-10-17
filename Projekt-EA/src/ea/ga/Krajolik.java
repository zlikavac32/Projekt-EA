/**
 * 
 */
package ea.ga;

/**
 * Predstavlja krajolik koji se pretrazuje.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public interface Krajolik {
	
	/**
	 * Racuna faktor dobrote za dani genom.
	 * 
	 * @param genom Genom za koji se racuna faktor dobrote
	 * @return Faktor dobrote
	 */
	public double izracunajFaktorDobrote(Genom<?> genom);
	
	/**
	 * Provjerava da li je genom valjan. Primjer genoma koji nije valjan jest genom koji predstavlja
	 * kombinaciju kutija koje je potrebno smjestiti unutar nekog prostora, no one su vece od tog 
	 * prostora. Sluzi za definiranje ogranicenja.
	 * 
	 * @param genom Genom za koji se provjerava da li je valjan
	 * @return True ako valjan, false ako nije
	 */
	public boolean jeValjanGenom(Genom<?> genom);
}
