/**
 * 
 */
package ea.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PadajuciIzbornik extends DijeljenaPloca {
	
	public PadajuciIzbornik(String labela, Object[] opcije) { this(labela, opcije, 0, .25); }

	public PadajuciIzbornik(String labela, Object[] opcije, int selected) { 
		this(labela, opcije, selected, .25); 
	}
	
	public PadajuciIzbornik(String labela, Object[] opcije, int selected, double proporcije) {
		super(proporcije);
		
		JPanel labelaKontejner = new JPanel(new BorderLayout());
		labelaKontejner.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		labelaKontejner.add(new JLabel(labela), BorderLayout.NORTH);
		
		setLeftComponent(labelaKontejner);
		
		JComboBox padajuciIzbornik = new JComboBox();
		
		for (int i = 0; i < opcije.length; i++) { padajuciIzbornik.addItem(opcije[i]); }
		
		padajuciIzbornik.setSelectedIndex(selected);
		
		setRightComponent(padajuciIzbornik);
		
	}
	
}
