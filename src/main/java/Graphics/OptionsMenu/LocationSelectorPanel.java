package Graphics.OptionsMenu;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataTypes.Location;
import DataTypes.RandomActivity;
import Resources.Data;

public class LocationSelectorPanel extends JPanel {

	private JScrollPane locationScrollPane = new JScrollPane();
	private ArrayList<LocationCheckBox> locationList = new ArrayList<LocationCheckBox>();
	private RandomActivity randomActivity;
	
	public LocationSelectorPanel(RandomActivity randomActivity){
		this.randomActivity = randomActivity;
		add(locationScrollPane);
		
		for(Location location : Data.getData().getLocationList()){
			LocationCheckBox locationCheckBox = new LocationCheckBox(location, randomActivity);
			locationScrollPane.add(locationCheckBox);
			locationList.add(locationCheckBox);
		}
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

