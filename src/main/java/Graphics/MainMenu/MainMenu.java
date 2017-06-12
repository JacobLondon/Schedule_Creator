package Graphics.MainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import DataTypes.Preset;
import Resources.Data;

public class MainMenu extends JPanel {
	
	private JButton generateScheduleButton;
	private JButton optionsButton;
	private JComboBox<Preset> dayPickerBox;
	
	private Data data = Data.getData();
	
	public MainMenu(final MenuWindow window){
		super();
		
		// Button creation
		generateScheduleButton = new JButton("Generate Schedule");
		optionsButton = new JButton("Options");
		
		Preset[] presetArray = new Preset[data.getPresetList().size()];
		dayPickerBox = new JComboBox<Preset>(data.getPresetList().toArray(presetArray));
		
		generateScheduleButton.setFont(Resources.Fonts.BUTTON_TITLE);
		optionsButton.setFont(Resources.Fonts.BUTTON_TITLE);
		dayPickerBox.setFont(Resources.Fonts.BUTTON_TITLE);
		
		generateScheduleButton.setForeground(Resources.Colors.TEXT);
		optionsButton.setForeground(Resources.Colors.TEXT);
		dayPickerBox.setForeground(Resources.Colors.TEXT);
		
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				window.openOptionsMenu();
			}
		});
		
		generateScheduleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				window.openGeneratedScheduleWindow((Preset)(dayPickerBox.getSelectedItem()));
			}
		});
		
		// menu spacing / sizing
		//optionsButton.
		//generateScheduleButton.
		
		optionsButton.setPreferredSize(Resources.Layout.MENU_BUTTON);
		generateScheduleButton.setPreferredSize(Resources.Layout.MENU_BUTTON);
		
		optionsButton.setMaximumSize(Resources.Layout.MENU_BUTTON);
		generateScheduleButton.setMaximumSize(Resources.Layout.MENU_BUTTON);
		dayPickerBox.setMaximumSize(Resources.Layout.DAY_BOX);
		
		optionsButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		generateScheduleButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		dayPickerBox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		optionsButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		generateScheduleButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		dayPickerBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		setLayout(new BorderLayout());
		
		
		// Create title
		JLabel scheduleCreator = new JLabel("  Schedule Creator");
		scheduleCreator.setBackground(Resources.Colors.TITLE_BACKGROUND);
		scheduleCreator.setForeground(Resources.Colors.TEXT);
		scheduleCreator.setFont(Resources.Fonts.MAIN_TITLE);
		scheduleCreator.setPreferredSize(Resources.Layout.TOOLBAR);
		add(scheduleCreator, BorderLayout.NORTH);
		
		// Layout menu buttons
		JPanel menuCenterPanel = new JPanel();
		menuCenterPanel.setBackground(Resources.Colors.PANEL_BACKGROUND);
		menuCenterPanel.setLayout(new BoxLayout(menuCenterPanel, BoxLayout.Y_AXIS));
		
		JPanel spaceArea = new JPanel();
		spaceArea.setPreferredSize(new Dimension(80,390));
		spaceArea.setBackground(Resources.Colors.PANEL_BACKGROUND);
		add(spaceArea, BorderLayout.WEST);
		
		add(menuCenterPanel, BorderLayout.CENTER);
		
		menuCenterPanel.add(Box.createVerticalGlue());
		menuCenterPanel.add(generateScheduleButton);
		menuCenterPanel.add(Box.createVerticalGlue());
		menuCenterPanel.add(optionsButton);
		menuCenterPanel.add(Box.createVerticalGlue());
		menuCenterPanel.add(dayPickerBox);
		menuCenterPanel.add(Box.createVerticalGlue());
		
	}

	public void updateDayPickerBox(){
		dayPickerBox.setModel(new DefaultComboBoxModel(data.getPresetList().toArray()));
		
	}
	
}
