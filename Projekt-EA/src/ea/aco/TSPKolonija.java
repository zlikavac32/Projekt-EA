package ea.aco;

//import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ea.util.RandomGenerator;

public abstract class TSPKolonija extends Kolonija {

	protected RandomGenerator generator;

	protected Grad[] gradovi;
	
	protected int[] indeksi;
	
	protected double[][] udaljenosti;
	
	protected double[][] tragovi;
	
	protected double alfa;
	
	protected double konstantaIsparavanja;

	public TSPKolonija(
		List<Par<String, Par<Double, Double>>> gradovi, int brojMrava, 
		double konstantaIspravanja, RandomGenerator generator, double alfa
	) {
		this.generator = generator;
		this.gradovi = new Grad[gradovi.size()];
		this.alfa = alfa;
		Iterator<Par<String, Par<Double, Double>>> iterator = gradovi.iterator();
		for (int i = 0; iterator.hasNext(); i++) { 
			Par<String, Par<Double, Double>> gradOpis = iterator.next();
			this.gradovi[i] = new Grad(gradOpis.drugi.prvi, gradOpis.drugi.drugi, gradOpis.prvi);
		}
		mravi = new TSPMrav[brojMrava];
		this.konstantaIsparavanja = konstantaIspravanja;
	}
	
	@Override
	public void inicijaliziraj() {
		indeksi = new int[gradovi.length];
		for (int i = 0; i < indeksi.length; i++) { indeksi[i] = i; }
		udaljenosti = new double[gradovi.length][gradovi.length];
		tragovi = new double[gradovi.length][gradovi.length];
		double pocetniTrag = 1e-3;
		for (int i = 0; i < udaljenosti.length; i++) {
			udaljenosti[i][i] = 0;
			tragovi[i][i] = 0;
			for (int j = i + 1; j < udaljenosti.length; j++) {
				udaljenosti[j][i] = udaljenosti[i][j] = gradovi[i].udaljenostDo(gradovi[j]);
				//System.out.println("Udaljenost od " + gradovi[i] + " do " + gradovi[j] + " je " + udaljenosti[i][j]);
				tragovi[i][j] = tragovi[j][i] = pocetniTrag;
			}
		}
		for (int i = 0; i < mravi.length; i++) { mravi[i] = new TSPMrav(udaljenosti, gradovi); }
	}
	
	public Grad[] vratiGradove() { return gradovi; }
	
	@Override
	public void obaviSetnje() {
		for (int i = 0; i < mravi.length; i++) { obaviSetnju((TSPMrav) mravi[i]); }
	}
	
	protected abstract void obaviSetnju(TSPMrav mrav);
	
	@Override
	public void obaviIsparavnje() {
		for (int i = 0; i < tragovi.length; i++) {
			for (int j = 0; j < tragovi.length; j++) {
				tragovi[i][j] = tragovi[j][i] *= (1 - konstantaIsparavanja);
			}
		}
//		System.out.println("-----------------------------------");
//		for (int i = 0; i < udaljenosti.length; i++) {
//			for (int j = 0; j < udaljenosti.length; j++) {
//				System.out.print((new DecimalFormat("0.00000").format(tragovi[i][j])) + " ");
//			}
//			System.out.println();
//		}
	}

	@Override
	public void azurirajTragove(int brojNajboljihMrava) {
		Arrays.sort(mravi);
		brojNajboljihMrava = Math.min(mravi.length, brojNajboljihMrava);
		for (int i = 0; i < brojNajboljihMrava; i++) {
			TSPMrav mrav = (TSPMrav) mravi[i];
			Iterator<Integer> putanja = mrav.iterator();
			if (!putanja.hasNext()) { continue; }
			Integer pocetak = putanja.next();
			Integer kraj = null;
			double nazivnik = mrav.duljinaPuta;
			if (Double.compare(mrav.duljinaPuta, 0) == 0) {
				nazivnik = 1e-20;
			}
			double delta = 1 / nazivnik;
			while (putanja.hasNext()) {
				kraj = putanja.next();
				tragovi[pocetak][kraj] = tragovi[kraj][pocetak] += delta;
				pocetak = kraj;
			}
		}
	}

}
