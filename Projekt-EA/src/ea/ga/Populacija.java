/**
 * 
 */
package ea.ga;

import java.util.ArrayList;
import java.util.List;

import ea.util.RandomGenerator;

/**
 * @author Zlikavac32
 *
 */
public abstract class Populacija<T extends Krajolik<?>> {
	
	protected List<Jedinka<T>> jedinke;
	
	protected Object delta;
	
	protected int mutator;
	
	protected boolean koristiMutaciju = false;
	
	protected boolean koristiRekombinaciju = false;
	
	protected int rekombinator;

	protected double vjerojatnostMutacije;
	
	protected Selektor<T> selektor;

	protected T krajolik;

	protected RandomGenerator generator;

	public Populacija(int velicina) {
		jedinke = new ArrayList<Jedinka<T>>(velicina);
		for (int i = 0; i < velicina; i++) { jedinke.add(null); }
	}

	public Jedinka<T> vratiNajbolju() {
		if (jedinke.size() == 0) { return null; }
		Jedinka<T> najbolja = jedinke.get(0);
		double najboljiFaktorDobrote = najbolja.racunajFaktorDobrote();
		int limit = jedinke.size();
		for (int i = 1; i < limit; i++) {
			double moguciNajboljiFaktorDobrote = jedinke.get(i).racunajFaktorDobrote();
			if (moguciNajboljiFaktorDobrote > najboljiFaktorDobrote) {
				najbolja = jedinke.get(i);
				najboljiFaktorDobrote = moguciNajboljiFaktorDobrote;
			}
		}
		
		return najbolja.kopiraj();
	}

	public Jedinka<T> vratiNajgoru() {
		if (jedinke.size() == 0) { return null; }
		Jedinka<T> najgora = jedinke.get(0);
		double najgoriFaktorDobrote = najgora.racunajFaktorDobrote();
		int limit = jedinke.size();
		for (int i = 1; i < limit; i++) {
			double moguciNajgoriFaktorDobrote = jedinke.get(i).racunajFaktorDobrote();
			if (moguciNajgoriFaktorDobrote < najgoriFaktorDobrote) {
				najgora = jedinke.get(i);
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
		int limit = jedinke.size();
		for (int i = 0; i < limit; i++) {
			graditelj.append("\t");
			graditelj.append(jedinke.get(i));
			graditelj.append("\n");
		}
		graditelj.append("]");
		return graditelj.toString();
	}
	
	public List<Jedinka<T>> vratiJedinke() {
		List<Jedinka<T>> vrati = new ArrayList<Jedinka<T>>(jedinke.size());
		int limit = jedinke.size();
		for (int i = 0; i < limit; i++) { vrati.add(i, jedinke.get(i)); }
		return vrati;
	}

	public void postaviVjerojatnostMutacije(double vjerojatnostMutacije) {
		this.vjerojatnostMutacije = vjerojatnostMutacije;
	}
	
	public void postaviSelektor(Selektor<T> selektor) {
		this.selektor = selektor;
	}
	
	public Selektor<T> vratiSelektor() { return selektor; }
	
	public abstract void evoluiraj();
	
	public abstract void inicijaliziraj();
	
	public T vratiKrajolik() { return krajolik; }
	
	public RandomGenerator vratiGenerator() { return generator; }
	
	public void postaviGenerator(RandomGenerator generator) { this.generator = generator; }
	
	public void postaviKrajolik(T krajolik) { this.krajolik = krajolik; }
	
}
