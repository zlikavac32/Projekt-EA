/**
 * 
 */
package ea.pso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zlikavac32
 *
 */
public class LokalnoSusjedstvo<T> implements Susjedstvo<T> {

	protected List<Cestica<T>> susjedstvo;
	
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
		
		susjedstvo = new ArrayList<Cestica<T>>(velicina);
		susjedstvo.add(cestice[indeksCestice]);
		for (int i = 0; i < udaljenost; i++) {
			susjedstvo.add(cestice[(indeksCestice - i - 1 + cestice.length) % cestice.length]);
		}
		for (int i = 0; i < udaljenost; i++) {
			susjedstvo.add(cestice[(indeksCestice + i + 1 + cestice.length) % cestice.length]);
		}
	}

	/**
	 * @see ea.pso.Susjedstvo#azuriraj()
	 */
	@Override
	public void azuriraj() {
		if (susjedstvo == null || susjedstvo.size() == 0) { return; }
		Cestica<T> najgore = susjedstvo.get(0);
		Cestica<T> najbolje = najgore;
		int kraj = susjedstvo.size();
		for (int i = 1; i < kraj; i++) {
			Cestica<T> temp = susjedstvo.get(i);
			if (temp.compareTo(najbolje) < 0) {
				najbolje = temp;
			}
			if (temp.compareTo(najgore) > 0) {
				najgore = temp;
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
