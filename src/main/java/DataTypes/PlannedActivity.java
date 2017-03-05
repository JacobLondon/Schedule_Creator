package DataTypes;

import java.awt.Color;
import java.io.Serializable;

public class PlannedActivity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int minuteDuration;
	private Color backgroundColor;	// color for background
	private Location location;		// color for text
	
	private static final Color DEFAULT_BG_COLOR = Color.white;
	
	public PlannedActivity(String name, int duration, Location location){
		
		this.name = name;
		minuteDuration = duration;
		this.location = location;
		backgroundColor = DEFAULT_BG_COLOR;
		
	}
	
	public void setName(String name){
		
		this.name = name;
	}
	public String getName(){
		
		return name;
	}
	
	public void setDuration(int duration){
		
		minuteDuration = duration;
	}
	public int getDuration(){
		
		return minuteDuration;
	}
	
	public void setLocation(Location location){
		
		this.location = location;
	}
	public Location getLocation(){
		
		return location;
	}
	
}
