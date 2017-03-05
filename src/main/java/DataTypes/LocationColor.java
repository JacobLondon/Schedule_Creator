package DataTypes;

import java.awt.Color;
import java.io.Serializable;

public class LocationColor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Color color;
	private boolean anyLocation;
	
	public LocationColor(Color color){
		
		this.color = color;
		anyLocation = color == null;
		
	}

	public LocationColor(){
		
		color = null;
		anyLocation = true;
		
	}

	public Color getColor(){
		
		return color;
		
	}
	
	public void setColor(Color color){
		
		this.color = color;
		anyLocation = color == null;
		
	}
	
	public boolean getAnyLocation(){
		
		return anyLocation;
		
	}
	
}
