package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataTypes.Preset;

public class TimePanel extends JPanel {

	public TimePanel(LocalTime startTime, int duration, int slotNumber){
		
		this(getHourFormat(startTime) + " - " +  getHourFormat(startTime.plusMinutes(duration)), "Slot " + slotNumber);
		
		
		
	}
	
	public static String getHourFormat(LocalTime time){
	
		int hours = time.getHour();
		
		String morningAM = "AM";
		if(hours > 12){
			hours -= 12;
			morningAM = "PM";
		}
		if(hours == 12)
			morningAM = "PM";
		if(hours == 0)
			hours += 12;
		
		return hours + " : " + getMinuteFormat(time) + " " + morningAM;
		
	}
	
	public static String getMinuteFormat(LocalTime time){
		
		int minutes = time.getMinute();
		if(minutes < 10)
			return "0" + minutes;
		return "" + minutes;
		
	}
	
	// header time panel
	public TimePanel(){
		
		this("Time","Slot Number");
		
	}
	
	private TimePanel(String time, String slot){
		
		super();
		
		JPanel timePanel = new JPanel();
		JPanel slotPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		JLabel timeLabel = new JLabel(time);
		JLabel slotLabel = new JLabel(slot);
		timeLabel.setFont(Resources.Fonts.STANDARD);
		slotLabel.setFont(Resources.Fonts.STANDARD);
		timePanel.add(timeLabel);
		slotPanel.add(slotLabel);
		
		add(timePanel, BorderLayout.EAST);
		add(slotPanel, BorderLayout.WEST);
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
	}
	
	public static ArrayList<TimePanel> getTimePanelList(Preset preset){
		
		ArrayList<TimePanel> timePanelList = new ArrayList<TimePanel>();
		timePanelList.add(new TimePanel());
		
		LocalTime startTime = preset.getStartTime();
		
		for(int i = 0; i < preset.getSlotNumber();i++){
			
			timePanelList.add(new TimePanel(startTime, preset.getIntervalTime(), i + 1));
			startTime = startTime.plusMinutes(preset.getIntervalTime());
		}
		
		return timePanelList;
	}
}
