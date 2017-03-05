package Backend;

import java.time.LocalTime;

public class TimeConverter {

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
