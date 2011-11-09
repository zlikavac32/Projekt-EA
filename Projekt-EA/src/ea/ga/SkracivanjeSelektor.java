/**
 * 
 */
package ea.ga;

import java.util.Arrays;

import ea.Simulator;

/**
 * @author Zlikavac32
 *
 */
public class SkracivanjeSelektor extends Selektor {

	int skracivanje;
	
	public SkracivanjeSelektor(int skracivanje) { this.skracivanje = skracivanje; }
	
	@Override
	public void postaviPopulaciju(Jedinka[] populacija) {
		Jedinka[] nova = Arrays.copyOf(populacija, populacija.length);
		Arrays.sort(nova);
		super.postaviPopulaciju(nova);
	}
	
	/* (non-Javadoc)
	 * @see ea.ga.Selektor#vratiSljedecuJedinku()
	 */
	@Override
	public Jedinka vratiSljedecuJedinku() {
		return populacija[Simulator.vratiRandomGenerator().vratiInt(skracivanje)];
	}

}
