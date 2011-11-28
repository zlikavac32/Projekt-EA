/**
 * 
 */
package ea.pso;

import java.lang.reflect.Array;

/**
 * @author Zlikavac32
 *
 */
public class LokalnoSusjedstvo<T> implements Susjedstvo<T> {

	protected Cestica<T>[] susjedstvo;
	
	protected Cestica<T> najbolje;
	
	protected Cestica<T> najgore;

	protected int udaljenost;
	
	public LokalnoSusjedstvo(int udaljenost) {
		if (udaljenost < 0) {
			throw new IllegalArgumentException("Udaljenost lokalnog susjedstva mora biti veca ili jednaka 0");
		}
		this.udaljenost = udaljenost;
	}
	
	@Override
	public void stvori(int indeksCestice, Cestica<T>[] cestice) {
		int velicina = 1 + udaljenost * 2;
		if (velicina > cestice.length) {
			throw new IllegalArgumentException("Raspon susjedstva je veci od populacije");
		}
		
		susjedstvo = cestice.getClass().cast(Array.newInstance(cestice.getClass(), velicina));
		susjedstvo[0] = cestice[indeksCestice];
		for (int i = 0; i < udaljenost; i++) {
			susjedstvo[i + 1] = cestice[(indeksCestice - i - 1 + cestice.length) % cestice.length];
		}
		for (int i = 0; i < udaljenost; i++) {
			susjedstvo[udaljenost + i + 1] = cestice[(indeksCestice + i + 1 + cestice.length) % cestice.length];
		}
	}

	/**
	 * @see ea.pso.Susjedstvo#azuriraj()
	 */
	@Override
	public void azuriraj() {
		if (susjedstvo == null || susjedstvo.length == 0) { return; }
		Cestica<T> najgore = susjedstvo[0];
		Cestica<T> najbolje = najgore;
		for (int i = 1; i < susjedstvo.length; i++) {
			if (susjedstvo[i].compareTo(najbolje) < 0) {
				najbolje = susjedstvo[i];
			}
			if (susjedstvo[i].compareTo(najgore) > 0) {
				najgore = susjedstvo[i];
			}
		}
		this.najbolje = najbolje.kopiraj();
		this.najgore = najgore.kopiraj();
	}

	/**
	 * @see ea.pso.Susjedstvo#vratiNajbolju()
	 */
	@Override
	public Cestica<T> vratiNajbolju() {
		return najbolje.kopiraj();
	}

	/**
	 * @see ea.pso.Susjedstvo#vratiNajgoru()
	 */
	@Override
	public Cestica<T> vratiNajgoru() {
		return najgore.kopiraj();
	}

}
