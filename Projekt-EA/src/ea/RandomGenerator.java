/**
 * 
 */
package ea;

import java.util.Random;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class RandomGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4279674621367886979L;
	
	private final static Random random = new Random();

	/**
	 * @return Slucajna vrijednost iz intervala [0, 1)
	 */
	public static double vratiDouble() { return random.nextDouble(); }

	/**
	 * @return Gaussova vrijednost sa sredinom 0 i devijacijom 1
	 */
	public static double vratiGauss() { return random.nextGaussian(); }

}
