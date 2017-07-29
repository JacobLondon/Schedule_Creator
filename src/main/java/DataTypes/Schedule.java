package DataTypes;

import java.awt.Color;
import java.util.Date;

import javax.swing.JComponent;

import org.apache.poi.xssf.usermodel.XSSFColor;

import Resources.Data;

public class Schedule {
	
	private Data data = Data.getData();
	private Color[][] foregroundColors;
	private Color[][] backgroundColors;
	private String[][] boxText;
	
	public Schedule(JComponent[][] panelData){
		foregroundColors = new Color[panelData.length][panelData[0].length];
		backgroundColors = new Color[panelData.length][panelData[0].length];
		boxText = new String[panelData.length][panelData[0].length];
		
		// fill the text in the boxes
		for(int x = 0; x < panelData.length; x++){
			for(int y = 0; y < panelData[x].length; y++){
				boxText[x][y] = panelData[x][y].getName();
			}
		}
		
		// fill the colors
		for(int x = 0; x < panelData.length; x++){
			for(int y = 0; y < panelData[x].length; y++){
				if(x == 0 || y == 0){
					foregroundColors[x][y] = Color.BLACK;
				}
				// check the planned activities for colors
				for(int i = 0; i < data.getPlannedActivityList().size(); i++){
					if(panelData[x][y].getName().equals(data.getPlannedActivityList().get(i).getName())){
						foregroundColors[x][y] = data.getPlannedActivityList().get(i).getLocation().getLocationColor().getColor();
					}
				}
				// check the random activities for colors
				for(int i = 0; i < data.getRandomActivityList().size(); i++){
					if(panelData[x][y].getName().equals(data.getRandomActivityList().get(i).getName())){
						foregroundColors[x][y] = data.getRandomActivityList().get(i).getLocations()
					}
				}
			}
		}
	}

	/**
	 * The number of rows in the schedule including the group row
	 * 
	 * @return  
	 */
	public int numRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The number of columns in the schedule including the time column
	 * 
	 * @return
	 */
	public int numCols() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The text in the schedule at x,y with the group row being x=0, and the time column being y=0
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public String getText(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The background color of the text in the schedule at x,y
	 * This will represent the color of the planned activity if it is one.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public short getBGColor(int x, int y) {
		// TODO Auto-generated method stub
		Color color = null;
		return new XSSFColor(color).getIndex();
	}

	/**
	 * The foreground color of the text in the schedule at x,y
	 * This will represent the color of the location if it is a random activity
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public short getFGColor(int x, int y) {
		// TODO Auto-generated method stub
		Color color = null;
		return new XSSFColor(color).getIndex();
	}

	/**
	 * The number of locations(used for the legend).
	 * 
	 * @return
	 */
	public int getNumLocations() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The ith location for the legend
	 * 
	 * @param i
	 * @return
	 */
	public Location getLocations(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 * foreground color
	 * 		room availability
	 * background color
	 * 		constant for specific planned activities
	 * Strings for names of the activities
	 * Groups
	 * 		group names
	 * 		time slots
	 * 
	 */
	
	
	
}
