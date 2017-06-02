package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataTypes.Location;
import DataTypes.Preset;
import Resources.Data;

public class LocationOptionsPanel extends JPanel{

	private JTextField addField;
	private JButton addButton;
	private JPanel addScrollPanel;
	private JScrollPane addScrollPane;
	private ArrayList<LocationPanel> locationPanelList = new ArrayList<LocationPanel>();
	private Location currentLocation;
	private LocationRightPanel rightPanel;
	
	public LocationOptionsPanel(){
		super();
		setLayout(new GridLayout(1,2));
		
		JLabel locationLabel = new JLabel("Locations");
		locationLabel.setFont(Resources.Fonts.HEADER_TITLE);
		
		JPanel locationAdder = new JPanel();
		locationAdder.setLayout(new BoxLayout(locationAdder, BoxLayout.Y_AXIS));
		
		JPanel adderPanel = new JPanel();
		adderPanel.setPreferredSize(Resources.Layout.DOUBLE_ADDER_PANEL);
		addField = new JTextField(15);
		addField.setForeground(Resources.Colors.TEXT);
		addField.setFont(Resources.Fonts.STANDARD);
		adderPanel.add(addField);
		
		addButton = new JButton("Add");
		addButton.setForeground(Resources.Colors.TEXT);
		addButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		adderPanel.add(addButton);
		
		locationAdder.setPreferredSize(Resources.Layout.DOUBLE_PANEL);
		
		addScrollPanel = new JPanel();
		addScrollPanel.setLayout(new BoxLayout(addScrollPanel, BoxLayout.Y_AXIS));
		addScrollPane = new JScrollPane(addScrollPanel);
		addScrollPane.setPreferredSize(Resources.Layout.DOUBLE_SCROLL_PANEL_ADDER);
		
		locationLabel.setAlignmentX(CENTER_ALIGNMENT);
		adderPanel.setAlignmentX(CENTER_ALIGNMENT);
		addScrollPane.setAlignmentX(CENTER_ALIGNMENT);
		addScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		locationAdder.add(locationLabel);
		locationAdder.add(Box.createVerticalGlue());
		locationAdder.add(adderPanel);
		locationAdder.add(Box.createVerticalGlue());
		locationAdder.add(addScrollPane);
		locationAdder.add(Box.createVerticalGlue());
		add(locationAdder);
		
		for(final Location location : Data.getData().getLocationList()){
			if(currentLocation == null){
				currentLocation = location;
			}
			LocationPanel locationPanel = new LocationPanel(location, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					selectLocation(location);
				}
			});
			addScrollPanel.add(locationPanel);
			locationPanelList.add(locationPanel);
		}
		
		rightPanel = new LocationRightPanel(currentLocation, this);
		add(rightPanel);
		
		
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(addField.getText().length() > 0){
					final Location newLocation = new Location(addField.getText());
					Data.getData().getLocationList().add(newLocation);
					LocationPanel locationPanel = new LocationPanel(newLocation, new ActionListener(){
						public void actionPerformed(ActionEvent e){
							selectLocation(newLocation);
						}
					});
					addScrollPanel.add(locationPanel);
					addField.setText("");
					validate();
				}
			}
		};
		
		addButton.addActionListener(addListener);
		addField.addActionListener(addListener);
		
		validate();
	}
	
	public void selectLocation(Location location){
		rightPanel.setCurrentLocation(location);
	}

	public void updateLocationList(Location currentLocation) {
		for(LocationPanel panel : locationPanelList){
			if(panel.getCurrentLocation().equals(currentLocation)){
				remove(panel);
			} else {
				panel.updateLocation();
			}
		}
		validate();
	}

	public void updateLocationList() {
		updateLocationList(null);
	}
	
}
