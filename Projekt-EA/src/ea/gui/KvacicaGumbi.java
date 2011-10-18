/**
 * 
 */
package ea.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class KvacicaGumbi extends DijeljenaPloca {
	
	public KvacicaGumbi(String labela, Object[] opcije) { this(labela, opcije, new int[] {}, .25); }

	public KvacicaGumbi(String labela, Object[] opcije, int[] selected) { 
		this(labela, opcije, selected, .25); 
	}
	
	public KvacicaGumbi(String labela, Object[] opcije, int[] selected, double proporcije) {
		super(proporcije);
		
		JPanel labelaKontejner = new JPanel(new BorderLayout());
		labelaKontejner.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		labelaKontejner.add(new JLabel(labela), BorderLayout.NORTH);
		
		setLeftComponent(labelaKontejner);
		
		JPanel gumbiciPloca = new JPanel(new GridLayout(0, 1, 0, 3));
		
		for (int i = 0; i < opcije.length; i++) {
			JCheckBox gumbic = new JCheckBox(opcije[i].toString());
			gumbiciPloca.add(gumbic);
			for (int indeks : selected) {
				if (indeks == i) {
					gumbic.setSelected(true);
					break;
				}
			}
		}
		
		setRightComponent(gumbiciPloca);
		
	}
	
}
