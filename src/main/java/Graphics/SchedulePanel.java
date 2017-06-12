package Graphics;

import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import DataTypes.Group;
import DataTypes.Preset;
import Resources.Data;

public class SchedulePanel extends JScrollPane {
	
	private Preset preset;
	private Data data;
	
	private JButton saveButton = new JButton("Save");
	
	private JComponent[][] panelData;

	public SchedulePanel(Preset preset, Data data) {
		this.preset = preset;
		this.data = data;
		panelData = new JComponent[preset.getNumSubgroups() + 1][preset.getSlotNumber() + 1];
		// Put save button in top left
		panelData[0][0] = saveButton;
		addGroupNames();
		addTimes();
		setSchedule();
	}

	private void setSchedule() {
		for(int x = 1; x < panelData.length; x++){
			for(int y = 1; y < panelData[x].length; y++){
				//TODO Make schedule panel
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
