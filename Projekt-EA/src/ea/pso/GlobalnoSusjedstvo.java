/**
 * 
 */
package ea.pso;

/**
 * @author Zlikavac32
 *
 */
public class GlobalnoSusjedstvo<T> implements Susjedstvo<T> {

	protected Cestica<T>[] susjedstvo;
	
	protected Cestica<T> najbolje;
	
	protected Cestica<T> najgore;

	@Override
	public void stvori(int indeksCestice, Cestica<T>[] cestice) {
		this.susjedstvo = cestice;
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
