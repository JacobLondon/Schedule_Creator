package DataTypes;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Preset implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private String name;
	private LocalTime startTime;
	private int slotNumber;
	private int intervalTime;
	private String[][] presetSchedule;
	private ArrayList<Group> groupList;
	
	private static final LocalTime DEFAULT_START_TIME = LocalTime.of(9, 0);
	private static final int DEFAULT_SLOT_NUMBER = 15;
	private static final int DEFAULT_INTERVAL_TIME = 30;
	private static final String RANDOM_ACTIVITY = "Random";
	
	public Preset(String name, ArrayList<Group> groupList){
		
		this.name = name;
		startTime = DEFAULT_START_TIME;
		slotNumber = DEFAULT_SLOT_NUMBER;
		intervalTime = DEFAULT_INTERVAL_TIME;
		this.groupList = groupList;
		
		int countGroups = countSubgroups();
	
		presetSchedule = new String[slotNumber][countGroups];
		Arrays.fill(presetSchedule[0], RANDOM_ACTIVITY);
		for(int i = 1; i < presetSchedule.length; i++){
			presetSchedule[i] = presetSchedule[0].clone();
		}
		
	}
	
	private int countSubgroups(){
		int countGroups = 0;
		for(Group traverse : groupList){
			
			countGroups += traverse.getSubGroupCount();
			
		}
		return countGroups;
	}
	
	public String getNameIndex(int row, int column){
		return presetSchedule[row][column];
	}
	
	public void setNameIndex(int row, int column, String value){
		presetSchedule[row][column] = value;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIntervalTime(){
		return intervalTime;
	}
	
	public int getSlotNumber(){
		return slotNumber;
	}
	
	public String toString(){
		return name;
	}
	
	
	public void setStartTime(LocalTime startTime){
		this.startTime = startTime;
		
	}
	
	public void setIntervalTime(int time){
		intervalTime = time;
	}
	
	public void setSlotNumber(int number){
		slotNumber = number;
	}
	
	public LocalTime getStartTime(){
		
		return startTime;
	}

	public void updateTime(int slotNumber, int intervalTime) {
		int prevSlotNumber = this.slotNumber;
		
		setSlotNumber(slotNumber);
		setIntervalTime(intervalTime);
		
		// Resize and fill the array
		if(prevSlotNumber > slotNumber){
			// Shrink down
			String[][] oldSchedule = presetSchedule;
			presetSchedule = new String[slotNumber][];
			for(int i = 0; i < slotNumber; i++){
				presetSchedule[i] = oldSchedule[i];
			}
		} else if(prevSlotNumber < slotNumber){
			// Add more rows
			String[][] oldSchedule = presetSchedule;
			presetSchedule = new String[slotNumber][];
			for(int i = 0; i < prevSlotNumber; i++){
				presetSchedule[i] = oldSchedule[i];
			}
			presetSchedule[prevSlotNumber] = new String[countSubgroups()];
			Arrays.fill(presetSchedule[prevSlotNumber], RANDOM_ACTIVITY);
			for(int i = prevSlotNumber + 1; i < presetSchedule.length; i++){
				presetSchedule[i] = presetSchedule[prevSlotNumber].clone();
			}
		} // Otherwise same number of rows
			
		
	}

}
