package Graphics.OptionsMenu.ActivityPlanModel;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import DataTypes.Preset;
import Resources.Data;

public class ActivityPlanTableModel extends AbstractTableModel {

	private Data data;
	private Vector<Vector<JComboBox>> tableData;
	private Vector<String> columnNames;
	
	public ActivityPlanTableModel(Preset preset){
		
		// number of rows
		tableData = new Vector<Vector<JComboBox>>(preset.getSlotNumber());
		// number of columns
		columnNames = new Vector<String>(data.getGroupList().size() + 1);
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
