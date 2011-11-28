package ea.aco;

public abstract class Mrav implements Comparable<Mrav> {

	
	/**
	 * Racuna faktor dobrote za trenutnu jedinku.
	 * 
	 * @return Faktor dobrote
	 */
	public abstract double racunajFaktorDobrote();
	
	public abstract Mrav kopiraj();

	@Override
	public int compareTo(Mrav strani) {
		if (strani == null) { return -1; }
		double mojFaktorDobrote = racunajFaktorDobrote();
		double straniFaktorDobrote = strani.racunajFaktorDobrote();
		if (mojFaktorDobrote > straniFaktorDobrote) { return -1; }
		else if (mojFaktorDobrote < straniFaktorDobrote) { return 1; }
		return 0;
	}
	
}
