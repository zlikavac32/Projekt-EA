/**
 * 
 */
package ea.ga;

import de.congrace.exp4j.Calculable;

/**
 * @author Zlikavac32
 *
 */
public class FunkcijaKrajolik extends RealniKrajolik {

	protected boolean invertiran;

	private Calculable funkcija;
	
	@Override
	public double racunajFaktorDobrote(Double vrijednostJedinke) {
		funkcija.setVariable("x", vrijednostJedinke);
		double vrijednost = funkcija.calculate();
		return invertiran ? -vrijednost : vrijednost;
	}

	@Override
	public boolean jeValjanGenom(Double vrijednostGenoma) {
		return vrijednostGenoma < gornjaGranica && vrijednostGenoma > donjaGranica;
	}
		
	/**
	 * Odreduje da li je funkcija invertirana ili ne, tj. odreduje da li trazimo minimum ili maksimum.
	 * 
	 * @param invertiran True ako je funkcija invertirana, false ako ne
	 */
	public void postaviInvertiran(boolean invertiran) { this.invertiran = invertiran; }

	@Override
	public double racunajVrijednost(double vrijednost) { 
		funkcija.setVariable("x", vrijednost);
		return funkcija.calculate();
	}

	public void postaviFunkciju(Calculable f) {
		funkcija = f;
	}


}
