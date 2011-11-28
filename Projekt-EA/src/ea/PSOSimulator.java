/**
 * 
 */
package ea;

import java.util.List;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.pso.Cestica;
import ea.pso.DinamickaOgranicavajucaInercijaBrzinaKalkulator;
import ea.pso.GlobalnoSusjedstvo;
import ea.pso.RealnaVarijablaCestica;
import ea.pso.Roj;
import ea.pso.StandradniRealnaVarijablaRoj;
import ea.pso.Susjedstvo;
import ea.pso.SusjedstvoGraditelj;
import ea.util.FunkcijaKrajolik;
import ea.util.RandomGenerator;
import ea.util.XKorakaKriterijKraja;


/**
 * @author Zlikavac32
 *
 */
public class PSOSimulator extends Simulator<RealnaVarijablaCestica[]> {

//	private Cestica<Double[]> najbolje = null;
	
	private XKorakaKriterijKraja<Roj<?>> kriterijKraja;
	
	private FunkcijaKrajolik krajolik;
	
	public PSOSimulator() { 
		randomGenerator = new RandomGenerator();
		randomGenerator.postaviSjeme(1);
		krajolik = new FunkcijaKrajolik();
	}
	@Override
	protected Void doInBackground() 
		throws Exception {
				
		try { simuliraj(); }
		catch (InterruptedException e) { Thread.currentThread().interrupt(); }
		catch (Exception e) {
			//gui.zapisiUZapisnikGresku(e.getMessage()); 
			e.printStackTrace();
		}
		
		return null;
	}
	
//	@SuppressWarnings("unchecked")
	private void simuliraj()
		throws InterruptedException {
		
		krajolik.postaviDonjuGranicu(new double[] {
			-5, -5
		});
		krajolik.postaviGornjuGranicu(new double[] {
			5, 5
		});
		krajolik.postaviVarijable(new String[] {
			"x", "y"
		});
		krajolik.postaviInvertiran(true);
		try {
			krajolik.postaviFunkciju(new ExpressionBuilder("20 + x^2 - cos(6.28 * x) + y^2 - sin(6.28 * y)").withVariableNames("x").withVariableNames("y").build());
		} catch (UnknownFunctionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnparsableExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Roj<Double[]> roj = new StandradniRealnaVarijablaRoj(5, randomGenerator, 2, krajolik, new SusjedstvoGraditelj<Double[]>() {
			
			@Override
			public Susjedstvo<Double[]> stvoriSusjedstvo() {
				return new GlobalnoSusjedstvo<Double[]>();
			}
			
		}, new DinamickaOgranicavajucaInercijaBrzinaKalkulator(2.05, 2.05, new double[] {
			-5, -5
		}, new double[] {
			5, 5
		}, 0.9, 0.5, 100));
		
		roj.inicijaliziraj();
		//((ACOGUI) gui).iscrtajGradove(kolonija.vratiGradove());
		kriterijKraja = new XKorakaKriterijKraja<Roj<?>>(200);
		while (!kriterijKraja.jeKraj(roj)) {
			if (Globalno.vratiBrzinu() > 0) { Thread.sleep(Globalno.vratiBrzinu()); }
			for (Cestica<Double[]> cestica : roj.vratiCestice()) {
				System.out.println("Cestica: ");
				for (double vrijednost : cestica.vratiVrijednost()) {
					System.out.println("\t" + vrijednost);
				}
			}
			System.out.println();
			System.out.println();
			roj.evoluiraj();
//			Cestica<Double[]> moguceNajbolje = roj.vratiLokalnoNajbolje();
//			najbolje = roj.vratiGlobalnoNajbolje();
			//publish(new Par<TSPMrav, TSPMrav>(najbolje, moguceNajbolje));	
		}
		for (Cestica<Double[]> cestica : roj.vratiCestice()) {
			System.out.println("Cestica: ");
			for (double vrijednost : cestica.vratiVrijednost()) {
				System.out.println("\t" + vrijednost);
			}
		}
		ispisiRjesenje();
	}
	
	@Override
    protected void process(List<RealnaVarijablaCestica[]> populacije) {
//		Par<TSPMrav, TSPMrav> zadnji = populacije.get(populacije.size() - 1);
//		((ACOGUI) gui).iscrtajPutanju(zadnji.prvi.vratiPutanju(), zadnji.drugi.vratiPutanju());
//        setProgress((int) ((((XKorakaKriterijKraja<TSPKolonija>) kriterijKraja).vratiBrojProteklihGeneracija() / (double) brojGeneracija) * 100));
    }

	public void uzBrojKoraka(int brojKoraka) {
//		this.brojKoraka = brojKoraka;
	}

	@Override
	public void ispisiRjesenje() {
//		gui.zapisiUZapisnik("Najbolje rjesenje: " + najbolje);
//		if (najbolje != null) { gui.zapisiUZapisnik("Ukupne udaljenosti: " + najbolje.vratiDuljinuPuta()); }
	}
	
}
