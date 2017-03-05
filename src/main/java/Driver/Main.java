package Driver;

import Graphics.MainMenu.MenuWindow;

public class Main {

	public static void main(String[] args) {

		
		
		MenuWindow menu = new MenuWindow(new Graphics.MainMenu.MenuWindowListener(){
			
			public void windowClosed(){
				System.exit(0);
			}
			
		});
		
	}

}
