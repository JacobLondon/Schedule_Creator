package Graphics.OptionsMenu.ActivityPlanModel;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import DataTypes.Preset;
import Resources.Data;

public class ActivityPlanTableModel extends AbstractTableModel {

	private Data data = Data.getData();
	private Vector<Vector<JComboBox>> tableData;
	private Vector<String> columnNames;
	private Vector<String> timeVector;
	private Preset preset;
	
	public ActivityPlanTableModel(Preset preset){
		
		
		this.preset = preset;
		
		// number of rows
		tableData = new Vector<Vector<JComboBox>>(preset.getSlotNumber());
		// number of columns
		columnNames = new Vector<String>(data.getGroupList().size() + 1);
		
		timeVector = new Vector<String>(preset.getSlotNumber());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh.mm a");
		for(int count = 0; count < preset.getSlotNumber(); count++){
			timeVector.add(count, preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count)).format(formatter) +
					" - " + preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count + 1)).format(formatter));
		}
		
		// populate array for combo box selections
		String[] activityNameArray = new String[data.getPlannedActivityList().size() + 1];
		activityNameArray[0] = "Random";
		for(int count = 0; count < data.getPlannedActivityList().size(); count++ ){
			activityNameArray[count + 1] = data.getPlannedActivityList().get(count).getName();			
		}
		
		for(int rows = 0; rows < preset.getSlotNumber(); rows++){
			tableData.add(new Vector<JComboBox>(data.getGroupList().size()));
			for(int columns = 0; columns < tableData.get(rows).size(); columns++){
				JComboBox columnBox = new JComboBox(activityNameArray);
				tableData.get(rows).add(columnBox);
				
			}		
		}
		
	}
	
	public int getColumnCount() {
		return data.getGroupList().size() + 1;
	}

	public int getRowCount() {
		return preset.getSlotNumber();
	}

	public Object getValueAt(int row, int column) {
		if(column == 0){
			return preset.getStartTime();
		}
		if(column == 1){
			return tableData.get(row).get(column - 1);
		}
		
		return null;
	}

}
