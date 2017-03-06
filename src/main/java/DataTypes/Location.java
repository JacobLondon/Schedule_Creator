package DataTypes;

import java.time.LocalTime;
import java.awt.Color;
import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * This class represents a location that an activity may take place as well as it's availability.
 *
 */
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalTime unavailableStart;
	private LocalTime unavailableEnd;
	
	private final DayAvailability[] availability = {new DayAvailability(DayOfWeek.SUNDAY), new DayAvailability(DayOfWeek.MONDAY), 
			new DayAvailability(DayOfWeek.TUESDAY), new DayAvailability(DayOfWeek.WEDNESDAY), new DayAvailability(DayOfWeek.THURSDAY), 
			new DayAvailability(DayOfWeek.FRIDAY), new DayAvailability(DayOfWeek.SATURDAY)};
	
	private final LocationColor locationColor;
	
	private String name;
	
	/**
	 * This is the constructor for Location.
	 * It defaults the color of the location to be black, and the availability to always available.
	 * 
	 * @param name The name of the location.
	 */
	public Location(String name){
		
		this.name = name;
		locationColor = new LocationColor(Color.black);
		unavailableStart = null;
		unavailableEnd = null;
		
	}
	
	/**
	 * A mutator for the name.
	 * 
	 * @param name The new name.
	 */
	public void setName(String name){
		
		this.name = name;
		
	}
	
	/**
	 * The accessor for the name.
	 * @return
	 */
	public String getName(){
		
		return name;
		
	}
	
	/**
	 * The mutator for the color of the location.
	 * 
	 * @param color The color to use for this location.
	 */
	public void setLocationColor(Color color){
		
		locationColor.setColor(color);
	}
	
	/**
	 * The accessor for the color of the location.
	 * 
	 * @return The color for this location.
	 */
	public LocationColor getLocationColor(){
		
		return locationColor;
	}
	
	/**
	 * This will set the beginning of a period of unavailability for a location.
	 * 
	 * @param time The time that this location begins to be unavailable.
	 */
	public void setUnavailableStart(LocalTime time){
		
		unavailableStart = time;
	}
	
	/**
	 * The accessor for the beginning of a period of unavailability for a location.
	 * 
	 * @return The time this location becomes unavailable.
	 */
	public LocalTime getUnavailableStart(){
		
		return unavailableStart;
	}
	
	/**
	 * This will set the end of a period of unavailability for a location.
	 * 
	 * @param time The time this location becomes available again.
	 */
	public void setUnavailableEnd(LocalTime time){
		
		unavailableEnd = time;
	}
	
	/**
	 * The accessor for the end of a period of unavailability for a locatin.
	 * 
	 * @return The time this location returns to availability.
	 */
	public LocalTime getUnavailableEnd(){
		
		return unavailableEnd;
	}
	
	/**
	 * The accessor for a day availability of a given day.
	 * 
	 * @param day The day of the week to check.
	 * @return Returns true if the location is available on that day.
	 */
	public boolean getDayAvailability(DayOfWeek day){
		
		for(int traverse = 0; traverse < availability.length; traverse++){
			
			if(availability[traverse].getDay().equals(day)){
				
				return availability[traverse].getAvailable();
				
			}
			
		}
		return false;
	}
}
