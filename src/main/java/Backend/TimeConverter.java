package Backend;

import java.time.LocalTime;

public class TimeConverter {

	/**
	 * This is a utility method that will convert user input for time to a LocalTime object.
	 * 
	 * @param timeHour The string representation of the hour.
	 * @param timeMinute The string representation of the minute.
	 * @param morningAM Whether the time is AM or PM
	 * @return Returns a LocalTime object that has the given time.
	 */
	public static LocalTime stringToLocalTime(String timeHour, String timeMinute, Boolean morningAM){
		
		int hour = Integer.parseInt("0" + timeHour);
		int minute = Integer.parseInt("0" + timeMinute);
		
		if(!morningAM){
			if(hour < 12)
				hour += 12;
			
		}
		else{
			if(hour == 12)
				hour = 0;
		}
		
		return LocalTime.of(hour, minute);
	}	
}
