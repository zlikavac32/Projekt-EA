/**
 * 
 */
package ea.ga;

import java.util.Arrays;

/**
 * @author Zlikavac32
 *
 */
public abstract class PreklapajucaPopulacija extends Populacija {
	
	protected int brojDjece;

	public PreklapajucaPopulacija(int velicina, int brojDjece) { 
		super(velicina); 
		this.brojDjece = brojDjece;
	}

	@Override
	public void evoluiraj() {

		Jedinka[] privremeno = Arrays.copyOf(jedinke, jedinke.length + brojDjece);
		selektor.postaviPopulaciju(jedinke);
		
		for (int i = jedinke.length; i < privremeno.length; i++) {
			Jedinka jedinka = selektor.vratiSljedecuJedinku().kopiraj();
			if (koristiRekombinaciju) { jedinka.rekombiniraj(rekombinator, selektor.vratiSljedecuJedinku().kopiraj()); }
			if (koristiMutaciju) { jedinka.mutiraj(mutator, vjerojatnostMutacije); }
			privremeno[i] = jedinka;
		}
		
		Arrays.sort(privremeno);
		jedinke = Arrays.copyOf(privremeno, jedinke.length);
		
	}

}
