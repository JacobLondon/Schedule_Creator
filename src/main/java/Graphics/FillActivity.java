package Graphics;

import java.util.ArrayList;

import DataTypes.PlannedActivity;
import DataTypes.Preset;
import DataTypes.RandomActivity;
import Resources.Data;

public class FillActivity {

	private String[][] outputArray;
	
	// elements are stored here when they are temporarily removed from presetScheduleForEachGroup
	private ArrayList<String> randomActivityPerRow = new ArrayList<String>();
	private Preset preset;
	private ArrayList<ArrayList<String>> presetSchedule;
	private ArrayList<ArrayList<String>> presetScheduleCopy;
	private ArrayList<RandomActivity> randomActivityList = Data.getData().getRandomActivityList();
	private ArrayList<PlannedActivity> plannedActivityList = Data.getData().getPlannedActivityList();
	
	private ArrayList<ArrayList<String>> presetScheduleForEachGroup;
	
	private int totalNumSubGroups = 0;
	
	public FillActivity(Preset preset){
		this.preset = preset;
		presetSchedule = preset.getPresetSchedule();
		refillElements();
		
		for(int i = 0; i < preset.getGroupList().size(); i++){
			totalNumSubGroups += preset.getGroupList().get(i).getSubGroupCount();
		}
		
		// Make a deep copy of preset schedule
		for(int i = 0; i < presetSchedule.size(); i++){
			presetScheduleCopy.add(new ArrayList<String>());
			for(int j = 0; j < presetSchedule.get(i).size(); i++){
				presetScheduleCopy.get(i).add(presetSchedule.get(i).get(j));
			}
		}
		
	}
	
	public void fillActivity(){
		
		for(int i = 0; i < preset.getSlotNumber(); i++){
			 
			// each row
			for(int j = 0; j < totalNumSubGroups; j++){
				if(presetScheduleCopy.get(i).get(j).equals("Random")){
					
				}
				else if(!presetScheduleCopy.get(i).get(j).equals("Random")){
					//not random, but planned
				}
			}
		}
		
	}
	
	/**
	 *  Makes a 2D arraylist of strings where each sub-arraylist contains the random activities
	 *  each group can have respectively.
	 * @return 2D ArrayList of Strings with RandomActivity names
	 */
	private ArrayList<ArrayList<String>> createGroupListForRandomActivities(){
		ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < preset.getGroupList().size(); i++){
			outputList.add(new ArrayList<String>());
			for(int j = 0; j < randomActivityList.size(); j++){
				if(randomActivityList.get(j).groupIsIngroupList(preset.getGroupList().get(i))){
					outputList.get(i).add(randomActivityList.get(j).getName());
				}
			}
		}
		return outputList;
	}
	
	/**
	 * Removes the given String from the 2D ArrayList of Strings in each ArrayList
	 * Adds the removed element into another ArrayList to keep track of elements.
	 * @param element The String to be removed from the ArrayList.
	 */
	private void removeElement(String element){
		for(int i = 0; i < presetScheduleForEachGroup.size(); i++){
			for(int j = 0; j < presetScheduleForEachGroup.get(i).size(); j++){
				if(presetScheduleForEachGroup.get(i).get(j).equals(element)){
					presetScheduleForEachGroup.get(i).remove(j);
				}
			}
		}
	}
	
	//GitHub webhook test :D?
	
	private void refillElements(){
		presetScheduleForEachGroup = createGroupListForRandomActivities();
	}
	
	/**
	 * Finds and returns the Group number which specifies the Group from the total amount of subgroups.
	 * @param groupNum The group number with all subgroups accounted for from previous Groups
	 * @return The equivalent Group index in GroupList 
	 */
	private int findGroup(int groupNum){
		for(int i = 0; i < preset.getGroupList().size(); i++){
			if(preset.getGroupList().get(i).getSubGroupCount() >= groupNum){
				return groupNum;
			}
			else{
				groupNum -= preset.getGroupList().get(i).getSubGroupCount();
			}
		}
		return -1;
		
	}
}
