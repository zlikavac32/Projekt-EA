/**
 * 
 */
package ea.ga;

import ea.Simulator;
import ea.util.RandomGenerator;

/**
 * @author Zlikavac32
 *
 */
public class DvoTurnirskiSelektor extends Selektor {

	/**
	 * @see ea.ga.Selektor#vratiSljedecuJedinku()
	 */
	@Override
	public Jedinka vratiSljedecuJedinku() {
		RandomGenerator randomGenerator = Simulator.vratiRandomGenerator();
		Jedinka prva = populacija[randomGenerator.vratiInt(populacija.length)];
		Jedinka druga = populacija[randomGenerator.vratiInt(populacija.length)];
		return (prva.racunajFaktorDobrote() > druga.racunajFaktorDobrote()) ? prva : druga;
	}

}
