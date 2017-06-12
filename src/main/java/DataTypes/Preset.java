package DataTypes;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Preset implements Serializable {

	private static final long serialVersionUID = 3L;
	
	private String name;
	private LocalTime startTime;
	private int slotNumber;
	private int intervalTime;
	private ArrayList<ArrayList<String>> presetSchedule;
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
	
		presetSchedule = new ArrayList<ArrayList<String>>();		
		for(int row = 0; row < DEFAULT_SLOT_NUMBER; row++){
			presetSchedule.add(new ArrayList<String>());
			for(int column = 0; column < countGroups; column++){
				presetSchedule.get(row).add(RANDOM_ACTIVITY);
			}	
		}		
	}
	
	private int countSubgroups(){
		int countGroups = 0;
		for(Group traverse : groupList){
			
			countGroups += traverse.getSubGroupCount();
			
		}
		return countGroups;
	}
	
	public void addColumn(){
		for(int traverse = 0; traverse < presetSchedule.size(); traverse++){
			presetSchedule.get(traverse).add(RANDOM_ACTIVITY);
		}
	}
	
	public void removeColumn(int indexToRemove){
		for(int traverse = 0; traverse < presetSchedule.size(); traverse++){
			presetSchedule.get(traverse).remove(indexToRemove);
		}
	}
	
	public String getNameIndex(int row, int column){
		return presetSchedule.get(row).get(column);
	}
	
	public void setNameIndex(int row, int column, String value){
		presetSchedule.get(row).set(column, value);
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
		
		if(prevSlotNumber > slotNumber){
			while(presetSchedule.size() > slotNumber){
				presetSchedule.remove(presetSchedule.size() - 1);
			}
		}
		else if(prevSlotNumber < slotNumber){
			while(presetSchedule.size() < slotNumber){
				ArrayList<String> randomArrayList = new ArrayList<String>();
				for(int traverse = 0; traverse < countSubgroups(); traverse ++){
					randomArrayList.add(RANDOM_ACTIVITY);
				}
				presetSchedule.add(randomArrayList);
			}
		}
		
	}

	public int getNumSubgroups() {
		int total = 0;
		for(Group currGroup : groupList){
			total += currGroup.getSubGroupCount();
		}
		return total;
	}

	public ArrayList<Group> getGroupList() {
		return groupList;
	}

}
