/**
 * 
 */
package ea.util;

/**
 * @author Zlikavac32
 *
 */
public class KoloSrece {

	private Racunljiv[] skup;

	private double[] koloSrece;
	
	private RandomGenerator generator;
	
	public static interface Racunljiv {
		public double racunajVrijednost();
	}
	
	public KoloSrece(Racunljiv[] skup, RandomGenerator generator) { 
		this.skup = skup; 
		this.generator = generator;
		stvoriKoloSrece();
	}
	
	private void stvoriKoloSrece() {
		double suma = 0;
		double odmak = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < skup.length; i++) { 
			double faktorDobrote = skup[i].racunajVrijednost();
			if (faktorDobrote < odmak) { odmak = faktorDobrote; }
		}
		
		odmak = (odmak < 0) ? -odmak : 0;
		
		suma = 0;
		
		for (int i = 0; i < skup.length; i++) { 
			suma += skup[i].racunajVrijednost() + odmak; 
		}
		
		double ukupnaSuma = suma;
		
		koloSrece = new double[skup.length];
				
		suma = 0;
		
		for (int i = 0; i < skup.length; i++) {
			suma += (skup[i].racunajVrijednost() + odmak) / ukupnaSuma;
			koloSrece[i] = suma;
		}
	}
	
	public Racunljiv okreni() {
		double vrijednost = generator.vratiDouble();
		
		int l = 0;
		int d = skup.length - 1;
		
		while (l < d) {
			int m = (l + d) / 2;
			if (koloSrece[m] > vrijednost) { d = m; }
			else { l = m + 1; }
		}
		
		return skup[l];
	}
	
}
