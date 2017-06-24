package Graphics.OptionsMenu.RandomActivitiesTab;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataTypes.Location;
import DataTypes.RandomActivity;

public class RandomActivityPanel extends JPanel {

	private JLabel randomActivityLabel;
	private RandomActivity randomActivity;
	
	public RandomActivityPanel(RandomActivity randomActivity, ActionListener selectListener) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.randomActivity = randomActivity;
		
		randomActivityLabel = new JLabel(randomActivity.getName());
		
		JButton selectButton = new JButton("Select");
		
		randomActivityLabel.setFont(Resources.Fonts.SMALL_TITLE);
		selectButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		
		randomActivityLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		add(Box.createHorizontalGlue());
		add(randomActivityLabel);
		add(Box.createHorizontalGlue());
		add(selectButton);
		//add(Box.createHorizontalGlue());
		
		selectButton.addActionListener(selectListener);
		setMinimumSize(new Dimension(100,150));
		setPreferredSize(new Dimension(100,150));
	}
	
	public RandomActivity getCurrentRandomActivity(){
		return randomActivity;
	}
	
	public void updateRandomActivity() {
		randomActivityLabel.setText(randomActivity.getName());
		validate();
	}
}
