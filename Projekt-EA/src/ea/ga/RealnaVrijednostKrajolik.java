/**
 * 
 */
package ea.ga;

import ea.funkcija.Funkcija;
import ea.funkcija.Izracunljiv;
import ea.funkcija.IzvanDomeneIznimka;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class RealnaVrijednostKrajolik implements Krajolik {

	private Izracunljiv funkcija;
	
	private boolean invertiran;

	/**
	 * @param funkcija
	 */
	public RealnaVrijednostKrajolik(Izracunljiv funkcija) { this(funkcija, false); }
	
	/**
	 * Stvara novi krajolik predstavljen funkcijom
	 * 
	 * @param funkcija Funckija koja predstavlja krajolik
	 * @param invertiran 
	 */
	public RealnaVrijednostKrajolik(Izracunljiv funkcija, boolean invertiran) { 
		this.funkcija = funkcija; 
		this.invertiran = invertiran;
	}
	
	/**
	 * Vraca faktor dobrote kao rezultat funckije koja je "nahranjena" realnom vrijednosti genoma.
	 * 
	 * @see ea.ga.Krajolik#izracunajFaktorDobrote(ea.ga.Genom)
	 */
	@Override
	public double izracunajFaktorDobrote(Genom<?> genom) {
		double vrijednost = 0.;
		try { vrijednost = racunaj(genom); } 
		catch (IzvanDomeneIznimka e) { }
		
		return invertiran ? -vrijednost : vrijednost;
	}

	/**
	 * @see ea.ga.Krajolik#jeValjanGenom(ea.ga.Genom)
	 */
	@Override
	public boolean jeValjanGenom(Genom<?> genom) {
		try { racunaj(genom); } 
		catch (IzvanDomeneIznimka e) { return false; }
		return true;
	}
	
	private double racunaj(Genom<?> genom)
		throws IzvanDomeneIznimka {
		if (!(genom instanceof RealnaVrijednostGenom)) { throw new IllegalArgumentException("Genom mora biti tipa RealnaVrijednostGenom"); }
		
		if (funkcija instanceof Funkcija) { ((Funkcija) funkcija).postaviVrijednost("x", ((RealnaVrijednostGenom) genom).vratiVrijednost()); }
		
		return funkcija.racunaj();
	}
}
