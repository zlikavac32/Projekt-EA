/**
 * 
 */
package ea.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Marijan Šuflaj <msufflaj32@gmail.com>
 *
 */
public class Log extends JPanel {

	private final JScrollPane scrollPane;
	
	private final JTextArea logPodrucje;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -669348663388760575L;
	
	/**
	 * 
	 */
	public Log() {
		logPodrucje = new JTextArea("Bok");
		logPodrucje.setEditable(false);
		
		scrollPane = new JScrollPane(logPodrucje);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scrollPane);
	}

}
