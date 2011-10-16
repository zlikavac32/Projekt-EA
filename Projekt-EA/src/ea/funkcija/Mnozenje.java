/**
 * 
 */
package ea.funkcija;

/**
 * Mnozi operande koji su dodani.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Mnozenje extends Izracunljiv {

	/**
	 * Vraca umnozak operanada.
	 * 
	 * @see ea.funkcija.Izracunljiv#racunaj()
	 */
	@Override
	public double racunaj() throws IzvanDomeneIznimka {
		if (operandi.size() != 2) { throw new IndexOutOfBoundsException("Potrebna su dva operanda"); }
		return operandi.get(0).racunaj() * operandi.get(1).racunaj();
	}

}
