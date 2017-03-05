package DataTypes;

import java.time.LocalTime;
import java.awt.Color;
import java.io.Serializable;
import java.time.DayOfWeek;

public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalTime unavailableStart;
	private LocalTime unavailableEnd;
	
	private final DayAvailability[] availability = {new DayAvailability(DayOfWeek.SUNDAY), new DayAvailability(DayOfWeek.MONDAY), 
			new DayAvailability(DayOfWeek.TUESDAY), new DayAvailability(DayOfWeek.WEDNESDAY), new DayAvailability(DayOfWeek.THURSDAY), 
			new DayAvailability(DayOfWeek.FRIDAY), new DayAvailability(DayOfWeek.SATURDAY)};
	
	private final LocationColor locationColor;
	
	private String name;
	
	// normal constructor
	public Location(String name){
		
		this.name = name;
		locationColor = new LocationColor(Color.black);
		unavailableStart = null;
		unavailableEnd = null;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	public String getName(){
		
		return name;
		
	}
	
	public void setLocationColor(Color color){
		
		locationColor.setColor(color);
	}
	public LocationColor getLocationColor(){
		
		return locationColor;
	}
	
	public void setUnavailableStart(LocalTime time){
		
		unavailableStart = time;
	}
	public LocalTime getUnavailableStart(){
		
		return unavailableStart;
	}
	
	public void setUnavailableEnd(LocalTime time){
		
		unavailableEnd = time;
	}
	public LocalTime getUnavailableEnd(){
		
		return unavailableEnd;
	}
	
	public boolean getDayAvailability(DayOfWeek day){
		
		for(int traverse = 0; traverse < availability.length; traverse++){
			
			if(availability[traverse].getDay().equals(day)){
				
				return availability[traverse].getAvailable();
				
			}
			
		}
		return false;
	}
}
