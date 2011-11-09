/**
 * 
 */
package ea.ga;

/**
 * @author Zlikavac32
 *
 */
public abstract class Populacija {
	
	protected Jedinka[] jedinke;
	
	protected Object delta;
	
	protected int mutator;
	
	protected boolean koristiMutaciju = false;
	
	protected boolean koristiRekombinaciju = false;
	
	protected int rekombinator;

	protected double vjerojatnostMutacije;
	
	protected Selektor selektor;

	public Populacija(int velicina) {
		jedinke = new Jedinka[velicina];
	}

	public Jedinka vratiNajbolju() {
		if (jedinke.length == 0) { return null; }
		Jedinka najbolja = jedinke[0];
		double najboljiFaktorDobrote = najbolja.racunajFaktorDobrote();
		for (int i = 1; i < jedinke.length; i++) {
			double moguciNajboljiFaktorDobrote = jedinke[i].racunajFaktorDobrote();
			if (moguciNajboljiFaktorDobrote > najboljiFaktorDobrote) {
				najbolja = jedinke[i];
				najboljiFaktorDobrote = moguciNajboljiFaktorDobrote;
			}
		}
		
		return najbolja.kopiraj();
	}

	public Jedinka vratiNajgoru() {
		if (jedinke.length == 0) { return null; }
		Jedinka najgora = jedinke[0];
		double najgoriFaktorDobrote = najgora.racunajFaktorDobrote();
		for (int i = 1; i < jedinke.length; i++) {
			double moguciNajgoriFaktorDobrote = jedinke[i].racunajFaktorDobrote();
			if (moguciNajgoriFaktorDobrote < najgoriFaktorDobrote) {
				najgora = jedinke[i];
				najgoriFaktorDobrote = moguciNajgoriFaktorDobrote;
			}
		}
		
		return najgora.kopiraj();
	}
	
	public void koristiMutaciju(int mutator) {
		koristiMutaciju = true;
		this.mutator = mutator;
	}
	
	public void koristiRekombinaciju(int rekombinator) {
		koristiRekombinaciju = true;
		this.rekombinator = rekombinator;
	}
	
	public void postaviMutacijskuDeltu(Object delta) { this.delta = delta; }
	
	@Override
	public String toString() {
		StringBuilder graditelj = new StringBuilder();
		graditelj.append("sa jedinkama\n[\n");
		for (int i = 0; i < jedinke.length; i++) {
			graditelj.append("\t");
			graditelj.append(jedinke[i]);
			graditelj.append("\n");
		}
		graditelj.append("]");
		return graditelj.toString();
	}
	
	public Jedinka[] vratiJedinke() {
		Jedinka[] vrati = new Jedinka[jedinke.length];
		for (int i = 0; i < jedinke.length; i++) { vrati[i] = jedinke[i].kopiraj(); }
		return vrati;
	}

	public void postaviVjerojatnostMutacije(double vjerojatnostMutacije) {
		this.vjerojatnostMutacije = vjerojatnostMutacije;
	}
	
	public void postaviSelektor(Selektor selektor) {
		this.selektor = selektor;
	}
	
	public Selektor vratiSelektor() { return selektor; }
	
	public abstract void evoluiraj();
	
	public abstract void inicijaliziraj();
}
