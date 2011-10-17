/**
 * 
 */
package ea.funkcija;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Funkcija extends Izracunljiv {

	private Map<String, Varijabla> varijable = new HashMap<String, Varijabla>();
	
	/**
	 * @param naziv
	 * @param varijabla
	 */
	public void dodajVarijablu(String naziv, Varijabla varijabla) { varijable.put(naziv, varijabla); }
	
	/**
	 * @param naziv
	 * @param vrijednost
	 */
	public void postaviVrijednost(String naziv, double vrijednost) {
		Varijabla varijabla = varijable.get(naziv);
		if (varijabla != null) { varijabla.postaviVrijednost(vrijednost); }
	}
	
	/**
	 * @see ea.funkcija.Izracunljiv#racunaj()
	 */
	@Override
	public double racunaj() throws IzvanDomeneIznimka {
		if (operandi.size() != 1) { }
		return operandi.get(0).racunaj();
	}
	
}
