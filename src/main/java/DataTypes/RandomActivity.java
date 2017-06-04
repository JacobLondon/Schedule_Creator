package DataTypes;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;

public class RandomActivity implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private String name;
	private ArrayList<Location> locationList;		// color for text
	private ArrayList<Group> groupList;
	
	public RandomActivity(String name, ArrayList<Location> locationList, ArrayList<Group> groupList){
		
		this.name = name;
		this.locationList = new ArrayList<Location>(locationList);
		this.groupList = new ArrayList<Group>(groupList);
		
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	public String getName(){
		
		return name;
	}
	
	public void setLocation(ArrayList<Location> locationList){
		
		this.locationList = locationList;
	}
	public ArrayList<Location> getLocations(){
		
		return locationList;
	}
	
	public void setGroup(ArrayList<Group> groupList){
		
		this.groupList = groupList;
	}
	public ArrayList<Group> getGroups(){
		
		return groupList;
	}
	
	public boolean locationIsInLocationList(Location location){
		return locationList.contains(location);
		
	}

	public boolean groupIsIngroupList(Group group) {
		return groupList.contains(group);
	}
}
