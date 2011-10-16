/**
 * 
 */
package ea.funkcija;

/**
 * Sluzi za dinamicku promijenu vrijednosti.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Varijabla extends Izracunljiv {
	
	private double vrijednost = 0;

	/**
	 * @see ea.funkcija.Izracunljiv#racunaj()
	 */
	@Override
	public double racunaj() throws IzvanDomeneIznimka { return vrijednost; }
	
	/**
	 * Postavlja vrijednost varijable.
	 * 
	 * @param vrijednost Vrijednost varijable
	 */
	public void postaviVrijednost(double vrijednost) { this.vrijednost = vrijednost; }

}
