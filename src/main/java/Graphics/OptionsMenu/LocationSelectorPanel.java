package Graphics.OptionsMenu;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataTypes.Location;
import DataTypes.RandomActivity;
import Resources.Data;

public class LocationSelectorPanel extends JPanel {

	private JScrollPane locationScrollPane;
	private ArrayList<LocationCheckBox> locationList = new ArrayList<LocationCheckBox>();
	private RandomActivity randomActivity;
	
	private JPanel scrollPanel;
	private JTextField nameField;
	
	public LocationSelectorPanel(RandomActivity randomActivity){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		nameField = new JTextField(14);
		nameField.setPreferredSize(new Dimension(300,26));
		nameField.setMaximumSize(new Dimension(300,26));
		add(nameField);
		nameField.setText(randomActivity.getName());
		
		
		this.randomActivity = randomActivity;
		
		scrollPanel = new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		locationScrollPane = new JScrollPane(scrollPanel);
		add(locationScrollPane);
		
		for(Location location : Data.getData().getLocationList()){
			LocationCheckBox locationCheckBox = new LocationCheckBox(location, randomActivity);
			scrollPanel.add(locationCheckBox);
			locationList.add(locationCheckBox);
		}
		locationScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		
		validate();
	}
	
	
	
	public void updateRandomActivity(RandomActivity randomActivity){
		
		this.randomActivity = randomActivity;
		for(LocationCheckBox locationBoxes : locationList){
			locationBoxes.updateRandomActivity(randomActivity);
		}
	}
	
	private class LocationCheckBox extends JCheckBox{
		
		private Location location;
		private RandomActivity randomActivity;
		
		public LocationCheckBox(Location location, RandomActivity randomActivity){
			
			super(location.getName(), randomActivity.locationIsInLocationList(location));
			
			this.location = location;
			this.randomActivity = randomActivity;
			
		}
		
		public void updateRandomActivity(RandomActivity randomActivity){
			
			this.randomActivity = randomActivity;
			setSelected(randomActivity.locationIsInLocationList(location));
			
		}
	}
}

