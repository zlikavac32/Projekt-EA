/**
 * 
 */
package ea.ga;

import java.util.Arrays;

/**
 * @author Zlikavac32
 *
 */
public abstract class NepreklapajucaPopulacija extends PreklapajucaPopulacija {
	

	public NepreklapajucaPopulacija(int velicina, int brojDjece) { 
		super(velicina, brojDjece); 
		if (brojDjece < velicina) {
			throw new IllegalArgumentException("Nepreklapajuca populacija mora imat broj djece veci ili jedank velicini populacije");
		}
	}
	
	@Override
	public void evoluiraj() {

		Jedinka[] privremeno = new Jedinka[brojDjece];
		selektor.postaviPopulaciju(jedinke);
		
		for (int i = 0; i < privremeno.length; i++) {
			Jedinka jedinka = selektor.vratiSljedecuJedinku().kopiraj();
			if (koristiRekombinaciju) { jedinka.rekombiniraj(rekombinator, selektor.vratiSljedecuJedinku().kopiraj()); }
			if (koristiMutaciju) { jedinka.mutiraj(mutator, vjerojatnostMutacije); }
			privremeno[i] = jedinka;
		}
		
		Arrays.sort(privremeno);
		jedinke = Arrays.copyOf(privremeno, jedinke.length);
			
	}

}
