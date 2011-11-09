/**
 * 
 */
package ea.ga;

import ea.util.KoloSrece;

/**
 * Sucelje koje definira jednu jedinku.
 * 
 * @author Zlikavac32
 *
 */
public interface Jedinka extends Comparable<Jedinka>, KoloSrece.Racunljiv {
	
	/**
	 * Racuna faktor dobrote za trenutnu jedinku.
	 * 
	 * @return Faktor dobrote
	 */
	public double racunajFaktorDobrote();

	/**
	 * Kopira trenutnu jedinku.
	 * 
	 * @return Kopija jedinke
	 */
	public Jedinka kopiraj();
	
	/**
	 * Mutira trenutnu jedinku.
	 * 
	 * @param mutator Mutator koji se koristi
	 */
	public void mutiraj(int mutator, double vjerojatnostMutacije);
	
	/**
	 * Mutira trenutnu jedinku za neku deltu.
	 * 
	 * @param mutator Mutator koji se koristi
	 * @param delta Delta koja odreduje nacin mutacije
	 * @param vjerojatnostMutacije 
	 */
	public void mutiraj(int mutator, Object delta, double vjerojatnostMutacije);
	
	/**
	 * Rekombinira trenutnu jedinku sa partnerom koristeci rekombinator.
	 * 
	 * @param rekombinator Rekombinator koji se koristi
	 * @param partner Partner koji se koristi u rekombinaciji
	 */
	public void rekombiniraj(int rekombinator, Jedinka partner);

	/**
	 * Inicijalizira trenutnu jedinku.
	 */
	public void inicijaliziraj();
	
	public Object vratiVrijednost();
	
}
