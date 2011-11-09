/**
 * 
 */
package ea.ga;

import ea.Simulator;

/**
 * @author Zlikavac32
 *
 */
public class UniformniSelektor extends Selektor {


	@Override
	public Jedinka vratiSljedecuJedinku() {
		return populacija[Simulator.vratiRandomGenerator().vratiInt(populacija.length)];
	}

}
