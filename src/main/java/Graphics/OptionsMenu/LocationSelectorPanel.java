package Graphics.OptionsMenu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private RandomActivityOptionsPanel randomActivityOptionsPanel;
	
	public LocationSelectorPanel(RandomActivity randomActivity, RandomActivityOptionsPanel randomActivityOptionsPanel){
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
	
	class GetLocationSelectorInfo{
		
		public String name;
		public ArrayList<Location> location = new ArrayList<Location>();
		public GetLocationSelectorInfo(JTextField nameField, ArrayList<LocationCheckBox> locationList){
			this.name = nameField.getText();
			
			for(LocationCheckBox checkBox : locationList){
				if(checkBox.isSelected()){
					location.add(checkBox.location);
				}
			}
			
		}
	}
	
	public GetLocationSelectorInfo getLocationSelectorInfo(){
		return new GetLocationSelectorInfo(nameField, locationList);
	}
	
	public void setCurrentRandomActivity(RandomActivity randomActivity) {
		this.randomActivity = randomActivity;
		nameField.setText(randomActivity.getName());
		for(LocationCheckBox box : locationList){
			box.updateRandomActivity(randomActivity);
		}
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

