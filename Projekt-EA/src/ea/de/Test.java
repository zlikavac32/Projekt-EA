package ea.de;

//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ea.util.PolinomKrajolik;
import ea.util.RandomGenerator;

public class Test {

	protected static PolinomKrajolik krajolik;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomGenerator r = new RandomGenerator();
		r.postaviSjeme(0);
				
		double[][] varijable = new double[101][1];
		
		for (int i = 0; i <= 100; i++) {
				varijable[i][0] = 5 - i * 10 / 100.;
		}
		
		double[][] koeficijenti = new double[][] {
			{ 
				-1.9, 0.104, 0, -1.234, 1
			}
		};
		
		RandomGenerator tr = new RandomGenerator();
		tr.setSeed(10);
		
//		DecimalFormat f = new DecimalFormat("0.000000");
//		
//		for (int j = 0; j < 1; j++) {
//			System.out.print("Varijabla ");
//			System.out.print(j + 1);
//			System.out.print(" ");
//			for (int i = 0; i <= 12; i++) {
//				koeficijenti[j][i] = tr.nextDouble() * (6) - 3;
//				System.out.print(f.format(koeficijenti[j][i]));
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
		
		krajolik = new PolinomKrajolik(koeficijenti, varijable);
		
		krajolik.postaviDonjuGranicu(new double[] { -5, -5 });
		krajolik.postaviGornjuGranicu(new double[] { 5, 5 });
		
		Mutator<double[][]> mutator = new BinomniMutator(1, r);
		Selektor<double[][], PolinomKrajolik> selektor = new NajboljiSelektor<PolinomKrajolik>(.9, 1);
		
		DEPopulacija<double[][], PolinomKrajolik> populacija = 
			new DEPopulacija<double[][], PolinomKrajolik>(100, r, mutator, selektor);
		
		populacija.inicijaliziraj(new Inicijalizator<double[][], PolinomKrajolik>() {
			
			@Override
			public List<Vektor<double[][], PolinomKrajolik>> inicijaliziraj(int velicina, RandomGenerator generator) {

				List<Vektor<double[][], PolinomKrajolik>> vektori = new ArrayList<Vektor<double[][], PolinomKrajolik>>(velicina);
				
				for (int i = 0; i < velicina; i++) {
					KoeficijentiVektor novi = new KoeficijentiVektor(1, 5, krajolik);
					novi.inicijaliziraj(generator);
					vektori.add(novi);
				}
				
				return vektori;
			}
		});
		
		for (int i = 0; i < 350; i++) {
			populacija.evoluiraj();
		}
		System.out.println(populacija.vratiGlobalnoNajbolje());
		
	}

}
