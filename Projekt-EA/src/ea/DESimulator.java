/**
 * 
 */
package ea;

import java.util.List;

import de.congrace.exp4j.Calculable;

import ea.de.Populacija;
import ea.de.Vektor;
import ea.util.FunkcijaKrajolik;
import ea.util.PolinomKrajolik;
import ea.util.RandomGenerator;
import ea.util.XKorakaKriterijKraja;

/**
 * @author Zlikavac32
 *
 */
public class DESimulator extends Simulator<List<Vektor<double[][], PolinomKrajolik>>> {
	
	private static final int NAJBOLJI_SELEKTOR = 0;

	private static final int RANDOM_SELEKTOR = 0;

	private static final int UNOFIRMNA_MUTACIJA = 0;

	private static final int BINOMNA_MUTACIAJ = 0;

	private static final int EKSPONENCIJALNA_MUTACIJA = 0;

	private int velicinaPopulacije;
	
	private int mutacija;
	
	private double vjerojatnostMutacije;

	private int selektor;
	
	private XKorakaKriterijKraja<Populacija<?, ?>> kriterijKraja;
	
	private int brojJedinki;

	private int brojGeneracija;

	private int populacijaVrsta;

	protected FunkcijaKrajolik krajolik;

	protected Populacija<double[][], PolinomKrajolik> populacija;
	
	protected Vektor<double[][], PolinomKrajolik> najboljaJedinka;

	public DESimulator() { 
		krajolik = new FunkcijaKrajolik();
		krajolik.postaviVarijable(new String[] { "x" });
		randomGenerator = new RandomGenerator();
	}
	
	public void koristeciSjeme(long sjeme) { randomGenerator.postaviSjeme(sjeme); }
	
	public void koristeciFunkciju(Calculable f) { 
		((FunkcijaKrajolik) krajolik).postaviFunkciju(f);
	}
	
	public void unutarGranica(double dolje, double gore) { 
		if (dolje > gore) { throw new IllegalArgumentException("Donja granica je veca od gornje");	}
		((FunkcijaKrajolik) krajolik).postaviDonjuGranicu(new double[] { dolje });
		((FunkcijaKrajolik) krajolik).postaviGornjuGranicu(new double[] { gore });
	}
	
	public void saVelicinomPopulacije(int velicina) { 
		velicinaPopulacije = velicina;
	}
	
	public void koristeciMutaciju(int mutacija) { 
		if (mutacija != UNOFIRMNA_MUTACIJA && mutacija != BINOMNA_MUTACIAJ && mutacija != EKSPONENCIJALNA_MUTACIJA) {
			throw new IllegalArgumentException(
				"Podrzane mutacije su UNOFIRMNA_MUTACIJA, BINOMNA_MUTACIAJ i EKSPONENCIJALNA_MUTACIJA"
			);
		}
		this.mutacija = mutacija;
	}
	
	public void uzVjerojatnostMutacije(double vjerojatnostMutacije) { 
		if (vjerojatnostMutacije < 0 || vjerojatnostMutacije > 1) {
			throw new IllegalArgumentException("Vjerojatnost mutacija mora biti u rasponu [0, 1]");
		}
		this.vjerojatnostMutacije = vjerojatnostMutacije;
	}
	
	public void koristeciSelektor(int selektor) { 
		if (selektor != RANDOM_SELEKTOR && selektor != NAJBOLJI_SELEKTOR) {
			throw new IllegalArgumentException("Podrzani selektoru su NAJBOLJI_SELEKTOR, RANDOM_SELEKTOR");
		}
		this.selektor = selektor;
	}
	
	public void uzBrojGeneracija(int brojGeneracija) { 
		this.brojGeneracija = brojGeneracija;
		kriterijKraja = new XKorakaKriterijKraja<Populacija<?, ?>>(brojGeneracija); 
	}

	@Override
	protected Void doInBackground() 
		throws Exception {
		
		try { simuliraj(); }
		catch (InterruptedException e) { Thread.currentThread().interrupt(); }
		catch (Exception e) {
			gui.zapisiUZapisnikGresku(e.getMessage()); 
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void simuliraj()
		throws InterruptedException {
		//TODO: Provjeri velicinu
	}
	
	@Override
	protected void process(
			List<List<Vektor<double[][], PolinomKrajolik>>> vektori) {
		// TODO Auto-generated method stub
		super.process(vektori);
	}

	@Override
	public void ispisiRjesenje() {
		// TODO Auto-generated method stub
		
	}
	
}
