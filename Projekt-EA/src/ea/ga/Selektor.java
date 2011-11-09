/**
 * 
 */
package ea.ga;

/**
 * @author Zlikavac32
 *
 */
public abstract class Selektor {
	
	protected Jedinka[] populacija;
	
	public void postaviPopulaciju(Jedinka[] populacija) {
		this.populacija = populacija;
	}
	
	public abstract Jedinka vratiSljedecuJedinku();

}
