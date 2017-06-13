package Graphics;

import java.util.ArrayList;

import DataTypes.PlannedActivity;
import DataTypes.Preset;
import DataTypes.RandomActivity;
import Resources.Data;

public class FillActivity {

	private String[][] outputArray;
	private ArrayList<String> randomActivityPerRow = new ArrayList<String>();
	private Preset preset;
	private ArrayList<ArrayList<String>> presetSchedule;
	private ArrayList<RandomActivity> randomActivityList = Data.getData().getRandomActivityList();
	private ArrayList<PlannedActivity> plannedActivityList = Data.getData().getPlannedActivityList();
	
	private ArrayList<ArrayList<String>> presetScheduleForEachGroup;
	
	public FillActivity(Preset preset){
		this.preset = preset;
		presetScheduleForEachGroup = createGroupListForRandomActivities();
		
	}
	
	public void fillActivity(){
		
		
		for(int i = 0; i < presetSchedule.size(); i++){
			for(int j = 0; j < presetSchedule.get(0).size(); j++){
				if(presetSchedule.get(i).get(j).equals("Random")){
					
				}
				else if(!presetSchedule.get(i).get(j).equals("Random")){
					//not random, but planned
				}
			}
		}
		
	}
	
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
}
