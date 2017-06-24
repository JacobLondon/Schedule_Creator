package Resources;

import java.net.URL;

public class Images {
	private static ClassLoader CL = Images.class.getClassLoader();
	private static String LOCATION = "Resources/";

	public static URL getCampLogo(){
		return CL.getResource(LOCATION + "camp_logo.jpg");
	}

}
