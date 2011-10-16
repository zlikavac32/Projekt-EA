/**
 * 
 */
package ea.funkcija;

import java.util.ArrayList;
import java.util.List;

/**
 * Sluzi za stvaranje elemenata koji ce formirati funckiju.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public abstract class Izracunljiv {
	
	protected List<Izracunljiv> operandi = new ArrayList<Izracunljiv>();

	/**
	 * Racuna vrijednost. Ovisi o implementaciji.
	 * 
	 * @return Vrijednost koju je izracunata
	 * @throws IzvanDomeneIznimka
	 */
	public abstract double racunaj()
		throws IzvanDomeneIznimka;
	
	/**
	 * Dodaje operand implementaciji.
	 * 
	 * @param operand Operand koji se dodaje
	 * @return Instanca objekta radi nadovezivanja
	 * @throws IllegalArgumentException Ako je operand null
	 */
	public Izracunljiv dodaj(Izracunljiv operand) {
		if (operand == null) { throw new IllegalArgumentException("Operand ne moze biti null"); }
		operandi.add(operand);
		return this;
	}
}
