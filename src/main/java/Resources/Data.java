package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import DataTypes.Group;
import DataTypes.Location;
import DataTypes.PlannedActivity;
import DataTypes.Preset;
import DataTypes.RandomActivity;

public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * The directory where the data file will be stored.
	 */
	private static File DIRECTORY_FILE = new File(System.getProperty("user.home") + System.getProperty("file.separator") +
			"Schedule Creator");
	
	/**
	 * The data file.
	 */
	private static File FILE = new File(System.getProperty("user.home") + System.getProperty("file.separator") +
			"Schedule Creator" + System.getProperty("file.separator") + "data");
	
	/**
	 * The Data object singleton.
	 */
	private static final Data DATA = new Data();
	
	// things to store	
	private ArrayList<Preset> presetList;
	private ArrayList<RandomActivity> randomActivityList;
	private ArrayList<PlannedActivity> plannedActivityList;
	private ArrayList<Group> groupList;
	private ArrayList<Location> locationList;
	
	/**
	 * The constructor for Data objects. This will attempt to find the information file,
	 * and if it does not find it, the constructor will create a new default file.
	 * 
	 */
	private Data(){
		try {
			
			if(FILE.exists()){
				
				FileInputStream readFile = new FileInputStream(FILE);
				ObjectInputStream readData = new ObjectInputStream(readFile);
				readDataFromDisk(readData);
			}
			else{
				if(!DIRECTORY_FILE.exists()){
					
					DIRECTORY_FILE.mkdir();
				}
				FILE.createNewFile();
				presetList = new ArrayList<Preset>();
				randomActivityList = new ArrayList<RandomActivity>();
				plannedActivityList = new ArrayList<PlannedActivity>();
				groupList = new ArrayList<Group>();
				locationList = new ArrayList<Location>();
				
				// defaults
				groupList.add(new Group("Group 1", 1));
				groupList.add(new Group("Group 2", 2));
				groupList.add(new Group("Group 3", 3));
				
				presetList.add(new Preset("Sunday", groupList));
				presetList.add(new Preset("Monday", groupList));
				presetList.add(new Preset("Tuesday", groupList));
				presetList.add(new Preset("Wednesday", groupList));
				presetList.add(new Preset("Thursday", groupList));
				presetList.add(new Preset("Friday", groupList));
				presetList.add(new Preset("Saturday", groupList));
				
				locationList.add(new Location("Room 1"));
				locationList.add(new Location("Room 2"));
				locationList.add(new Location("Room 3"));
				
				randomActivityList.add(new RandomActivity("Random Activity 1", locationList));
				randomActivityList.add(new RandomActivity("Random Activity 2", locationList));
				randomActivityList.add(new RandomActivity("Random Activity 3", locationList));
				
				plannedActivityList.add(new PlannedActivity("Planned Activity 1", 30, locationList.get(0) ));
				plannedActivityList.add(new PlannedActivity("Planned Activity 2", 30, locationList.get(1) ));
				plannedActivityList.add(new PlannedActivity("Planned Activity 3", 30, locationList.get(2) ));
				
				writeDataToDisk();

			}

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	/**
	 * This method is used to read the file that contains data upon the creation 
	 * of the Data object.
	 * 
	 * @param readData The input stream that from the file to be read.
	 */
	private void readDataFromDisk(ObjectInputStream readData){
		
		try {
			Data data = (Data) readData.readObject();
			
			groupList = data.groupList;
			presetList = data.presetList;
			locationList = data.locationList;
			randomActivityList = data.randomActivityList;
			plannedActivityList = data.plannedActivityList;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * This method writes information stored in the data object to the disk.
	 * 
	 */
	public void writeDataToDisk(){
		
		ObjectOutputStream writeData;
		
		FileOutputStream fileToObjectStream;
		try {
			fileToObjectStream = new FileOutputStream(FILE, false);
			writeData = new ObjectOutputStream(fileToObjectStream);
			writeData.writeObject(this);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The method will get a reference to the data singleton.
	 * 
	 * @return Returns a reference to the data singleton.
	 */
	public static Data getData(){
		return DATA;
	}
	
	/**
	 * This is the accessor for the preset lists.
	 * 
	 * @return Returns a reference to the Preset list
	 */
	public ArrayList<Preset> getPresetList(){
		return presetList;
	}
	
	/**
	 * This is the accessor for the groups lists.
	 * 
	 * @return Returns a reference to the Group list
	 */
	public ArrayList<Group> getGroupList(){
		return groupList;		
	}
	
	/**
	 * This is the accessor for the location lists.
	 * 
	 * @return Returns a reference to the Location list
	 */
	public ArrayList<Location> getLocationList(){
		return locationList;		
	}
	
	/**
	 * This is the accessor for the random activity list.
	 * 
	 * @return Returns a reference to the Random activities list
	 */
	public ArrayList<RandomActivity> getRandomActivityList(){
		return randomActivityList;		
	}
	
	/**
	 * This is the accessor for the planned activity list.
	 * 
	 * @return Returns a reference to the Planned activities list
	 */
	public ArrayList<PlannedActivity> getPlannedActivityList(){
		return plannedActivityList;		
	}
}
