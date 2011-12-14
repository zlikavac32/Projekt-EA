package ea.ais;

import java.util.ArrayList;
import java.util.List;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.util.FunkcijaKrajolik;
import ea.util.RandomGenerator;
import ea.util.RealniKrajolik;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomGenerator generator = new RandomGenerator();
		generator.postaviSjeme(123456L);
		Mutator<byte[]> mutator = new DistribuiraniMutator(4, generator);
		Populacija<byte[], double[], RealniKrajolik> populacija = new CLONALGPopulacija<byte[], double[], RealniKrajolik>(10, 10, 10, generator, mutator);
		((DistribuiraniMutator) mutator).postaviPopulaciju(populacija);
		populacija.inicijaliziraj(new Inicijalizator<byte[], double[], RealniKrajolik>() {
			
			@Override
			public List<AntiTijelo<byte[], double[], RealniKrajolik>> inicijaliziraj(int velicina, RandomGenerator generator) {
				
				List<AntiTijelo<byte[], double[], RealniKrajolik>> vrati = new ArrayList<AntiTijelo<byte[],double[],RealniKrajolik>>(velicina);
				
				FunkcijaKrajolik krajolik = new FunkcijaKrajolik();
				krajolik.postaviDonjuGranicu(new double[] { -5, -5 });
				krajolik.postaviGornjuGranicu(new double[] { 5, 5 });
				krajolik.postaviVarijable(new String[] { "x", "y" });
				krajolik.postaviInvertiran(true);
				try {
					krajolik.postaviFunkciju(
						new ExpressionBuilder("20 + x^2 + y^2 - 10 * cos(6.28 * x) - 10 * cos(6.28 * y)")
							.withVariableNames("x","y")
							.build()
					);
				} catch (UnknownFunctionException e) {
					e.printStackTrace();
				} catch (UnparsableExpressionException e) {
					e.printStackTrace();
				}
				
				for (int i = 0; i < velicina; i++) {
					RealnaVarijablaAntiTijelo antiTijelo = new RealnaVarijablaAntiTijelo(2, 12, krajolik);
					antiTijelo.inicijaliziraj(generator);
					vrati.add(antiTijelo);
				}
				
				
				return vrati;
			}
		});
		System.out.println(populacija.vratiGlobalnoNajbolje());
		System.out.println();
		for (int i = 0; i < 200; i++) {
			populacija.evoluiraj();
		}
		System.out.println(populacija.vratiGlobalnoNajbolje());
	}

}
