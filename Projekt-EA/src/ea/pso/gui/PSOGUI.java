/**
 * 
 */
package ea.pso.gui;

import static ea.gui.GUIKonstante.ZAUSTAVI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.VectorSeriesCollection;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.PSOSimulator;
import ea.gui.DijeljenaPloca;
import ea.gui.GUI;
import ea.gui.RadioGumbi;
import ea.gui.TekstualnaVrijednost;
import ea.util.RealniKrajolik;

import static ea.pso.gui.PSOGUIKonstante.*;

/**
 * @author Zlikavac32
 *
 */
public class PSOGUI extends GUI {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5336525365429544799L;
	
	private TekstualnaVrijednost funkcija;
	
	private TekstualnaVrijednost xOd;
	
	private TekstualnaVrijednost xDo;
	
	private TekstualnaVrijednost yDo;
	
	private TekstualnaVrijednost yOd;
	
	private TekstualnaVrijednost sjeme;
	
	private TekstualnaVrijednost brojCestica;
	
	private RadioGumbi brzinaKalkualtor;
	
	private TekstualnaVrijednost brojGeneracija;
	
	private RadioGumbi susjedstvo;
	
	private TekstualnaVrijednost c1;
	
	private TekstualnaVrijednost c2;
	
	private TekstualnaVrijednost donjaGranicaBrzinaX;
	
	private TekstualnaVrijednost gornjaGranicaBrzinaX;
	
	private TekstualnaVrijednost donjaGranicaBrzinaY;
	
	private TekstualnaVrijednost gornjaGranicaBrzinaY;
	
	private TekstualnaVrijednost inercija;
	
	private TekstualnaVrijednost faktorSmanjeInercije;
	
	private TekstualnaVrijednost maksInercija;
	
	private TekstualnaVrijednost brojKorakaInercija;
	
	private TekstualnaVrijednost minInercija;

	private TekstualnaVrijednost brojOkolnihJedinki;

	private TekstualnaVrijednost brojTocaka;

	private Ploca brojTocakaPloca = new Ploca();

	
	//Ploce
	
	private Ploca funkcijaPloca = new Ploca();
	
	private Ploca xOdPloca = new Ploca();
	
	private Ploca xDoPloca = new Ploca();
	
	private Ploca yDoPloca = new Ploca();
	
	private Ploca yOdPloca = new Ploca();
	
	private Ploca sjemePloca = new Ploca();
	
	private Ploca brojCesticaPloca = new Ploca();
	
	private Ploca brzinaKalkualtorPloca = new Ploca();
	
	private Ploca brojGeneracijaPloca = new Ploca();
	
	private Ploca susjedstvoPloca = new Ploca();
	
	private Ploca c1Ploca = new Ploca();
	
	private Ploca c2Ploca = new Ploca();
	
	private Ploca donjaGranicaBrzinaXPloca = new Ploca();
	
	private Ploca gornjaGranicaBrzinaXPloca = new Ploca();
	
	private Ploca donjaGranicaBrzinaYPloca = new Ploca();
	
	private Ploca gornjaGranicaBrzinaYPloca = new Ploca();
	
	private Ploca inercijaPloca = new Ploca();
	
	private Ploca faktorSmanjeInercijePloca = new Ploca();
	
	private Ploca maksInercijaPloca = new Ploca();
	
	private Ploca brojKorakaInercijaPloca = new Ploca();
	
	private Ploca minInercijaPloca = new Ploca();

	private Ploca brojOkolnihJedinkiPloca = new Ploca();

	private DefaultXYZDataset kolekcija;

	private JFreeChart graf;

	private XYPlot nacrt;
	

	
	private class NacrtajFunkciju implements Runnable {
		
		RealniKrajolik krajolik;
		
		NacrtajFunkciju(RealniKrajolik krajolik) {
			this.krajolik = krajolik;
		}

		@Override
		public void run() {

			int brojElemenata = Integer.parseInt(brojTocaka.vratiVrijednost());
			if (brojElemenata < 1) { throw new IllegalArgumentException("Broj tocaka mora biti veci od 0"); }
			double[][] podatci = new double[3][(brojElemenata + 1) * (brojElemenata + 1)];
			double doljeX = krajolik.vratiDonjuGranicu()[0];
			double goreX = krajolik.vratiGornjuGranicu()[0];
			double doljeY = krajolik.vratiDonjuGranicu()[1];
			double goreY = krajolik.vratiGornjuGranicu()[1];
			double korakX = (goreX - doljeX) / brojElemenata;
			double xPomak = doljeX;
			int indeksElementa = 0;
			for (int i = 0; i <= brojElemenata; i++) {
				double korakY = (goreY - doljeY) / brojElemenata;
				double yPomak = doljeY;
				for (int j = 0; j <= brojElemenata; j++) {
					podatci[0][indeksElementa] = xPomak;
					podatci[1][indeksElementa] = yPomak;
					podatci[2][indeksElementa++] = krajolik.racunajVrijednost(new double[] {
						xPomak, yPomak
					});
					yPomak += korakY;
				}
				xPomak += korakX;
			}
			
			kolekcija.addSeries("Funkcija", podatci);
		}
	}
		
	public PSOGUI(String title) { super(title); }
	
	protected JPanel stvoriKontroleKontejner() {
		
		JPanel kontroleKontejnerVanjski = new JPanel(new BorderLayout());
		JPanel kontroleKontejner = new JPanel();
	
		kontroleKontejner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		kontroleKontejner.setLayout(new BoxLayout(kontroleKontejner, BoxLayout.Y_AXIS));
		
		inicijalizirajElementeKontrola();
		
		DijeljenaPloca[] elementi = new DijeljenaPloca[] {
			funkcija, sjeme, xOd, xDo, yDo, yOd, brojTocaka, brojCestica, 
			brzinaKalkualtor, c1, c2, 
			inercija, faktorSmanjeInercije, maksInercija, minInercija, brojKorakaInercija, 
			susjedstvo, brojOkolnihJedinki,
			donjaGranicaBrzinaX, gornjaGranicaBrzinaX, donjaGranicaBrzinaY, gornjaGranicaBrzinaY, 
			brojGeneracija
		};
		
		Ploca[] ploce = new Ploca[] {
			funkcijaPloca, sjemePloca, xOdPloca, xDoPloca, yDoPloca, yOdPloca, brojTocakaPloca, brojCesticaPloca, 
			brzinaKalkualtorPloca, c1Ploca, c2Ploca, 
			inercijaPloca, faktorSmanjeInercijePloca, maksInercijaPloca, minInercijaPloca, brojKorakaInercijaPloca,
			susjedstvoPloca, brojOkolnihJedinkiPloca,
			donjaGranicaBrzinaXPloca, gornjaGranicaBrzinaXPloca, donjaGranicaBrzinaYPloca, gornjaGranicaBrzinaYPloca, 
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
		
		kontroleKontejnerVanjski.add(kontroleKontejner, BorderLayout.NORTH);
		
		return kontroleKontejnerVanjski;
	}
	
	protected void inicijalizirajElementeKontrola() {
		
		ActionListener sakrijOtkrij = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String akcija = brzinaKalkualtor.vratiOdabrani().getActionCommand();
				boolean flag = akcija.equals(INERCIJA_BRZINA_KALKULATOR) || 
					akcija.equals(DINAMICKA_INERCIJA_BRZINA_KALKUALTOR);
				inercija.setEnabled(flag);
				flag = akcija.equals(DINAMICKA_INERCIJA_BRZINA_KALKUALTOR);
				faktorSmanjeInercije.setEnabled(flag);
				flag = akcija.equals(DINAMICKA_OGRANICAVAJUCA_INERCIJA_KALKUALTOR);
				maksInercija.setEnabled(flag);
				minInercija.setEnabled(flag);
				brojKorakaInercija.setEnabled(flag);
				flag = susjedstvo.vratiOdabrani().getActionCommand().equals(LOKALNO_SUSJEDSTVO);
				brojOkolnihJedinki.setEnabled(flag);
			}
		};
		
		funkcija = new TekstualnaVrijednost("Funkcija", FUNKCIJA);
		xOd = new TekstualnaVrijednost("X Od", "-10");
		xDo = new TekstualnaVrijednost("X Do", "10");
		yOd = new TekstualnaVrijednost("Y Od", "-10");
		yDo = new TekstualnaVrijednost("Y Do", "10");
		brojTocaka  = new TekstualnaVrijednost("Broj tocaka", "1000");
		sjeme = new TekstualnaVrijednost("Sjeme", "123456");
		brojCestica = new TekstualnaVrijednost("Broj cestica", "15");
		brzinaKalkualtor = new RadioGumbi("Brzina kalkulator", new String[] {
			STANDARDNI_BRZINA_KALKULATOR, INERCIJA_BRZINA_KALKULATOR, DINAMICKA_INERCIJA_BRZINA_KALKUALTOR,
			DINAMICKA_OGRANICAVAJUCA_INERCIJA_KALKUALTOR, OGRANICAVAJUCA_BRZINA_KALKULATOR
		}, 0, new ActionListener[] {
			sakrijOtkrij, sakrijOtkrij, sakrijOtkrij, sakrijOtkrij, sakrijOtkrij
		});
		brojGeneracija = new TekstualnaVrijednost("Broj generacija", "250");
		susjedstvo = new RadioGumbi("Susjedstvo", new String[] {
			GLOBALNO_SUSJEDSTVO, LOKALNO_SUSJEDSTVO
		}, 0, new ActionListener[] {
			sakrijOtkrij, sakrijOtkrij
		});
		c1 = new TekstualnaVrijednost("c1", "2");
		c2 = new TekstualnaVrijednost("c2", "2.5");
		donjaGranicaBrzinaX = new TekstualnaVrijednost("Donja granica brzina X", "-8");
		gornjaGranicaBrzinaX = new TekstualnaVrijednost("Gornja granica brzina X", "8");
		donjaGranicaBrzinaY = new TekstualnaVrijednost("Donja granica brzina Y", "-8");
		gornjaGranicaBrzinaY = new TekstualnaVrijednost("Gornja granica brzina Y", "8");
		inercija = new TekstualnaVrijednost("Inercija", "0.9");
		inercija.setEnabled(false);
		faktorSmanjeInercije = new TekstualnaVrijednost("Faktor smanjenja inercije", "0.9");
		faktorSmanjeInercije.setEnabled(false);
		maksInercija = new TekstualnaVrijednost("Maks inercija", "0.9");
		maksInercija.setEnabled(false);
		minInercija = new TekstualnaVrijednost("Min inercija", "0.2");
		minInercija.setEnabled(false);
		brojKorakaInercija = new TekstualnaVrijednost("Broj koraka inercija", "150");
		brojKorakaInercija.setEnabled(false);
		brojOkolnihJedinki = new TekstualnaVrijednost("Broj okolnih jedinki", "5");
		brojOkolnihJedinki.setEnabled(false);
		
	}

	protected ChartPanel stvoriGraf() {
		kolekcija = new DefaultXYZDataset();
		graf = ChartFactory.createXYLineChart(FUNKCIJA, "", "", kolekcija, PlotOrientation.VERTICAL, true, false, false);
		nacrt = graf.getXYPlot();
		nacrt.setRenderer(0, new XYBlockRenderer());
		
		nacrt.setRenderer(1, new XYShapeRenderer());
		
		nacrt.setDataset(2, new VectorSeriesCollection());
		nacrt.setRenderer(2, new VectorRenderer());
		
		ChartPanel grafPloca = new ChartPanel(graf);
		
		return grafPloca;
	}
	
	protected void pokreniSimulaciju(JButton gumb) 
		throws UnknownFunctionException, UnparsableExpressionException {
		
		PSOSimulator simulator = new PSOSimulator();
		
		simulator.postaviGUI(this);
		
		super.simulator = simulator;
		simulator.execute();
		
		gumb.setText(ZAUSTAVI);
				
	}

	public void nacrtajFunkciju(RealniKrajolik krajolik) {
		SwingUtilities.invokeLater(new NacrtajFunkciju(krajolik));
	}
}
