package DataTypes;

import java.io.Serializable;

/**
 * This will represent a group of students.
 *
 */
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int subGroupCount;
	
	/**
	 * The constructor for Groups.
	 * 
	 * @param name The name of the group
	 * @param subGroupCount How many subgroups the group splits up into.
	 */
	public Group(String name, int subGroupCount){
		
		this.name = name;
		this.subGroupCount = subGroupCount;
		
	}
	
	/**
	 * An accessor for the amount of subgroups.
	 * 
	 * @return The number of subgroups for the group.
	 */
	public int getSubGroupCount(){
		
		return subGroupCount;
	}
	
	/**
	 * A mutator for the amount of subgroups.
	 * 
	 * @param amount The number of subgroups for the  group.
	 */
	public void setSubGroupCount(int amount){
		
		subGroupCount = amount;
	}
	
	/**
	 * An accessor for the group's name. 
	 * 
	 * @return The groups name.
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * The mutator for the group's name.
	 * 
	 * @param name The name of the group.
	 */
	public void setName(String name){
		
		this.name = name;
	}
}
