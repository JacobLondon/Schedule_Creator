package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Graphics.MainMenu.MenuWindow;
import Graphics.OptionsMenu.AboutTab.AboutPanel;
import Graphics.OptionsMenu.CampInfoTab.CampInfoPanel;
import Graphics.OptionsMenu.LocationsTab.LocationOptionsPanel;
import Graphics.OptionsMenu.PlannedActivitiesTab.PlannedActivityOptionsPanel;
import Graphics.OptionsMenu.PresetsTab.PresetOptionsPanel;
import Graphics.OptionsMenu.RandomActivitiesTab.RandomActivityOptionsPanel;
import Resources.Data;

public class OptionsMenu extends JPanel {
	
	private static final String CAMP = "Camp Info";
	private static final String LOCATION = "Location";
	private static final String RAND_ACT = "Random Activites";
	private static final String PLAND_ACT = "Planned Activites";
	private static final String PRESET = "Presets";
	private static final String ABOUT = "About";

	private JToolBar toolbar = new JToolBar();
	
	private CampInfoPanel campPanel = new CampInfoPanel();
	private LocationOptionsPanel locationPanel = new LocationOptionsPanel();
	private RandomActivityOptionsPanel randActivitiesPanel = new RandomActivityOptionsPanel();
	private PlannedActivityOptionsPanel plannedActivitiesPanel = new PlannedActivityOptionsPanel();
	private PresetOptionsPanel presetsPanel = new PresetOptionsPanel();
	private AboutPanel aboutPanel = new AboutPanel();
	
	private JButton menuButton;
	private JButton campButton;
	private JButton locationButton;
	private JButton randActivitiesButton;
	private JButton plannedActivitiesButton;
	private JButton presetsButton;
	private JButton aboutButton;
	
	private CardLayout layout;
	private JPanel cardPanel;
		
	public OptionsMenu(final MenuWindow window){
		
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		toolbar.setPreferredSize(Resources.Layout.TOOLBAR);
		toolbar.setFloatable(false);
		
		layout = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(layout);
		
		cardPanel.add(campPanel, CAMP);
		cardPanel.add(locationPanel, LOCATION);
		cardPanel.add(randActivitiesPanel, RAND_ACT);
		cardPanel.add(plannedActivitiesPanel, PLAND_ACT);
		cardPanel.add(presetsPanel, PRESET);
		cardPanel.add(aboutPanel, ABOUT);
		
		add(cardPanel, BorderLayout.CENTER);
		
		menuButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				window.openMainMenu();
				Data.getData().writeDataToDisk();
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		menuButton.setText("Menu");
		menuButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		campButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(CAMP);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		campButton.setText(CAMP);
		campButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		locationButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(LOCATION);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		locationButton.setText(LOCATION);
		locationButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		randActivitiesButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(RAND_ACT);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		randActivitiesButton.setText(RAND_ACT);
		randActivitiesButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		plannedActivitiesButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(PLAND_ACT);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		plannedActivitiesButton.setText(PLAND_ACT);
		plannedActivitiesButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		presetsButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(PRESET);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		presetsButton.setText(PRESET);
		presetsButton.setFont(Resources.Fonts.SMALL_TITLE);
		
		aboutButton = toolbar.add(new Action(){

			public void actionPerformed(ActionEvent arg0) {
				setPanel(ABOUT);
			}

			public void addPropertyChangeListener(PropertyChangeListener arg0) {}

			public Object getValue(String arg0) {
				return null;
			}

			public boolean isEnabled() {
				return true;
			}

			public void putValue(String arg0, Object arg1) {}

			public void removePropertyChangeListener(PropertyChangeListener arg0) {}

			public void setEnabled(boolean arg0) {}
			
		});
		aboutButton.setText(ABOUT);
		aboutButton.setFont(Resources.Fonts.SMALL_TITLE);
		
	}

	private void setPanel(String panel) {
		layout.show(cardPanel, panel);
	}

}
