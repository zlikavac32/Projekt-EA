/**
 * 
 */
package ea;

import java.util.List;

import ea.aco.AntSystemTSPKolonija;
import ea.aco.Grad;
import ea.aco.Par;
import ea.aco.SimpleACOTSPKolonija;
//import ea.aco.SimpleACOTSPKolonija;
import ea.aco.TSPKolonija;
import ea.aco.gui.ACOGUI;
import ea.gui.GUI;
import ea.util.RandomGenerator;
import ea.util.XKorakaKriterijKraja;
import ea.aco.TSPMrav;

/**
 * @author Zlikavac32
 *
 */
public class ACOSimulator extends Simulator<Par<Grad[], Grad[]>> {

	public static final int SIMPLE_ACO_ALGORITAM = 0;
	
	public static final int ANT_SYSTEM_ALGORITAM = 1;
	
	private XKorakaKriterijKraja<TSPKolonija> kriterijKraja;
	
	private int brojGeneracija = 1;

	private List<Par<String, Par<Integer, Integer>>> gradoviLista;

	private int brojMrava;

	private double konstantaIsparavanja;

	private double beta;

	private double alfa;

	private int algoritam;

	private int brojMravaAzurira;

	public ACOSimulator() { 
		randomGenerator = new RandomGenerator();
	}
	
	public void koristeciSjeme(long sjeme) { randomGenerator.postaviSjeme(sjeme); }

	public void koristeciBrojMrava(int brojMrava) {
		if (brojMrava < 1) { throw new IllegalArgumentException("Broj mrava mora biti veci od 0"); }
		this.brojMrava = brojMrava;
	}

	public void koristeciBrojMravaZaAzuriranje(int brojMravaAzurira) {
		if (brojMravaAzurira < 1) { 
			throw new IllegalArgumentException("Broj mrava za azuriranje mora biti veci od 0"); 
		}
		this.brojMravaAzurira = brojMravaAzurira;
	}

	public void uzBrojGeneracija(int brojGeneracija) { 
		this.brojGeneracija = brojGeneracija;
		kriterijKraja = new XKorakaKriterijKraja<TSPKolonija>(brojGeneracija); 
	}

	public void koristeciGradove(
			List<Par<String, Par<Integer, Integer>>> gradoviLista) {
		this.gradoviLista = gradoviLista;
	}

	public void koristeciKonstantuIsparavanja(double konstantaIsparavanja) {
		this.konstantaIsparavanja = konstantaIsparavanja;
	}

	public void koristeciBeta(double beta) {
		this.beta = beta;
	}

	public void koristeciAlfa(double alfa) {
		this.alfa = alfa;
	}
	
	public void koristeciAlgoritam(int algoritam) {
		if (algoritam != SIMPLE_ACO_ALGORITAM && algoritam != ANT_SYSTEM_ALGORITAM) {
			throw new IllegalArgumentException("Podrzani algoritmi su SIMPLE_ACO_ALGORITAM i ANT_SYSTEM_ALGORITAM");
		}
		this.algoritam = algoritam;
	}

	@Override
	protected Void doInBackground() 
		throws Exception {
		
		try { simuliraj(); }
		catch (Exception e) {
			if (!(e instanceof InterruptedException)) {
				GUI.zapisiUZapisnikGresku(e.getMessage()); 
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void simuliraj()
		throws InterruptedException {
		
		TSPKolonija kolonija = null;
		if (algoritam == ANT_SYSTEM_ALGORITAM) {
			kolonija = new AntSystemTSPKolonija(gradoviLista, brojMrava, konstantaIsparavanja, randomGenerator, alfa, beta);
		} else {
			kolonija = new SimpleACOTSPKolonija(gradoviLista, brojMrava, konstantaIsparavanja, randomGenerator, alfa);
		}
		kolonija.inicijaliziraj();
		ACOGUI.iscrtajGradove(kolonija.vratiGradove());
		kriterijKraja = new XKorakaKriterijKraja<TSPKolonija>(brojGeneracija);
		TSPMrav najbolje = null;
		while (!kriterijKraja.jeKraj(kolonija)) {
			if (Globalno.vratiBrzinu() > 0) { Thread.sleep(Globalno.vratiBrzinu()); }
			kolonija.evoluiraj(brojMravaAzurira);
			TSPMrav moguceNajbolje = (TSPMrav) kolonija.vratiNajbolje();
			najbolje = (TSPMrav) kolonija.vratiGlobalnoNajbolje();
			publish(new Par<Grad[], Grad[]>(najbolje.vratiPutanju(), moguceNajbolje.vratiPutanju()));	
		}
		GUI.zapisiUZapisnik("Najbolje rjesenje: " + najbolje);
		if (najbolje != null) { GUI.zapisiUZapisnik("Ukupne udaljenosti: " + najbolje.vratiDuljinuPuta()); }
	}
	
	@Override
    protected void process(List<Par<Grad[], Grad[]>> populacije) {
		Par<Grad[], Grad[]> zadnji = populacije.get(populacije.size() - 1);
		ACOGUI.iscrtajPutanju(zadnji.prvi, zadnji.drugi);
        setProgress((int) ((((XKorakaKriterijKraja<TSPKolonija>) kriterijKraja).vratiBrojProteklihGeneracija() / (double) brojGeneracija) * 100));
    }
	
}
