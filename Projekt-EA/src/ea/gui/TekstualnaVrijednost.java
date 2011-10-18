/**
 * 
 */
package ea.gui;


import javax.swing.JLabel;
import javax.swing.JTextField;

public class TekstualnaVrijednost extends DijeljenaPloca {

	private JTextField poljeZaUnos = new JTextField();
	
	public TekstualnaVrijednost(String labela) { this(labela, "", .25); }
	
	public TekstualnaVrijednost(String labela, String pocetnaVrijednost) { 
		this(labela, pocetnaVrijednost, .25); 
	}
	
	public TekstualnaVrijednost(String labela, String pocetnaVrijednost, double proporcije) {
		super(proporcije);
		
		setLeftComponent(new JLabel(labela));
		poljeZaUnos.setText(pocetnaVrijednost);
		setRightComponent(poljeZaUnos);
	}
	
}
