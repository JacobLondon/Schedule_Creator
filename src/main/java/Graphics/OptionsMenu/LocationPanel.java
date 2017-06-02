package Graphics.OptionsMenu;

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
import javax.swing.JTextField;

import DataTypes.Location;

public class LocationPanel extends JPanel {

	private JLabel locationLabel;
	private Location location;
	
	public LocationPanel(Location location, ActionListener selectListener){
		super();
		setLayout(new GridLayout(1,2));
		
		this.location = location;
		locationLabel = new JLabel(location.getName());
		
		JButton useButton = new JButton("Select");
		
		locationLabel.setFont(Resources.Fonts.SMALL_TITLE);
		useButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		
		locationLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		add(Box.createHorizontalGlue());
		add(locationLabel);
		add(Box.createHorizontalGlue());
		add(useButton);
		//add(Box.createHorizontalGlue());
		
		useButton.addActionListener(selectListener);
		setMinimumSize(new Dimension(200,450));
		setPreferredSize(new Dimension(354,69));
	}	
	
	public Location getCurrentLocation(){
		return location;
	}

	public void updateLocation() {
		locationLabel.setText(location.getName());
		validate();
	}
}
