/**
 * 
 */
package ea;

import javax.swing.SwingWorker;

import ea.gui.GUI;
import ea.util.RandomGenerator;

//import ea.ga.Krajolik;
//import ea.ga.Populacija;
//import ea.util.RandomGenerator;

/**
 * Simulator evolucijskog algoritma.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public abstract class Simulator<T> extends SwingWorker<Void, T> {
	
	protected RandomGenerator randomGenerator;
	
	protected GUI gui;
	
	public void postaviGUI(GUI gui) { this.gui = gui; }
	
	public abstract void ispisiRjesenje();
	
//	protected static RandomGenerator randomGenerator;
//	
//	protected static Krajolik<?> krajolik;
//	
//	protected static Populacija populacija;
//	
//	public static RandomGenerator vratiRandomGenerator() { return randomGenerator; }
//	
//	public static Krajolik<?> vratiKrajolik() { return krajolik; }
//	
//	public static Populacija vratiPopulaciju() { return populacija; }
	
}
