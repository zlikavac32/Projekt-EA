/**
 * 
 */
package ea.aco;

import java.util.Arrays;
import java.util.List;

import ea.util.RandomGenerator;
import ea.util.Util;

/**
 * @author Zlikavac32
 *
 */
public class AntSystemTSPKolonija extends TSPKolonija {
	
	protected double[][] heuristika;
	
	protected double beta;
	
	public AntSystemTSPKolonija(
		List<Par<String, Par<Integer, Integer>>> gradovi, int brojMrava, double konstantaIsparavanja, 
		RandomGenerator generator, double alfa, double beta
	) {
		super(gradovi, brojMrava, konstantaIsparavanja, generator, alfa);
		this.beta = beta;
	}
	
	@Override
	public void inicijaliziraj() {
		super.inicijaliziraj();
		heuristika = new double[udaljenosti.length][udaljenosti.length];
		for (int i = 0; i < udaljenosti.length; i++) {
			udaljenosti[i][i] = 0;
			for (int j = i + 1; j < udaljenosti.length; j++) {
				heuristika[i][j] = heuristika[j][i] = Math.pow(1 / udaljenosti[i][j], beta);
			}
		}
		
	}

	@Override
	protected void obaviSetnju(TSPMrav mrav) {
		if (indeksi == null || indeksi.length < 1) { return ; }
		int[] dohvatljivi = Arrays.copyOf(indeksi, indeksi.length);
		Util.izmjesaj(dohvatljivi, generator);
		mrav.resetiraj();
		mrav.dodajGradUPut(dohvatljivi[0]);
		double[] vjerojatnostiOdabira = new double[dohvatljivi.length];
		int kraj = dohvatljivi.length - 1;
		for (int i = 1; i < kraj; i++) {
			double suma = 0;
			int prethodni = mrav.vratiPrethodni();
			//TODO: Osmisli nacin da se koristi KoloSrece razred
			for (int j = i; j < dohvatljivi.length; j++) { 
				int sljedeci = dohvatljivi[j];
				suma += vjerojatnostiOdabira[sljedeci] = Math.pow(tragovi[prethodni][sljedeci], alfa) * heuristika[prethodni][sljedeci];
			}
			for (int j = i; j < dohvatljivi.length; j++) { 
				int sljedeci = dohvatljivi[j];
				vjerojatnostiOdabira[sljedeci] /= suma;
			}
			double brojUIntervalu = generator.vratiDouble();
			double vjerojatnostDoSada = 0;
			int odabranaJedinka = i;
			for (int j = i; vjerojatnostDoSada < brojUIntervalu && j < dohvatljivi.length; j++) {
				vjerojatnostDoSada += vjerojatnostiOdabira[dohvatljivi[j]];
				odabranaJedinka = j;
			}
			int temp = dohvatljivi[i];
			dohvatljivi[i] = dohvatljivi[odabranaJedinka];
			dohvatljivi[odabranaJedinka] = temp;
			mrav.dodajGradUPut(dohvatljivi[i]);
		}
		mrav.dodajGradUPut(dohvatljivi[dohvatljivi.length - 1]);
	}

	@Override
	public void azurirajTragove() { azurirajTragove(mravi.length); }

	@Override
	public void evoluiraj(int brojMravaAzurira) {
		obaviSetnje();
		obaviIsparavnje();
		azurirajTragove(mravi.length);	
		obnoviGlobalnoNajbolje();
	}

	@Override
	public void evoluiraj() { evoluiraj(mravi.length); }

}
