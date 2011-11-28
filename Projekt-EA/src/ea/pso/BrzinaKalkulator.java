/**
 * 
 */
package ea.pso;

import ea.util.RandomGenerator;

/**
 * @author Zlikavac32
 *
 */
public interface BrzinaKalkulator<T> {
	
	public double[] izracunajBrzinu(Cestica<T> cestica, RandomGenerator generator);
	
	public void zavrsiKrug();
	
}
