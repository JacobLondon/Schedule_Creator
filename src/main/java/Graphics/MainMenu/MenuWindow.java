package Graphics.MainMenu;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataTypes.Preset;
import Graphics.OnCloseCallback;
import Graphics.ScheduleWindow;
import Graphics.OptionsMenu.OptionsMenu;
import Resources.Data;

public class MenuWindow extends JFrame {
	
	private static final String MAIN_MENU = "Main";
	private static final String OPTIONS_MENU = "Options";

	private MainMenu mainMenu;
	private OptionsMenu optionsMenu;
	private CardLayout layout;
	
	
	public MenuWindow(){
		
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
	
	public void openGeneratedScheduleWindow(Preset preset){
		setVisible(false);
		new ScheduleWindow(preset, new OnCloseCallback(){
			public void onClose(){
				setVisible(true);
			}
		});
	}
	
	@Override
	public void dispose(){
		super.dispose();
		Data.getData().writeDataToDisk();
		System.exit(0);
		
	}

	
}
