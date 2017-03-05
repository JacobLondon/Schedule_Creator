import java.io.File;
import java.util.ArrayList;

import DataTypes.Group;
import DataTypes.Preset;
import Resources.Data;

public class Tester {

	public static void main(String[] args) {
		
		File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") +
				"Schedule Creator" + System.getProperty("file.separator") + "data");
		
		Data.getData();
		System.err.println(file);
		System.err.println(file.canWrite());
	}

}
