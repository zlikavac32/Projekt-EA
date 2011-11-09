package ea.aco;

public abstract class Mrav implements Comparable<Mrav> {
	
	/**
	 * Vraca < 0 ako je trenutno rjesenje bolje od prosljedenog mrava, > 0 ako je losije ili 0 ako su isti
	 */
	@Override
	public abstract int compareTo(Mrav o);
	
	public abstract Mrav kopiraj();
	
}
