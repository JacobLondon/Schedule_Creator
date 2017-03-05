package Graphics.MainMenu;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphics.OptionsMenu.OptionsMenu;
import Resources.Data;

public class MenuWindow extends JFrame {
	
	private static final String MAIN_MENU = "Main";
	private static final String OPTIONS_MENU = "Options";

	private MainMenu mainMenu;
	private OptionsMenu optionsMenu;
	private CardLayout layout;
	
	private Vector<MenuWindowListener> windowListeners = new Vector<MenuWindowListener>();
	
	public MenuWindow(MenuWindowListener windowListener){
		
		super("Schedule Creator");
		mainMenu = new MainMenu(this);
		optionsMenu = new OptionsMenu(this);

		layout = new CardLayout();
		setLayout(layout);
		add(mainMenu, MAIN_MENU);
		add(optionsMenu, OPTIONS_MENU);
		
		setPreferredSize(Resources.Layout.MENU_WINDOW);
		
		pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void openMainMenu(){
		layout.show(getContentPane(), MAIN_MENU);
		mainMenu.updateDayPickerBox();
	}
	
	public void openOptionsMenu(){
		layout.show(getContentPane(), OPTIONS_MENU);
	}
	
	@Override
	public void dispose(){
		
		super.dispose();
		notifyWindowClosed();
		Data.getData().writeDataToDisk();
		
	}

	public void notifyWindowClosed(){
		
		for(MenuWindowListener currentListener : windowListeners){
			currentListener.windowClosed();
		}
		
		
	}
	
}
