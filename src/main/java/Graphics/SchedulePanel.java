package Graphics;

import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import DataTypes.Activity;
import DataTypes.Group;
import DataTypes.Preset;
import Resources.Data;

public class SchedulePanel extends JPanel {
	
	private Preset preset;
	private Data data;
	
	private JButton saveButton = new JButton("Save");
	
	private JComponent[][] panelData;
	private int width;
	private int height;
	private FillActivity fillActivity;
	
	public SchedulePanel(Preset preset, Data data) throws NotEnoughRandomActivitiesException {
		this.preset = preset;
		this.data = data;
		fillActivity = new FillActivity(preset);
		panelData = new JComponent[preset.getNumSubgroups() + 1][preset.getSlotNumber() + 1];
		
		// Put save button in top left
		panelData[0][0] = saveButton;
		width = panelData.length;
		height = panelData[0].length;
		
		addGroupNames();
		addTimes();
		setSchedule();
		addPanelInfo();
		fillActivity.fillActivity();
		selectComboBoxes();
		
		validate();
	}
	
	/**
	 * Fills all the ComboBoxes with the corresponding RandomActivities.
	 */
	private void selectComboBoxes(){
		for(int x = 1; x < panelData.length; x++){
			for(int y = 1; y < panelData[x].length; y++){
				((JComboBox) panelData[x][y]).setSelectedItem(fillActivity.getActivities().get(y-1).get(x-1));
			}
		}
	}
	
	private void setSchedule() {
		
		Vector<Activity> activityList = new Vector<Activity>(Data.getData().getRandomActivityList());
		activityList.addAll(Data.getData().getPlannedActivityList());
		
		for(int x = 1; x < width; x++){
			for(int y = 1; y < height; y++){
				JComboBox gridBox = new JComboBox(activityList);
				gridBox.setEditable(true);
				panelData[x][y] = gridBox;
			}
		}
	}

	private void addPanelInfo(){
		
		setLayout(new GridLayout(height, width));
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				add(panelData[x][y]);
				
			}
		}
	}
	
	private Group getGroupFromColumn(int column){
		for(Group curr : preset.getGroupList()){
			if(column < curr.getSubGroupCount()){
				return curr;
			}
			column = column - curr.getSubGroupCount();
		}
		return null;
	}
	
	private void addTimes() {
		int count = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		for(int i = 1; i < preset.getSlotNumber() + 1; i++){
			panelData[0][i] = new JLabel(new StringBuilder()
					.append(preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count)).format(formatter))
					.append(" - ")
					.append(preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count + 1)).format(formatter))
					.toString());
			count++;
		}
	}

	private void addGroupNames() {
		int groupNum = 0;
		int subGroupNum = 0;
		for(int i = 1; i < panelData.length; i++){
			panelData[i][0] = new JLabel(preset.getGroupList().get(groupNum).getName() + subGroupNum);
			subGroupNum++;
			if(subGroupNum >= preset.getGroupList().get(groupNum).getSubGroupCount()){
				groupNum++;
				subGroupNum = 0;
			}
		}
	}

}
