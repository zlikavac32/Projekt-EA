package ea.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -3200550997106801733L;
	
	/**
	 * Stvara graficko sucelje i postavlja naslov.
	 * 
	 * @param title 
	 */
	
	public GUI(String title) {  super(title); }
	
	/**
	 * Dodaje komponente.
	 */
	public void inicijaliziraj() { 
		setLayout(new BorderLayout());
		setSize(new Dimension(800, 750));
		
		JSplitPane grafZapisnikDijeljeniProzorcic = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JTextArea zapisnikPodrucje = new JTextArea();
		
		
		grafZapisnikDijeljeniProzorcic.setTopComponent(new JPanel());
		grafZapisnikDijeljeniProzorcic.setBottomComponent(zapisnikPodrucje);
		grafZapisnikDijeljeniProzorcic.setResizeWeight(0.8);
		add(grafZapisnikDijeljeniProzorcic, BorderLayout.CENTER);
		
		
		JPanel komandeDesnaStranaGlavniPanel = new JPanel(new BorderLayout());
		komandeDesnaStranaGlavniPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel komandeKontejner = new JPanel();
		komandeKontejner.setLayout(new BoxLayout(komandeKontejner, BoxLayout.Y_AXIS));
		
		komandeKontejner.add(new PadajuciIzbornik("Algoritam", new Object[] {
			"Genetski"	
		}));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Funkcija", "x^2 * sin(x)"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new RadioGumbi("Trazi", new Object[] {
			"Minimum", "Maksimum"	
		}, 0));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Velicina populacije", "20"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new KvacicaGumbi("Trazi", new Object[] {
			"Mutacija", "Rekombinacija"	
		}));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Faktor"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new RadioGumbi("Vrsta", new Object[] {
			"Okret bita"	
		}, 0));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Broj sjecista"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new RadioGumbi("Vrsta", new Object[] {
			"Preklapajuca", "Nepreklapajuca"	
		}, 0));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Broj djece"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new RadioGumbi("Selekcija", new Object[] {
			"Uniformna", "Proporcionalna", "Linearna", "Turnirska", "Odbacivanje"	
		}, 0));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Broj krugova"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new RadioGumbi("Simuliraj", new Object[] {
			"X generacija", "Stabilnost"	
		}, 0));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		komandeKontejner.add(new TekstualnaVrijednost("Broj generacija", "1000"));
		komandeKontejner.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel akcijeKontrolePloca = new JPanel(new BorderLayout());
		
		JPanel akcijeKontrolePlocaUnutarnja = new JPanel(new GridLayout(1, 0, 5, 0));
		akcijeKontrolePlocaUnutarnja.add(new JButton("Pokreni"));
		
		akcijeKontrolePloca.add(akcijeKontrolePlocaUnutarnja, BorderLayout.EAST);

	
		
		komandeDesnaStranaGlavniPanel.add(komandeKontejner, BorderLayout.NORTH);
		komandeDesnaStranaGlavniPanel.add(Box.createRigidArea(new Dimension(280, 0)));
		komandeDesnaStranaGlavniPanel.add(akcijeKontrolePloca, BorderLayout.SOUTH);
		
		add(komandeDesnaStranaGlavniPanel, BorderLayout.EAST);	
	}
	
}
