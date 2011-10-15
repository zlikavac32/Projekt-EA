/**
 * 
 */
package ea.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8526916806161376960L;

	/**
	 * Stvara graficko sucelje appleta.
	 * 
	 * @param title Naslov appleta
	 */
	public GUI(String title) {
		super(title);
	}

	/**
	 * Inicijalizira graficko sucelje. Dodaje komponente u applet.
	 */
	public void inicijaliziraj() {
		setSize(500, 500);
		setLayout(new BorderLayout(5, 5));
		
		add(new Graf(), BorderLayout.CENTER);
		add(new Komande(), BorderLayout.EAST);
		
		JPanel logPanel = new JPanel(new BorderLayout());
		logPanel.add(new Log(), BorderLayout.WEST);
		
		add(logPanel, BorderLayout.SOUTH);
	}

}
