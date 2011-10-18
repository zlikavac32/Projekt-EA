/**
 * 
 */
package ea.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioGumbi extends DijeljenaPloca {
	
	private ButtonGroup grupaGumbica = new ButtonGroup();

	public RadioGumbi(String labela, Object[] opcije) { this(labela, opcije, -1, .25); }

	public RadioGumbi(String labela, Object[] opcije, int selected) { 
		this(labela, opcije, selected, .25); 
	}
	
	public RadioGumbi(String labela, Object[] opcije, int selected, double proporcije) {
		super(proporcije);
		
		JPanel labelaKontejner = new JPanel(new BorderLayout());
		labelaKontejner.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		labelaKontejner.add(new JLabel(labela), BorderLayout.NORTH);
		
		setLeftComponent(labelaKontejner);
		
		JPanel gumbiciPloca = new JPanel(new GridLayout(0, 1, 0, 3));
		
		for (int i = 0; i < opcije.length; i++) {
			JRadioButton gumbic = new JRadioButton(opcije[i].toString());
			grupaGumbica.add(gumbic);
			gumbiciPloca.add(gumbic);
			if (i == selected) { grupaGumbica.setSelected(gumbic.getModel(), true); }
		}
		
		setRightComponent(gumbiciPloca);
		
	}
	
}
