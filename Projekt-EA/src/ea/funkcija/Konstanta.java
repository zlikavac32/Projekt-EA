/**
 * 
 */
package ea.funkcija;

/**
 * Predstavlja konstantu.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Konstanta extends Izracunljiv {
	
	private double konstanta;
	
	/**
	 * Dodaje vrijednost konstanti.
	 * 
	 * @param konstanta Vrijednost konstante
	 */
	public Konstanta(double konstanta) { this.konstanta = konstanta; }

	/**
	 * Vraca vrijednost konstante.
	 * 
	 * @see ea.funkcija.Izracunljiv#racunaj()
	 */
	@Override
	public double racunaj() 
		throws IzvanDomeneIznimka { return konstanta; }

}
