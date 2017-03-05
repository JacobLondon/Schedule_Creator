package DataTypes;

import java.io.Serializable;
import java.time.DayOfWeek;

public class DayAvailability implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean available;
	private final DayOfWeek day;
	
	public DayAvailability(DayOfWeek day){
		
		this(true, day);
		
	}	
	public DayAvailability(boolean available, DayOfWeek day){
		
		this.available = available;
		this.day = day;
		
	}
	
	public void setAvailable(boolean available){
		
		this.available = available;
		
	}
	
	public boolean getAvailable(){
		
		return available;
		
	}
	
	public DayOfWeek getDay(){
		
		return day;
		
	}
}
