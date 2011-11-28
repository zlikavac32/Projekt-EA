/**
 * 
 */
package ea.pso;

import java.util.Arrays;

import ea.util.RandomGenerator;
import ea.util.RealniKrajolik;

/**
 * @author Zlikavac32
 *
 */
public class RealnaVarijablaCestica extends Cestica<Double[]> {

	protected int brojVarijabli;
	
	protected RealniKrajolik krajolik;
	
	protected double faktorDobrote;
	
	protected double najboljiFaktorDobrote;
	
	public RealnaVarijablaCestica(
		int brojVarijabli, RealniKrajolik krajolik, Susjedstvo<Double[]> susjedstvo, 
		BrzinaKalkulator<Double[]> brzinaKalkulator
	) { 
		super(krajolik, susjedstvo, brzinaKalkulator); 
		this.krajolik = krajolik;
		this.brojVarijabli = brojVarijabli;
	}

	/**
	 * @see ea.pso.Cestica#jeValjanaVrijednost(java.lang.Object)
	 */
	@Override
	public boolean jeValjanaVrijednost(Double[] vrijednost) { return krajolik.jeValjanaVrijednost(vrijednost); }

	/**
	 * @see ea.pso.Cestica#racunajFaktorDobrote()
	 */
	@Override
	public double racunajFaktorDobrote() { return faktorDobrote; }
	
	protected void azurirajFaktorDobrote() {
		faktorDobrote = krajolik.racunajFaktorDobrote(vrijednost);
	}

	@Override
	public RealnaVarijablaCestica kopiraj() {
		RealnaVarijablaCestica kopija = new RealnaVarijablaCestica(
			brojVarijabli, krajolik, susjedstvo, brzinaKalkulator
		);
		kopija.brzina = Arrays.copyOf(brzina, brzina.length);
		kopija.vrijednost = Arrays.copyOf(vrijednost, vrijednost.length);
		return kopija;
	}

	@Override
	public void inicijaliziraj(RandomGenerator generator) {
		vrijednost = new Double[brojVarijabli];
		brzina = new double[brojVarijabli];
		for (int i = 0; i < brojVarijabli; i++) {
			brzina[i] = 0;
			vrijednost[i] = generator.vratiDouble() * (krajolik.vratiGornjuGranicu()[i] - krajolik.vratiDonjuGranicu()[i])
				+ krajolik.vratiDonjuGranicu()[i];
		}
		azurirajFaktorDobrote();
		obnoviNajboljuVrijednost();
	}

	@Override
	public void evoluiraj(RandomGenerator generator) {
		brzina = brzinaKalkulator.izracunajBrzinu(this, generator);
		Double[] novaVrijednost = new Double[vrijednost.length];
		for (int i = 0; i < brojVarijabli; i++) {
			novaVrijednost[i] = vrijednost[i] + brzina[i];
		}
		if (jeValjanaVrijednost(novaVrijednost)) {
			vrijednost = novaVrijednost;
			azurirajFaktorDobrote();
			obnoviNajboljuVrijednost();
		}
	}

	protected void obnoviNajboljuVrijednost() {
		if (najboljaVrijednost == null || racunajFaktorDobrote() > najboljiFaktorDobrote) {
			najboljaVrijednost = Arrays.copyOf(vrijednost, vrijednost.length);
			najboljiFaktorDobrote = faktorDobrote;
		}
	}

	@Override
	public Double[] vratiVrijednost() {
		return Arrays.copyOf(vrijednost, vrijednost.length);
	}

	@Override
	public Double[] vratiNajboljuVrijednost() {
		return Arrays.copyOf(najboljaVrijednost, najboljaVrijednost.length);
	}

}
