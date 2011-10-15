/**
 * 
 */
package ea;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import ea.gui.GUI;

/**
 * Ulaz u nasu aplikaciju.
 * 
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Aplikacija extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4803571029460862673L;
	
	/**
	 * Stvara graficko sucelje.
	 * 
	 * @see java.applet.Applet#init()
	 */
	@Override
	public void init() {
		super.init();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GUI gui = new GUI("Evolucijski algoritmi");
				gui.inicijaliziraj();
				gui.setVisible(true);
			}
		});
	}

}
