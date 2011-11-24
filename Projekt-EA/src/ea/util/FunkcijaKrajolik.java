/**
 * 
 */
package ea.util;

import de.congrace.exp4j.Calculable;

/**
 * @author Zlikavac32
 *
 */
public class FunkcijaKrajolik extends RealniKrajolik {

	protected boolean invertiran;

	private Calculable funkcija;
	
	private String[] varijable;
	
	@Override
	public double racunajFaktorDobrote(Double[] vrijednostJedinke) {
		for (int i = 0; i < vrijednostJedinke.length; i++) {
			funkcija.setVariable(varijable[i], vrijednostJedinke[i]);
		}
		double vrijednost = funkcija.calculate();
		return invertiran ? -vrijednost : vrijednost;
	}

	@Override
	public boolean jeValjanGenom(Double[] vrijednostGenoma) {
		for (int i = 0; i < vrijednostGenoma.length; i++) {
			if (vrijednostGenoma[i] > gornjaGranica[i] || vrijednostGenoma[i] < donjaGranica[i]) { return false; }
		}
		return true;
	}
		
	/**
	 * Odreduje da li je funkcija invertirana ili ne, tj. odreduje da li trazimo minimum ili maksimum.
	 * 
	 * @param invertiran True ako je funkcija invertirana, false ako ne
	 */
	public void postaviInvertiran(boolean invertiran) { this.invertiran = invertiran; }
	
	public void postaviVarijable(String[] varijable) { this.varijable = varijable; }

	@Override
	public double racunajVrijednost(double vrijednost) { 
		funkcija.setVariable("x", vrijednost);
		return funkcija.calculate();
	}

	public void postaviFunkciju(Calculable f) {
		funkcija = f;
	}


}
