package Graphics.OptionsMenu;

import java.time.format.DateTimeFormatter;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import DataTypes.Preset;
import Resources.Data;

public class PresetTable extends JTable {

	private static final String TIME_COLUMN_NAME = "Time";
	private Preset currentPreset;
	private DefaultTableModel model;
	private TableCellEditor defaultCellEditor = getDefaultEditor(String.class);
	private TableCellEditor comboBoxCellEditor;
	private Data data;
	private boolean reformattingTable;
	
	public PresetTable(Preset startingPreset){
		
		super(new DefaultTableModel());
		model = (DefaultTableModel) getModel();
		model.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(!reformattingTable){
					updateDataFromTable(e.getFirstRow(), e.getColumn());
				}
			}
		});
		data = Data.getData();
		setPreset(startingPreset);
		
	}
	
	/**
	 * This will update the data object when an item is changed in the jtable.
	 * 
	 * @param row The row that was updated
	 * @param col The column that was updated
	 */
	private void updateDataFromTable(int row, int col){
		int timeSlot = row;
		int groupNumber = col - 1;
		currentPreset.setNameIndex(timeSlot, groupNumber, (String) model.getValueAt(row, col));
	}
	
	/**
	 * Sets up the table for the preset using the currently selected preset.
	 * @param preset Will become the current selected preset.
	 */
	public void setPreset(Preset preset){
		
		reformattingTable = true;
		currentPreset = preset;
		
		// makes an array of column names
		String[] columnNames = new String[1 + Data.getData().getGroupList().size()];
		columnNames[0] = TIME_COLUMN_NAME;
		for(int traverse = 1; traverse < columnNames.length; traverse++){
			columnNames[traverse] = Data.getData().getGroupList().get(traverse - 1).getName();
		}

		// fills the combo box with random and the planned activities
		JComboBox<String> startingComboBox = new JComboBox<String>();
		startingComboBox.addItem("Random");
		for(int traverse = 0; traverse < Data.getData().getPlannedActivityList().size(); traverse++){
			startingComboBox.addItem(Data.getData().getPlannedActivityList().get(traverse).getName());
		}
		startingComboBox.setEditable(false);
		comboBoxCellEditor = new DefaultCellEditor(startingComboBox);
		
		
		// sets the names of the columns and sets the number of rows to be correct amount in regard to how many slots there are
		model.setColumnIdentifiers(columnNames);
		model.setRowCount(currentPreset.getSlotNumber());
		
		// sets the cell editor
		getColumnModel().getColumn(0).setCellEditor(defaultCellEditor);
		for(int traverse = 1; traverse < columnNames.length; traverse++){
			getColumnModel().getColumn(traverse).setCellEditor(comboBoxCellEditor);
		}

		
		// formats the times and fills the first column of the model with times
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh.mm a");
		for(int count = 0; count < preset.getSlotNumber(); count++){
			model.setValueAt(preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count)).format(formatter) +
					" - " + preset.getStartTime().plusMinutes(preset.getIntervalTime() * (count + 1)).format(formatter), count, 0);
		}
		
		// fills the model with the corresponding preset names
		for(int column = 1; column < columnNames.length; column++){
			for(int row = 0; row < preset.getSlotNumber(); row++){
				model.setValueAt(currentPreset.getNameIndex(row, column - 1), row, column);
			}
		}
		
		setVisible(true);
		validate();
		reformattingTable = false;
	}
	
}
