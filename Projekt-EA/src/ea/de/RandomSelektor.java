package ea.de;

import java.util.List;

import ea.util.Krajolik;
import ea.util.Par;
import ea.util.RandomGenerator;

public class RandomSelektor<T extends Krajolik<double[][]>> implements Selektor<double[][], T> {
	
	protected double faktorTezine;
	
	protected int brojParova;
	
	public RandomSelektor(double faktorTezine, int brojParova) {
		this.faktorTezine = faktorTezine;
		this.brojParova = brojParova;
	}

	@Override
	public Par<Vektor<double[][], T>, double[][]> selektiraj(
		int indeks, Populacija<double[][], T> populacija, RandomGenerator generator
	) {
		List<Vektor<double[][], T>> vektori = populacija.vratiPopulaciju();
		
		int kraj = vektori.size();
		int[] listaIndeksa = new int[kraj - 1];
		
		for (int i = 0, c = 0; i < kraj; i++) {
			if (i == indeks) { continue; }
			listaIndeksa[c++] = i;
		}
		
		kraj--;
		
		double[][] tempVektor = populacija.vratiPopulaciju().get(0).vratiVrijednost();
		
		int[] indeksi = new int[brojParova];
		
		for (int i = 0; i < brojParova; i++) {
			int r = generator.vratiInt(kraj);
			indeksi[i] = listaIndeksa[r];
			int temp = listaIndeksa[kraj - 1];
			listaIndeksa[kraj - 1] = listaIndeksa[r];
			listaIndeksa[r] = temp;
			kraj--;
		}
		
		double[][] donor = new double[tempVektor.length][tempVektor[0].length];
		
		kraj = brojParova / 2;
		
		for (int k = 0; k < kraj; k++) {
			double[][] prvi = populacija.vratiPopulaciju().get(indeksi[2 * k]).vratiVrijednost();
			double[][] drugi = populacija.vratiPopulaciju().get(indeksi[2 * k + 1]).vratiVrijednost();
			
			for (int i = 0; i < donor.length; i++) {
				for (int j = 0; j < donor[0].length; j++) {
					donor[i][j] = faktorTezine * (prvi[i][j] - drugi[i][j]);
				}
			}
		}
		
		return new Par<Vektor<double[][], T>, double[][]>(
			populacija.vratiLokalnoNajbolje().kopiraj(),
			donor
		);
		
	}

}
