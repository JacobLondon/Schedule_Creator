package Graphics;

import java.util.ArrayList;

import DataTypes.PlannedActivity;
import DataTypes.Preset;
import DataTypes.RandomActivity;
import Resources.Data;

public class FillActivity {

	private String[][] outputArray;
	
	// elements are stored here when they are temporarily removed from presetScheduleForEachGroup
	private Preset preset;
	private ArrayList<ArrayList<String>> presetSchedule;
	private ArrayList<ArrayList<String>> presetScheduleCopy = new ArrayList<ArrayList<String>>();
	private ArrayList<RandomActivity> randomActivityList = Data.getData().getRandomActivityList();
	
	private ArrayList<ArrayList<String>> presetScheduleXForEachGroup;
	private ArrayList<ArrayList<String>> presetScheduleYForEachGroup = new ArrayList<ArrayList<String>>();
	
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
			for(int j = 0; j < presetSchedule.get(i).size(); j++){
				presetScheduleCopy.get(i).add(presetSchedule.get(i).get(j));
			}
		}
		
	}
	
	public ArrayList<ArrayList<String>> getActivities(){
		return presetScheduleCopy;
	}
	
	public void fillActivity() throws NotEnoughRandomActivitiesException {
		
		// Fill the Y check with sub-arrays
		for(int i = 0; i < totalNumSubGroups; i++){
			presetScheduleYForEachGroup.add(new ArrayList<String>());
		}
		
		// Scan for Random and replace with a randomly chosen RandomActivity
		for(int i = 0; i < preset.getSlotNumber(); i++){
			refillElements();
			
			// each row
			for(int j = 0; j < totalNumSubGroups; j++){
				
				if(presetScheduleCopy.get(i).get(j).equals("Random")){
					if(presetScheduleXForEachGroup.get(findGroup(j)).isEmpty()){
						throw new NotEnoughRandomActivitiesException("Ran out of random activities while generating schedule.");
					}
					do{
						presetScheduleCopy.get(i).set(j, getRandomElement(presetScheduleXForEachGroup.get(findGroup(j))));
					}while(presetScheduleYForEachGroup.get(j).contains(presetScheduleCopy.get(i).get(j)));
					
					presetScheduleYForEachGroup.get(j).add(presetScheduleCopy.get(i).get(j));
					removeElement(presetScheduleCopy.get(i).get(j), j);
					
				}
				// planned activities are already there
								
			}
		}
		
	}
	
	/**
	 * 
	 * @param stringList ArrayList of Strings with elements.
	 * @return A random element within the ArrayList.
	 */
	private String getRandomElement(ArrayList<String> stringList){
		return stringList.get((int)(stringList.size()*Math.random()));
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
	private void removeElement(String element, int subGroupNumber){
		for(int i = 0; i < presetScheduleXForEachGroup.size(); i++){
			for(int j = 0; j < presetScheduleXForEachGroup.get(i).size(); j++){
				if(presetScheduleXForEachGroup.get(i).get(j).equals(element)){
					presetScheduleXForEachGroup.get(i).remove(j);
				}
			}
		}
	}
	
	//GitHub webhook test :D?
	
	private void refillElements(){
		presetScheduleXForEachGroup = createGroupListForRandomActivities();
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
