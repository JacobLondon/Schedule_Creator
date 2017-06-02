package Graphics.OptionsMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataTypes.Location;
import Resources.Data;

public class LocationRightPanel extends JPanel {
	private JColorChooser color;
	private JTextField nameField;
	private JButton save;
	private JButton delete;
	private Location currentLocation;
	private Data data = Data.getData();
	private ActionListener saveListener;
	private ActionListener deleteListener;
	private LocationOptionsPanel locationOptionsPanel;
	
	public LocationRightPanel(Location currentLocation, LocationOptionsPanel locationOptionsPanel){
		this.currentLocation = currentLocation;
		this.locationOptionsPanel = locationOptionsPanel;
		color = new JColorChooser();
		nameField = new JTextField(14);
		save = new JButton("Save");
		delete = new JButton("Delete");
		
		setCurrentLocation(currentLocation);
		
		add(nameField);
		add(color);
		add(save);
		add(delete);
		
		
	}

	public void setCurrentLocation(Location location) {
		save.removeActionListener(saveListener);
		delete.removeActionListener(deleteListener);

		currentLocation = location;
		color.setColor(currentLocation.getLocationColor().getColor());
		nameField.setText(currentLocation.getName());
		
		saveListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentLocation.setName(nameField.getText());
				currentLocation.getLocationColor().setColor(color.getColor());
				locationOptionsPanel.updateLocationList();
			}
		};
		deleteListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				data.getLocationList().remove(data.getLocationList().indexOf(currentLocation));
				data.writeDataToDisk();
				locationOptionsPanel.updateLocationList(currentLocation);
			}
		};
		
		save.addActionListener(saveListener);
		delete.addActionListener(deleteListener);
	}
}
