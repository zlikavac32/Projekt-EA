/**
 * 
 */
package ea.pso;

import ea.util.RandomGenerator;

/**
 * @author Zlikavac32
 *
 */
public class KonstantnaInercijaBrzinaKalkulator implements BrzinaKalkulator<Double[]> {

	private double c1;
	
	private double c2;
	
	private double[] donjaGranica;
	
	private double[] gornjaGranica;

	private double inercija;

	public KonstantnaInercijaBrzinaKalkulator(double c1, double c2, double[] donjaGranica, double gornjaGranica[], double inercija) {
		this.c1 = c1;
		this.c2 = c2;
		this.donjaGranica = donjaGranica;
		this.gornjaGranica = gornjaGranica;
		if (inercija > 1 || inercija < 0) { throw new IllegalArgumentException("Faktor inercije mora biti u rasponu [0, 1]"); }
		this.inercija = inercija;
	}
	
	@Override
	public double[] izracunajBrzinu(Cestica<Double[]> cestica,
			RandomGenerator generator) {
		
		double[] staraBrzina = cestica.vratiBrzinu();
		
		double[] brzina = new double[staraBrzina.length];
		Double[] trenutno = cestica.vratiVrijednost();
		Double[] osobnoNajbolje = cestica.vratiNajboljuVrijednost();
		Double[] globalnoNajbolje = cestica.vratiSusjedstvo().vratiNajbolju().vratiVrijednost();
		
		for (int i = 0; i < brzina.length; i++) {
			brzina[i] = inercija * staraBrzina[i] + c1 * generator.vratiDouble() * (
				osobnoNajbolje[i] - trenutno[i]
			) +  c2 * generator.vratiDouble() * (
				globalnoNajbolje[i] - trenutno[i]
			);
			if (brzina[i] < donjaGranica[i]) { brzina[i] = donjaGranica[i]; }
			else if (brzina[i] > gornjaGranica[i]) { brzina[i] = gornjaGranica[i]; }
		}
		
		return brzina;
	}

	@Override
	public void zavrsiKrug() {
		//Nista
	}

}
