package Graphics.OptionsMenu.AboutTab;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutPanel extends JPanel {

	public AboutPanel(){
		
		JLabel aboutLabel = new JLabel("<html>Schedule Creator application<br/>Version: 1.0<br/>Developed by Jacob London and Bart Tam<html/>");
		aboutLabel.setFont(Resources.Fonts.HEADER_TITLE);
		add(aboutLabel);
	}
	
}
