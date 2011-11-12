/**
 * 
 */
package ea.aco;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Zlikavac32
 *
 */
public class TSPMrav extends Mrav implements Iterable<Integer> {

	protected double[][] udaljenosti;
	
	protected Grad[] gradovi;
	
	protected int[] put;
	
	protected int indeks = 0;
	
	protected double duljinaPuta = 0;
	
	public TSPMrav(double[][] udaljenosti, Grad[] gradovi) {
		this.put = new int[udaljenosti.length];
		this.udaljenosti = udaljenosti;
		this.gradovi = gradovi;
	}
	
	/**
	 * Pretpostavka je da su dva puta jednaka ako se razlikuju za manje od 1e-9
	 * @see ea.aco.Mrav#compareTo(ea.aco.Mrav)
	 */
	@Override
	public int compareTo(Mrav mrav) {
		if (mrav == null) { return -1; }
		TSPMrav mravN = (TSPMrav) mrav;
		if (Math.abs(duljinaPuta - mravN.duljinaPuta) < 1e-9) { return 0; }
		return duljinaPuta < mravN.duljinaPuta ? -1 : 1;
	}

	public void dodajGradUPut(int grad) {
		if (indeks == put.length) { throw new IndexOutOfBoundsException("Dodano previse gradova"); }
		int preth = indeks - 1;
		if (indeks > 0) { 
			duljinaPuta += udaljenosti[put[preth]][grad];
			if (indeks == put.length - 1) {
				duljinaPuta += udaljenosti[put[0]][grad];
			}
		}
		put[indeks++] = grad;
	}
	
	public int vratiPrethodni() {
		if (indeks == 0) { return -1; }
		return put[indeks - 1];
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			private int tren = 0;
			
			@Override
			public boolean hasNext() { return tren < indeks; }

			@Override
			public Integer next() { return put[tren++]; }

			@Override
			public void remove() {
				//Nije implementirano
			}
		};
	}

	public void resetiraj() {
		indeks = 0;
		duljinaPuta = 0;
	}
	
	public Grad[] vratiPutanju() {
		if (indeks != gradovi.length) { throw new IllegalStateException("Indeks putanje i broj gradova nisu isti"); }
		Grad[] putanja = new Grad[gradovi.length];
		for (int i = 0; i < indeks; i++) {
			putanja[i] = gradovi[put[i]];
		}
		return putanja;		
	}

	@Override
	public Mrav kopiraj() {
		TSPMrav novi = new TSPMrav(udaljenosti, gradovi);
		novi.put = Arrays.copyOf(put, put.length);
		novi.indeks = indeks;
		novi.duljinaPuta = duljinaPuta;
		return novi;
	}
	
	@Override
	public String toString() {
		StringBuilder graditelj = new StringBuilder("TSPMrav [");
		for (Grad grad : vratiPutanju()) {
			graditelj.append("\n\t");
			graditelj.append(grad);
		}
		graditelj.append("\n]");
		return graditelj.toString();
	}

	public double vratiDuljinuPuta() { return duljinaPuta; }

}
