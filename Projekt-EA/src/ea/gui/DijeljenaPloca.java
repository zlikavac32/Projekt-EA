/**
 * 
 */
package ea.gui;

import javax.swing.JSplitPane;

public class DijeljenaPloca extends JSplitPane {
	
	public DijeljenaPloca() { this(.25); }
	
	public DijeljenaPloca(double proporcije) {
		setDividerSize(0);
		setResizeWeight(proporcije);
		setBorder(null);
	}

}
