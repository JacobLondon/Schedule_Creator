package DataTypes;

import java.io.Serializable;
import java.util.ArrayList;

public class RandomActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private ArrayList<Location> locationList;		// color for text
	
	public RandomActivity(String name, ArrayList<Location> locationList){
		
		this.name = name;
		this.locationList = new ArrayList<Location>(locationList);
		
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
	public ArrayList<Location> getLocation(){
		
		return locationList;
	}
	
}
