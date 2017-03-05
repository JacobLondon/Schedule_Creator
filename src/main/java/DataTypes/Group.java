package DataTypes;

import java.io.Serializable;

public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int subGroupCount;
	
	public Group(String name ,int subGroupCount){
		
		this.name = name;
		this.subGroupCount = subGroupCount;
		
	}
	
	public int getSubGroupCount(){
		
		return subGroupCount;
	}
	public void setSubGroupCount(int amount){
		
		subGroupCount = amount;
	}
	
	public String getName(){
		
		return name;
	}
	public void setName(String name){
		
		this.name = name;
	}
}
