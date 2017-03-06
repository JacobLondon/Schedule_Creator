package DataTypes;

import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * This will contain a day of the week and whether it is available.
 * This will be used in combination with other objects to determine their availability.
 *
 */
public class DayAvailability implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean available;
	private final DayOfWeek day;
	
	/**
	 * The default constructor for a certain day of the week.
	 * It defaults to available for that day.
	 * 
	 * @param day The day of the week that this represents.
	 */
	public DayAvailability(DayOfWeek day){
		
		this(true, day);
		
	}	
	
	/**
	 * The constructor for a certain day of the week with a known availability.
	 * 
	 * @param available Whether or not the day is available.
	 * @param day The day of the week that this represents.
	 */
	public DayAvailability(boolean available, DayOfWeek day){
		
		this.available = available;
		this.day = day;
		
	}
	
	/**
	 * A mutator for availability.
	 * 
	 * @param available What to change the availability to.
	 */
	public void setAvailable(boolean available){
		
		this.available = available;
		
	}
	
	/**
	 * An accessor for availability.
	 * 
	 * @return Returns whether or not the day is available.
	 */
	public boolean getAvailable(){
		
		return available;
		
	}
	
	/**
	 * An accessor for the day of the week.
	 * 
	 * @return Returns the day of the week.
	 */
	public DayOfWeek getDay(){
		
		return day;
		
	}
}
