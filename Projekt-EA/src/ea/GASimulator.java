/**
 * 
 */
package ea;

import java.util.List;

import de.congrace.exp4j.Calculable;

import ea.ga.FenotipNepreklapajucaPopulacija;
import ea.ga.FenotipPreklapajucaPopulacija;
import ea.ga.GenotipJedinka;
import ea.ga.DvoTurnirskiSelektor;
import ea.ga.FunkcijaKrajolik;
import ea.ga.GenotipNepreklapajucaPopulacija;
import ea.ga.GenotipPreklapajucaPopulacija;
import ea.ga.Populacija;
import ea.ga.Jedinka;
import ea.ga.ProporcionalniSelektor;
import ea.ga.FenotipJedinka;
import ea.ga.Selektor;
import ea.ga.SkracivanjeSelektor;
import ea.ga.UniformniSelektor;
import ea.ga.gui.GAGUI;
import ea.gui.GUI;
import ea.util.KriterijKraja;
import ea.util.RandomGenerator;
import ea.util.XKorakaKriterijKraja;

/**
 * @author Zlikavac32
 *
 */
public class GASimulator extends Simulator<Jedinka[]> {
	
	public static final int MAKSIMUM = 2;
	
	public static final int MINIMUM = 1;
	
	public static final int DELTA_MUTACIJA = FenotipJedinka.DELTA_MUTACIJA;
	
	public static final int GAUSS_MUTACIJA = FenotipJedinka.GAUSS_MUTACIJA;
	
	public static final int OKRET_BITA_MUTACIJA = GenotipJedinka.OKRET_BITA_MUTACIJA;
	
	public static final int JEDAN_CVOR_REKOMBINACIJA = GenotipJedinka.JEDAN_CVOR_REKOMBINACIJA;
	
	public static final int DVA_CVORA_REKOMBINACIJA = GenotipJedinka.DVA_CVORA_REKOMBINACIJA;
	
	public static final int ARITMETICKA_SREDINA_REKOMBINACIJA = FenotipJedinka.ARITMETICKA_SREDINA_REKOMBINACIJA;
	
	public static final int TEZINSKA_SREDINA_REKOMBINACIJA = FenotipJedinka.TEZINSKA_SREDINA_REKOMBINACIJA;
	
	public static final int ALFTA_INTERVAL_REKOMBINACIJA = FenotipJedinka.ALFA_INTERVAL_REKOMBINACIJA;
	
	public static final int FENTOTIP = 0;
	
	public static final int GENOTIP = 1;

	public static final int NEPREKLAPAJUCA = 0;
	
	public static final int SKRACIVANJE = 1;
	
	public static final int PROPORCIONALNA = 2;
	
	public static final int DVO_TURNIRSKA = 3;
	
	public static final int UNIFORMNA = 4;

	public static final int PREKLAPAJUCA = 1;
	
	private int velicinaPopulacije;
	
	private int reprezentacija;
	
	private int brojBitova;
	
	private int mutacija;
	
	private boolean koristiMutaciju = false;
	
	private double mutacijaksaDelta;
	
	private double vjerojatnostMutacije;
	
	private int rekombinacija;
	
	private boolean koristiRekombinaciju = false;
	
	private int selektor;
	
	private int brojDjece;
	
	private KriterijKraja<Populacija> kriterijKraja;
	
	private int brojJedinki;

	private int brojGeneracija;

	private int populacijaVrsta;
	
	public GASimulator() { 
		krajolik = new FunkcijaKrajolik();
		randomGenerator = new RandomGenerator();
	}
	
	public void koristeciSjeme(long sjeme) { randomGenerator.postaviSjeme(sjeme); }
	
	public void koristeciFunkciju(Calculable f) { 
		((FunkcijaKrajolik) krajolik).postaviFunkciju(f);
	}
	
	public void unutarGranica(double dolje, double gore) { 
		if (dolje > gore) { throw new IllegalArgumentException("Donja granica je veca od gornje");	}
		((FunkcijaKrajolik) krajolik).postaviDonjuGranicu(dolje);
		((FunkcijaKrajolik) krajolik).postaviGornjuGranicu(gore);
	}
	
	public void traziEkstrem(int ekstrem) { 
		if (ekstrem != MAKSIMUM && ekstrem != MINIMUM) { throw new IllegalArgumentException("Mozemo traziti MINIMUM ili MAKSIMUM"); }
		if (ekstrem == MINIMUM) { ((FunkcijaKrajolik) krajolik).postaviInvertiran(true); }
	} 
	
	public void saVelicinomPopulacije(int velicina) { 
		if (velicina < 1) { throw new IllegalArgumentException("Populacija mora biti veca ili jednaka 1"); }
		velicinaPopulacije = velicina;
	}
	
	public void koristeciReprezentaciju(int reprezentacija) { 
		if (reprezentacija != GENOTIP && reprezentacija != FENTOTIP) { throw new IllegalArgumentException("Reprezentacija mora biti GENOTIP ili FENOTIP"); }
		this.reprezentacija = reprezentacija;
	}
	
	public void koristeciBrojBitova(int brojBitova) {
		if (brojBitova < 1) { throw new IllegalArgumentException("Broj bitova mora biti veci ili jednak 1"); }
		this.brojBitova = brojBitova;
	}
	
	public void koristeciMutaciju(int mutacija) { 
		if (mutacija != GAUSS_MUTACIJA && mutacija != DELTA_MUTACIJA && mutacija != OKRET_BITA_MUTACIJA) {
			throw new IllegalArgumentException("Podrzane mutacije su GAUSS_MUTACIJA, DELTA_MUTACIJA i OKRET_BITA_MUTACIJA");
		}
		this.mutacija = mutacija;
		koristiMutaciju = true;
	}
	
	public void uzMutacijskuDeltu(double mutacijskaDelta) { this.mutacijaksaDelta = mutacijskaDelta; }
	
	public void uzVjerojatnostMutacije(double vjerojatnostMutacije) { 
		if (vjerojatnostMutacije < 0 || vjerojatnostMutacije > 1) {
			throw new IllegalArgumentException("Vjerojatnost mutacija mora biti u rasponu [0, 1]");
		}
		this.vjerojatnostMutacije = vjerojatnostMutacije;
	}
	
	public void koristeciRekombinaciju(int rekombinacija) { 
		if (
			rekombinacija != JEDAN_CVOR_REKOMBINACIJA && rekombinacija != DVA_CVORA_REKOMBINACIJA &&
			rekombinacija != ARITMETICKA_SREDINA_REKOMBINACIJA && rekombinacija != TEZINSKA_SREDINA_REKOMBINACIJA &&
			rekombinacija != ALFTA_INTERVAL_REKOMBINACIJA
		) {
			throw new IllegalArgumentException("Podrzane rekombinacije su JEDAN_CVOR_REKOMBINACIJA i DVA_CVORA_REKOMBINACIJA");
		}
		this.rekombinacija = rekombinacija;
		koristiRekombinaciju = true;
	}
	
	public void koristeciSelektor(int selektor) { 
		if (selektor != UNIFORMNA && selektor != PROPORCIONALNA && selektor != DVO_TURNIRSKA && selektor != SKRACIVANJE) {
			throw new IllegalArgumentException("Podrzani selektoru su UNIFORMNA, PROPORCIONALNA, DVO_TURNIRSKA i ODBACIVANJE");
		}
		this.selektor = selektor;
	}
	
	public void uzBrojJedinkiZaSkracivanje(int brojJedinki) { 
		if (brojJedinki < 1  || brojJedinki > velicinaPopulacije) {
			throw new IllegalArgumentException("Broj jedinki za odbacivanje mora biti veci ili jednak 1 i manji ili jednak velicini populacije"); 
		}
		this.brojJedinki = brojJedinki;
	}
	
	public void saBrojemDjece(int brojDjece) { 
		if (brojDjece < 0) { throw new IllegalArgumentException("Broj djece mora biti veci ili jednak 0"); }
		this.brojDjece = brojDjece;
	}
	
	public void uzBrojGeneracija(int brojGeneracija) { 
		this.brojGeneracija = brojGeneracija;
		kriterijKraja = new XKorakaKriterijKraja<Populacija>(brojGeneracija); 
	}


	public void koristeciPopulaciju(int populacija) {
		if (populacija != PREKLAPAJUCA && populacija != NEPREKLAPAJUCA) {
			throw new IllegalArgumentException("Podrzane populacije su PREKLAPAJUCA i NEPREKLAPAJUCA");
		}
		this.populacijaVrsta = populacija;
	}

	@Override
	protected Void doInBackground() 
		throws Exception {
		
		try { simuliraj(); }
		catch (Throwable e) {
			if (!(e instanceof InterruptedException)) {
				GUI.zapisiUZapisnikGresku(e.getMessage()); 
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private void simuliraj()
		throws InterruptedException {
		Populacija populacija = stvoriPopulaciju();
		if (koristiRekombinaciju) { populacija.koristiRekombinaciju(rekombinacija); }
		if (koristiMutaciju) { 
			populacija.koristiMutaciju(mutacija); 
			populacija.postaviMutacijskuDeltu(mutacijaksaDelta);
			populacija.postaviVjerojatnostMutacije(vjerojatnostMutacije);
		}
				
		populacija.inicijaliziraj();
		
		GASimulator.populacija = populacija;
		
		Jedinka najboljaJedinka = null;

        publish(populacija.vratiJedinke());
        
		while (!kriterijKraja.jeKraj(populacija)) {
			if (Globalno.vratiBrzinu() > 0) { Thread.sleep(Globalno.vratiBrzinu()); }
			populacija.evoluiraj();
			publish(populacija.vratiJedinke());
			Jedinka mogucaNajboljaJedinka = populacija.vratiNajbolju();
			if (najboljaJedinka == null || najboljaJedinka.racunajFaktorDobrote() < mogucaNajboljaJedinka.racunajFaktorDobrote()) {
				najboljaJedinka = mogucaNajboljaJedinka.kopiraj();
			}
		}
		
		GUI.zapisiUZapisnik("Najbolja jedinka: " + najboljaJedinka);
	}
	
	@Override
    protected void process(List<Jedinka[]> populacije) {
		GAGUI.iscrtajPopulaciju(populacije.get(populacije.size() - 1));
        setProgress((int) ((((XKorakaKriterijKraja<Populacija>) kriterijKraja).vratiBrojProteklihGeneracija() / (double) brojGeneracija) * 100));
    }

	private Populacija stvoriPopulaciju() {
		Populacija populacija;
		if (reprezentacija == GENOTIP) { populacija = stvoriGenotipskuPopulaciju(); }
		else { populacija = stvoriFenotipskuPopulciju(); }
		populacija.postaviSelektor(stvoriSelektor());
		return populacija;
	}

	private Populacija stvoriFenotipskuPopulciju() {
		if (populacijaVrsta == PREKLAPAJUCA) { return new FenotipPreklapajucaPopulacija(velicinaPopulacije, brojDjece); }
		return new FenotipNepreklapajucaPopulacija(velicinaPopulacije, brojDjece); 
	}

	private Populacija stvoriGenotipskuPopulaciju() {
		if (populacijaVrsta == PREKLAPAJUCA) { return new GenotipPreklapajucaPopulacija(velicinaPopulacije, brojDjece, brojBitova); }
		return new GenotipNepreklapajucaPopulacija(velicinaPopulacije, brojDjece, brojBitova); 
	}

	private Selektor stvoriSelektor() {
		switch (selektor) {
			case SKRACIVANJE :
				return new SkracivanjeSelektor(brojJedinki);
			case PROPORCIONALNA :
				return new ProporcionalniSelektor();
			case UNIFORMNA :
				return new UniformniSelektor();
			case DVO_TURNIRSKA :
				return new DvoTurnirskiSelektor();
			default :
				throw new IllegalArgumentException("Ne postoji definirana populacija");
		}
	}
	
}