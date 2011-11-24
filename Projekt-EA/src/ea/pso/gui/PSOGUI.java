/**
 * 
 */
package ea.pso.gui;

import static ea.gui.GUIKonstante.ZAUSTAVI;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import ea.gui.DijeljenaPloca;
import ea.gui.GUI;

/**
 * @author Zlikavac32
 *
 */
public class PSOGUI extends GUI {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5336525365429544799L;

	public PSOGUI(String title) { super(title); }
	
	protected JPanel stvoriKontroleKontejner() {
		
		JPanel kontroleKontejnerVanjski = new JPanel(new BorderLayout());
		JPanel kontroleKontejner = new JPanel();
	
		kontroleKontejner.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		kontroleKontejner.setLayout(new BoxLayout(kontroleKontejner, BoxLayout.Y_AXIS));
		
		inicijalizirajElementeKontrola();
		
		DijeljenaPloca[] elementi = new DijeljenaPloca[] {

		};
		
		Ploca[] ploce = new Ploca[] {

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
		
//		ActionListener sakrijOtkrij = new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		};
		
	}

	protected ChartPanel stvoriGraf() {
		
		
		ChartPanel grafPloca = new ChartPanel(null);
		
		return grafPloca;
	}
	
	protected void pokreniSimulaciju(JButton gumb) 
		throws UnknownFunctionException, UnparsableExpressionException {
		
		
		
		gumb.setText(ZAUSTAVI);
				
	}
}
