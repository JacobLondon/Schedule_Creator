package Graphics.OptionsMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataTypes.PlannedActivity;
import Resources.Data;

public class PlannedActivityRightPanel extends JPanel {

	private PlannedActivityOptionsPanel optionsPanel;
	private PlannedActivity currentActivity;
	private JTextField nameField;
	private JButton delete;
	private JButton save;
	private ActionListener saveListener;
	private ActionListener deleteListener;
	private Data data = Data.getData();

	public PlannedActivityRightPanel(PlannedActivity currentActivity, PlannedActivityOptionsPanel optionsPanel){
		this.optionsPanel = optionsPanel;
		nameField = new JTextField(14);
		save = new JButton("Save");
		delete = new JButton("Delete");
		
		setCurrentActivity(currentActivity);
		
		add(nameField);
		add(save);
		add(delete);
	}

	public void setCurrentActivity(PlannedActivity activity) {
		save.removeActionListener(saveListener);
		delete.removeActionListener(deleteListener);
		
		currentActivity = activity;
		nameField.setText(currentActivity.getName());
		
		saveListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentActivity.setName(nameField.getText());
				optionsPanel.updateActivityList();
			}
		};
		deleteListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				data.getPlannedActivityList().remove(currentActivity);
				optionsPanel.updateActivityList(currentActivity);
			}
		};
		
		save.addActionListener(saveListener);
		delete.addActionListener(deleteListener);
	}
}
