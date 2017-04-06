package Driver;

import javax.swing.SwingUtilities;

import Graphics.MainMenu.MenuWindow;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MenuWindow menu = new MenuWindow();
			}
		});
		
	}

}
