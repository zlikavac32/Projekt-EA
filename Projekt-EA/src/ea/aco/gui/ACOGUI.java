/**
 * 
 */
package ea.aco.gui;

import static ea.gui.GUIKonstante.ZAUSTAVI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.ACOSimulator;
import ea.aco.Grad;
import ea.aco.Par;
import ea.gui.DijeljenaPloca;
import ea.gui.GUI;
import ea.gui.RadioGumbi;
import ea.gui.TekstPodrucje;
import ea.gui.TekstualnaVrijednost;

import static ea.aco.gui.ACOGUIKonstante.*;

/**
 * @author Zlikavac32
 *
 */
public class ACOGUI extends GUI {


	private TekstPodrucje gradovi;
	
	private TekstualnaVrijednost brojGeneracija;
	
	private TekstualnaVrijednost sjeme;

	private RadioGumbi algoritam;
	
	private TekstualnaVrijednost brojMrava;
	
	private TekstualnaVrijednost konstantaIsparavanja;
	
	private TekstualnaVrijednost alfa;
	
	private TekstualnaVrijednost beta;

	private TekstualnaVrijednost brojMravaAzurira;
	
	
	private Ploca gradoviPloca = new Ploca();
	
	private Ploca brojMravaAzuriraPloca = new Ploca();
	
	private Ploca brojGeneracijaPloca = new Ploca();
	
	private Ploca sjemePloca = new Ploca();

	private Ploca algoritamPloca = new Ploca();

	private Ploca brojMravaPloca = new Ploca();

	private Ploca konstantaIsparavanjaPloca = new Ploca();

	private Ploca alfaPloca = new Ploca();

	private Ploca betaPloca = new Ploca();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8465945028324200176L;

	private static XYSeriesCollection putanjeKolekcija;
	
	private static XYSeriesCollection najboljaPutanjaKolekcija;

	protected static XYSeriesCollection kolekcija;

	protected static JFreeChart graf;

	protected static XYPlot nacrt;

	public ACOGUI(String title) { super(title); }
	
	
	protected JPanel stvoriKontroleKontejner() {
		
		JPanel kontroleKontejnerVanjski = new JPanel(new BorderLayout());
		JPanel kontroleKontejner = new JPanel();
	
		kontroleKontejner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		kontroleKontejner.setLayout(new BoxLayout(kontroleKontejner, BoxLayout.Y_AXIS));
		
		inicijalizirajElementeKontrola();
		
		DijeljenaPloca[] elementi = new DijeljenaPloca[] {
			gradovi, sjeme, brojMrava, brojMravaAzurira, 
			algoritam, konstantaIsparavanja, 
			alfa, beta, brojGeneracija
		};
		
		Ploca[] ploce = new Ploca[] {
			gradoviPloca, sjemePloca, brojMravaPloca, brojMravaAzuriraPloca, 
			algoritamPloca, konstantaIsparavanjaPloca, 
			alfaPloca, betaPloca, brojGeneracijaPloca
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
				
				boolean flag = algoritam.vratiOdabrani().getActionCommand().equals(ANT_SYSTEM);
				beta.setEnabled(flag);
				flag = algoritam.vratiOdabrani().getActionCommand().equals(SIMPLE_ACO);
				brojMravaAzurira.setEnabled(flag);
			}
		};
		
		brojMravaAzurira = new TekstualnaVrijednost(BROJ_MRAVA_AZURIRA, "2");
		brojMrava = new TekstualnaVrijednost(BROJ_MRAVA, "50");	
		brojGeneracija = new TekstualnaVrijednost(BROJ_GENERACIJA, "20");
		sjeme = new TekstualnaVrijednost(SJEME, "123456");
		algoritam = new RadioGumbi(ALGORITAM, new String[] {
			SIMPLE_ACO, ANT_SYSTEM
		}, 0, new ActionListener[] { 
			sakrijOtkrij, sakrijOtkrij
		});
		konstantaIsparavanja = new TekstualnaVrijednost(KONSTANTA_ISPARAVANJA, "0.5");
		alfa = new TekstualnaVrijednost(ALFA, "1");
		beta = new TekstualnaVrijednost(BETA, "3");
		beta.setEnabled(false);
		gradovi = new TekstPodrucje(GRADOVI, 
			"0, 0, A\n"
			+ "1, 5, B\n"
			+ "3, 8, C\n"
			+ "10, 3, D\n"
			+ "4, 7, E\n"
			+ "10, 20, F\n"
			+ "11, 8, G\n"
			+ "7, 13, H\n"
			+ "8, 8, I\n"
			+ "9, 11, J\n"
			+ "6, 15, K\n"
			+ "1, 3, L\n"
		, 10);
		
	}

	protected ChartPanel stvoriGraf() {
		XYSeries podatci = new XYSeries("Gradovi");
		kolekcija = new XYSeriesCollection();
		kolekcija.addSeries(podatci);
		graf = ChartFactory.createXYLineChart("Gradovi", "", "", null, PlotOrientation.VERTICAL, true, false, false);
		nacrt = graf.getXYPlot();
		nacrt.setRenderer(0, new XYShapeRenderer());
		
		java.awt.geom.Ellipse2D.Double kruzici = new java.awt.geom.Ellipse2D.Double(-4D, -4D, 8D, 8D);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setSeriesShape(0, kruzici);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesFillPaint(0, Color.YELLOW);
        renderer.setSeriesOutlinePaint(0, Color.GRAY);
        

		podatci = new XYSeries(PUTANJA);
		putanjeKolekcija = new XYSeriesCollection();
		putanjeKolekcija.addSeries(podatci);
        
        nacrt.setRenderer(1, renderer);

		kruzici = new java.awt.geom.Ellipse2D.Double(-4D, -4D, 8D, 8D);
        renderer = new XYLineAndShapeRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setSeriesShape(0, kruzici);
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesFillPaint(0, Color.YELLOW);
        renderer.setSeriesOutlinePaint(0, Color.GRAY);
        

		podatci = new XYSeries(NAJBOLJA_PUTANJA);
		najboljaPutanjaKolekcija = new XYSeriesCollection();
		najboljaPutanjaKolekcija.addSeries(podatci);
        
        nacrt.setRenderer(2, renderer);
		
		ChartPanel grafPloca = new ChartPanel(graf);
		
		return grafPloca;
	}
	
	protected void pokreniSimulaciju(JButton gumb) 
		throws UnknownFunctionException, UnparsableExpressionException {
		
		ACOSimulator simulator = new ACOSimulator();

		try {
			simulator.koristeciSjeme(Long.parseLong(sjeme.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Sjeme mora biti cijeli broj");
			return ;
		} 
		
		try {
			simulator.koristeciBrojMrava(Integer.parseInt(brojMrava.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Broj mrava mora biti cijeli broj");
			return ;
		} 
		
		
		List<Par<String, Par<Integer, Integer>>> gradoviLista = new ArrayList<Par<String, Par<Integer, Integer>>>();
		String[] linije = gradovi.vratiVrijednost().split("\n");
		for (int i = 0; i < linije.length; i++) {
			String dijelovi[] = linije[i].split(",");
			int x = Integer.parseInt(dijelovi[0].trim());
			int y = Integer.parseInt(dijelovi[1].trim());
			Par<String, Par<Integer, Integer>> grad = new Par<String, Par<Integer, Integer>>(
				dijelovi.length > 2 ? dijelovi[2].trim() : Integer.toString(i + 1), 
				new Par<Integer, Integer>(x, y)
			);
			gradoviLista.add(grad);
		}
		
		simulator.koristeciGradove(gradoviLista);
		
		
		int odabraniAlgoritam = algoritam.vratiOdabrani().getActionCommand().equals(SIMPLE_ACO) ?
			ACOSimulator.SIMPLE_ACO_ALGORITAM : ACOSimulator.ANT_SYSTEM_ALGORITAM;
		
		simulator.koristeciAlgoritam(odabraniAlgoritam);

		try {
			simulator.koristeciAlfa(Double.parseDouble(alfa.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Alfa mora biti broj");
			return ;
		} 
		
		if (odabraniAlgoritam == ACOSimulator.ANT_SYSTEM_ALGORITAM) {
			try {
				simulator.koristeciBeta(Double.parseDouble(beta.vratiVrijednost()));
			} catch (NumberFormatException e) { 
				zapisiUZapisnik("Beta mora biti broj");
				return ;
			} 
		} else {

			try {
				simulator.koristeciBrojMravaZaAzuriranje(Integer.parseInt(brojMravaAzurira.vratiVrijednost()));
			} catch (NumberFormatException e) { 
				zapisiUZapisnik("Broj mrava za azuriranje mora biti cijeli broj");
				return ;
			} 
		}
		
		try {
			simulator.koristeciKonstantuIsparavanja(Double.parseDouble(konstantaIsparavanja.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Konstanta isparavanja mora biti broj");
			return ;
		} 
		
		try {
			simulator.uzBrojGeneracija(Integer.parseInt(brojGeneracija.vratiVrijednost()));
		} catch (NumberFormatException e) { 
			zapisiUZapisnik("Broj generacija mora biti cijeli broj");
			return ;
		}
		
		simulator.addPropertyChangeListener(new ZaustaviSimulaciju(gumb));
		simulator.execute();
		
		this.simulator = simulator;
		
		
		gumb.setText(ZAUSTAVI);
				
	}
	
	public static void iscrtajPutanju(Grad[] najbolji, Grad[] gradovi) {
		XYSeries podatci = new XYSeries(PUTANJA, false, true);
		Grad prvi = gradovi[0];
		for (Grad grad : gradovi) { podatci.add(grad.x, grad.y); }
		podatci.add(prvi.x, prvi.y); 
		putanjeKolekcija.removeSeries(0);
		putanjeKolekcija.addSeries(podatci);
		nacrt.setDataset(1, putanjeKolekcija);

		podatci = new XYSeries(NAJBOLJA_PUTANJA, false, true);
		prvi = najbolji[0];
		for (Grad grad : najbolji) { podatci.add(grad.x, grad.y); }
		podatci.add(prvi.x, prvi.y); 
		najboljaPutanjaKolekcija.removeSeries(0);
		najboljaPutanjaKolekcija.addSeries(podatci);
		nacrt.setDataset(2, najboljaPutanjaKolekcija);
	}


	public static void iscrtajGradove(Grad[] gradovi) {
		XYSeries podatci = new XYSeries("Gradovi");
		XYShapeRenderer renderer = (XYShapeRenderer) nacrt.getRenderer(0);
		renderer.removeAnnotations();
		for (Grad grad : gradovi) { 
			podatci.add(grad.x, grad.y); 
			renderer.addAnnotation(new XYTextAnnotation(grad.ime, grad.x, grad.y));
		}
		nacrt.setRenderer(0, null);
		kolekcija.removeSeries(0);
		kolekcija.addSeries(podatci);
		nacrt.setRenderer(0, renderer);
		nacrt.setDataset(kolekcija);
	}
}
