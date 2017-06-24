package Graphics.OptionsMenu.PresetsTab;

import java.time.format.DateTimeFormatter;
import java.util.ListIterator;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

import DataTypes.Group;
import DataTypes.Preset;
import Resources.Data;

public class ActivityPlanPanel extends JPanel {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh.mm a");
	private Data data;
	private JTable table;
	private Object[] columnNames;
	private Object[][] rowData;
	
	public ActivityPlanPanel(Preset currentPreset){
		data = Data.getData();
		rowData = new Object[currentPreset.getSlotNumber()][columnNames.length];
		
		// Initalize the table data
		setTable(currentPreset);
	}

	private void setTable(Preset currentPreset) {
		// Make sure the group sizes havent changed
		if(columnNames.length != data.getGroupList().size() + 1){
			columnNames = new Object[data.getGroupList().size() + 1];
			columnNames[0] = "Time";
		}
		
		// Make sure the slot numbers havent changed
		if(rowData.length != currentPreset.getSlotNumber() || rowData[0].length != columnNames.length){
			rowData = new Object[currentPreset.getSlotNumber()][columnNames.length];
		}

		// Initialize the columns
		ListIterator<Group> groups = data.getGroupList().listIterator();
		for(int i = 1; i < columnNames.length; i++){
			columnNames[i] = groups.next();
		}
		
		// Create an array for the activities
		String[] activityNameArray = new String[data.getPlannedActivityList().size() + 1];
		activityNameArray[0] = "Random";
		for(int count = 0; count < data.getPlannedActivityList().size(); count++ ){
			activityNameArray[count + 1] = data.getPlannedActivityList().get(count).getName();			
		}
		
		// Initialize the table.
		for(int rowIndex = 0; rowIndex < rowData.length; rowIndex++){
			rowData[rowIndex][0] = getTime(currentPreset, rowIndex);
			for(int colIndex = 1; colIndex < rowData[rowIndex].length; colIndex++){
				rowData[rowIndex][colIndex] = new JComboBox<String>(activityNameArray);
			}
		}
		
		
	}
	
	private static String getTime(Preset currentPreset, int offset){
		return currentPreset.getStartTime().plusMinutes(currentPreset.getIntervalTime() 
				* (offset)).format(formatter) + " - " + 
				currentPreset.getStartTime().plusMinutes(currentPreset.getIntervalTime() * 
				(offset + 1)).format(formatter);
	}
}
