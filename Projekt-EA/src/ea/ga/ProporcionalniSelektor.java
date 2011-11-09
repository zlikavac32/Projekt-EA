/**
 * 
 */
package ea.ga;

import ea.Simulator;
import ea.util.KoloSrece;

/**
 * @author Zlikavac32
 *
 */
public class ProporcionalniSelektor extends Selektor {


	protected KoloSrece koloSrece;
	
	@Override
	public void postaviPopulaciju(Jedinka[] populacija) {
		super.postaviPopulaciju(populacija);
		koloSrece = new KoloSrece(populacija, Simulator.vratiRandomGenerator());
	}
	
	/**
	 * @see ea.ga.Selektor#vratiSljedecuJedinku()
	 */
	@Override
	public Jedinka vratiSljedecuJedinku() {
		return (Jedinka) koloSrece.okreni();
	}

}
