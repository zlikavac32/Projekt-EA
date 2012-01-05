/**
 * 
 */
package ea.de.gui;

import static ea.gui.GUIKonstante.*;
import static ea.de.gui.DEGUIKonstante.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.GASimulator;
import ea.ga.Jedinka;
import ea.gui.DijeljenaPloca;
import ea.gui.GUI;
import ea.gui.RadioGumbi;
import ea.gui.TekstualnaVrijednost;
import ea.util.RealniKrajolik;

/**
 * @author Zlikavac32
 *
 */
public class DEGUI extends GUI {

	private TekstualnaVrijednost funkcija;

	private TekstualnaVrijednost donjaGranica;

	private TekstualnaVrijednost brojTocaka;
	
	private TekstualnaVrijednost gornjaGranica;

	private TekstualnaVrijednost velicinaPopulacije;

	private TekstualnaVrijednost brojGeneracija;

	private TekstualnaVrijednost sjeme;
	
	private TekstualnaVrijednost brojUzoraka;
	
	private TekstualnaVrijednost redPolinoma;
	
	private TekstualnaVrijednost gornjaGranicaKoeficijenta;
	
	private TekstualnaVrijednost donjaGranicaKoeficijenta;
	
	private TekstualnaVrijednost brojParova;
	
	private RadioGumbi mutator;
	
	private RadioGumbi selektor;
	
	private TekstualnaVrijednost vjerojatnostMutacije;
	
	private TekstualnaVrijednost tau;
	

	//Ploce

	private Ploca funkcijaPloca = new Ploca();

	private Ploca donjaGranicaPloca = new Ploca();

	private Ploca brojTocakaPloca = new Ploca();

	private Ploca gornjaGranicaPloca = new Ploca();

	private Ploca velicinaPopulacijePloca = new Ploca();

	private Ploca brojGeneracijaPloca = new Ploca();

	private Ploca sjemePloca = new Ploca();

	private Ploca brojUzorakaPloca = new Ploca();
	
	private Ploca redPolinomaPloca = new Ploca();
	
	private Ploca gornjaGranicaKoeficijentaPloca = new Ploca();
	
	private Ploca donjaGranicaKoeficijentaPloca = new Ploca();
	
	private Ploca brojParovaPloca = new Ploca();
	
	private Ploca mutatorPloca = new Ploca();
	
	private Ploca selektorPloca = new Ploca();
	
	private Ploca vjerojatnostMutacijePloca = new Ploca();
	
	private Ploca tauPloca = new Ploca();

	
	protected XYSeriesCollection kolekcija;

	protected JFreeChart graf;

	protected XYPlot nacrt;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8465945028324200176L;
	
	private class NacrtajFunkciju extends SwingWorker<Void, Void> {
		
		RealniKrajolik krajolik;
		private XYSeries podatci;
		
		NacrtajFunkciju(RealniKrajolik krajolik) {
			this.krajolik = krajolik;
		}

		@Override
		protected Void doInBackground() 
			throws Exception {
			podatci = new XYSeries("Funckija");

			int brojElemenata = Integer.parseInt(brojTocaka.vratiVrijednost());
			if (brojElemenata < 1) { throw new IllegalArgumentException("Broj tocaka mora biti veci od 0"); }
			double dolje = krajolik.vratiDonjuGranicu()[0];
			double gore = krajolik.vratiGornjuGranicu()[0];
			double korak = (gore - dolje) / brojElemenata;
			for (int i = 0; i <= brojElemenata; i++) {
//				System.out.println("Izracunao sam (" + dolje + ", " + krajolik.racunajVrijednost(new double[] { dolje }) + ")");
				podatci.add(dolje, krajolik.racunajVrijednost(new double[] { dolje }));
				
//				System.out.println("Spremio sam (" + podatci.getX(podatci.getItemCount() - 1) + ", " + podatci.getY(podatci.getItemCount() - 1) + ")");
				dolje += korak;			
			}
			return null;
		}
		
		@Override
		protected void done() {
			kolekcija.removeSeries(0);
			kolekcija.addSeries(podatci);
		}
	}
	
	private class NacrtajJedinke extends SwingWorker<Void, Void> {
		
		RealniKrajolik krajolik;
		
		List<Jedinka<RealniKrajolik>> jedinke;

		private XYSeries podatci;
		
		NacrtajJedinke(List<Jedinka<RealniKrajolik>> jedinke, RealniKrajolik krajolik) {
			this.jedinke = jedinke;
			this.krajolik = krajolik;
		}

		@Override
		public Void doInBackground()
			throws Exception {
			podatci = new XYSeries("podatci");
			int limit = jedinke.size();
			for (int i = 0; i < limit; i++) {
				double x = (Double) jedinke.get(i).vratiVrijednost();
				podatci.add(x, krajolik.racunajVrijednost(new double[] { x }));
			}
			return null;
		}
		
		@Override
		protected void done() {
			XYSeriesCollection kolekcijaJedinki = new XYSeriesCollection(podatci);
			nacrt.setDataset(1, kolekcijaJedinki);
		}
		
	}

	public DEGUI(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	
	protected JPanel stvoriKontroleKontejner() {
		
		JPanel kontroleKontejner = new JPanel();
	
		kontroleKontejner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		kontroleKontejner.setLayout(new BoxLayout(kontroleKontejner, BoxLayout.Y_AXIS));
		
		inicijalizirajElementeKontrola();
		
		DijeljenaPloca[] elementi = new DijeljenaPloca[] {
			funkcija, sjeme, donjaGranica, gornjaGranica, brojTocaka, brojUzoraka, gornjaGranicaKoeficijenta,
			donjaGranicaKoeficijenta, redPolinoma,
			velicinaPopulacije, mutator, vjerojatnostMutacije, tau, selektor, brojParova,
			brojGeneracija
		};
		
		Ploca[] ploce = new Ploca[] {
			funkcijaPloca, sjemePloca, donjaGranicaPloca, gornjaGranicaPloca, brojTocakaPloca, 
			brojUzorakaPloca, gornjaGranicaKoeficijentaPloca,
			donjaGranicaKoeficijentaPloca, redPolinomaPloca,
			velicinaPopulacijePloca, mutatorPloca, vjerojatnostMutacijePloca, tauPloca, selektorPloca, 
			brojParovaPloca,
			brojGeneracijaPloca
		};
		
		for (int i = 0; i < elementi.length; i++) {
			JPanel ploca = new JPanel();
			ploca.setLayout(new BoxLayout(ploca, BoxLayout.Y_AXIS));
			ploca.add(elementi[i]);
			ploca.add(Box.createRigidArea(new Dimension(0, 10)));
			ploce[i].ploca = ploca;
			kontroleKontejner.add(ploca);
		}
		return kontroleKontejner;
	}
	
	protected void inicijalizirajElementeKontrola() {
		
		ActionListener sakrijOtkrij = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				boolean zastavica = mutator.vratiOdabrani().getActionCommand().equals(EKSPONENCIJALNA_MUTACIJA);
				tau.setEnabled(zastavica);
						
			}
		};
		
		vjerojatnostMutacije = new TekstualnaVrijednost("Vjerojatnost mutacije", "0.05");
		vjerojatnostMutacije.setEnabled(false);
		brojGeneracija = new TekstualnaVrijednost("Broj generacija", "400");
		velicinaPopulacije = new TekstualnaVrijednost("Broj vektora", "80");
		brojTocaka = new TekstualnaVrijednost("Broj točaka", "1000");
		gornjaGranica = new TekstualnaVrijednost("Do", "5");
		donjaGranica = new TekstualnaVrijednost("Od", "-5");
		sjeme = new TekstualnaVrijednost("Sjeme", "123456");
		funkcija = new TekstualnaVrijednost("Funkcija", DEGUIKonstante.FUNKCIJA);
		brojUzoraka = new TekstualnaVrijednost("Broj uzoraka", "100");
		redPolinoma = new TekstualnaVrijednost("Red polinoma", "5");
		gornjaGranicaKoeficijenta = new TekstualnaVrijednost("Gornja granica koeficijenta", "5");
		donjaGranicaKoeficijenta = new TekstualnaVrijednost("Donja granica koeficijenta", "-5");
		brojParova = new TekstualnaVrijednost("Broj parova", "1");
		mutator = new RadioGumbi("Vrsta mutacije", new Object[] {
			BINOMNA_MUTACIJA, EKSPONENCIJALNA_MUTACIJA, UNIFORMNA_MUTACIJA
		}, 0, new ActionListener[] {
			sakrijOtkrij, sakrijOtkrij, sakrijOtkrij, sakrijOtkrij
		});
		selektor = new RadioGumbi("Selekcija", new Object[] {
			NAJBOLJI_SELEKTOR, RANDOM_SELEKTOR
		});
		vjerojatnostMutacije = new TekstualnaVrijednost("Vjerojatnost mutacije", "0.9");
		tau = new TekstualnaVrijednost("Tau", "0.1");
		tau.setEnabled(false);
	}

	protected ChartPanel stvoriGraf() {
		XYSeries podatci = new XYSeries("");
		kolekcija = new XYSeriesCollection();
		kolekcija.addSeries(podatci);
		graf = ChartFactory.createXYLineChart(DEGUIKonstante.FUNKCIJA, "X", "Y", kolekcija, PlotOrientation.VERTICAL, false, false, false);
		nacrt = graf.getXYPlot();
		nacrt.setRenderer(1, new XYShapeRenderer());
		
		ChartPanel grafPloca = new ChartPanel(graf);
		
		return grafPloca;
	}

	public void nacrtajFunkciju(RealniKrajolik krajolik) {
		new NacrtajFunkciju(krajolik).execute();
	}
	
	protected void pokreniSimulaciju(JButton gumb) 
		throws UnknownFunctionException, UnparsableExpressionException {
		
		
		GASimulator simulator = new GASimulator();
		
		try {
			simulator.koristeciSjeme(Long.parseLong(sjeme.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Sjeme mora biti cijeli broj");
			return ;
		} 
		
		try {
			simulator.unutarGranica(
				Double.parseDouble(donjaGranica.vratiVrijednost()),
				Double.parseDouble(gornjaGranica.vratiVrijednost())
			);
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Granice moraju biti realan broj");
			return ;
		}
		
		
		try {
			simulator.saVelicinomPopulacije(Integer.parseInt(velicinaPopulacije.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Velicina populacije mora biti cijeli broj");
			return ;
		}
		
		String funkcijaString = funkcija.vratiVrijednost().toLowerCase();
		graf.setTitle(funkcijaString);
		
		simulator.koristeciFunkciju(
			new ExpressionBuilder(funkcijaString).withVariableNames("x")
			.withVariable("pi", Math.PI).withVariable("e", Math.E).build()
		);
		
		try {
			simulator.uzBrojGeneracija(Integer.parseInt(brojGeneracija.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Broj generacija mora biti cijeli broj");
			return ;
		}
		
		simulator.postaviGUI(this);
		
		simulator.addPropertyChangeListener(new ZaustaviSimulaciju(gumb));
		simulator.execute();
		
		this.simulator = simulator;
		
		
		gumb.setText(ZAUSTAVI);
				
	}

	public void iscrtajPopulaciju(List<Jedinka<RealniKrajolik>> jedinke, RealniKrajolik krajolik) {
		new NacrtajJedinke(jedinke, krajolik).execute();
	}
}
