package Graphics.OptionsMenu.PlannedActivitiesTab;

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

import DataTypes.PlannedActivity;
import Resources.Data;

public class PlannedActivityOptionsPanel extends JPanel {

	private JTextField addField;
	private JButton addButton;
	private JPanel addScrollPanel;
	private JScrollPane addScrollPane;
	private ArrayList<PlannedActivityPanel> plannedActivityPanelList = new ArrayList<PlannedActivityPanel>();
	private PlannedActivityRightPanel rightPanel;
	
	public PlannedActivityOptionsPanel(){
		
		setLayout(new GridLayout(1,2));
		
		// panel creation
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(Resources.Layout.DOUBLE_PANEL);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		// left panel
		JLabel plannedActivityLabel = new JLabel("Planned Activities");
		JPanel addPanel = new JPanel();
		addField = new JTextField(15);
		addButton = new JButton("Add");
		addScrollPanel = new JPanel();
		addScrollPanel.setLayout(new BoxLayout(addScrollPanel, BoxLayout.Y_AXIS));
		addScrollPane = new JScrollPane(addScrollPanel);
		
		addPanel.setPreferredSize(Resources.Layout.DOUBLE_ADDER_PANEL);
		addScrollPane.setPreferredSize(Resources.Layout.DOUBLE_SCROLL_PANEL_ADDER);
		
		plannedActivityLabel.setFont(Resources.Fonts.HEADER_TITLE);
		addField.setFont(Resources.Fonts.STANDARD);
		addButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		addScrollPane.setAlignmentX(CENTER_ALIGNMENT);
		addScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		// add to panel
		addPanel.add(addField);
		addPanel.add(addButton);
		
		leftPanel.add(plannedActivityLabel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(addPanel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(addScrollPane);
		leftPanel.add(Box.createVerticalGlue());
		
		add(leftPanel);
		
		PlannedActivity currentActivity = null;
		for(final PlannedActivity plannedActivity : Data.getData().getPlannedActivityList()){
			if(currentActivity == null){
				currentActivity = plannedActivity;
			}
			PlannedActivityPanel plannedActivityPanel = new PlannedActivityPanel(plannedActivity, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					selectCurrentActivity(plannedActivity);
				}
			});
			addScrollPanel.add(plannedActivityPanel);
			plannedActivityPanelList.add(plannedActivityPanel);
		}
		
		rightPanel = new PlannedActivityRightPanel(currentActivity, this);
		rightPanel.setPreferredSize(Resources.Layout.DOUBLE_PANEL);

		add(rightPanel);
		
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(addField.getText().length() > 0){
					final PlannedActivity newPlannedActivity = new PlannedActivity(addField.getText(), 30, Data.getData().getLocationList().get(0));
					Data.getData().getPlannedActivityList().add(newPlannedActivity);
					PlannedActivityPanel plannedActivityPanel = new PlannedActivityPanel(newPlannedActivity, new ActionListener(){
						public void actionPerformed(ActionEvent e){
							selectCurrentActivity(newPlannedActivity);
						}
					});
					addScrollPanel.add(plannedActivityPanel);
					addField.setText("");
					validate();
				}
			}
		};
		
		addButton.addActionListener(addListener);
		addField.addActionListener(addListener);
		
		validate();
		
		
	}
	
	private void selectCurrentActivity(PlannedActivity activity){
		rightPanel.setCurrentActivity(activity);
	}

	public void updateActivityList() {
		updateActivityList(null);
	}

	public void updateActivityList(PlannedActivity currentActivity) {
		for(PlannedActivityPanel panel : plannedActivityPanelList){
			if(panel.getCurrentActivity().equals(currentActivity)){
				remove(panel);
			} else {
				panel.updateLocation();
			}
		}
	}

}
