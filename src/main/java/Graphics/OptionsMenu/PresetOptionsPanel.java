package Graphics.OptionsMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Backend.TimeConverter;
import Backend.Updateable;
import DataTypes.Group;
import DataTypes.Preset;
import Resources.Data;

public class PresetOptionsPanel extends JPanel implements Updateable{

	private Graphics.OptionsMenu.PresetOptionsPanel.PresetPointer presetPointer;
	private static final String TIMING_SETUP = "Timing Setup";
	private static final String ACTIVITY_PLAN = "Activity Plan";
	
	private JTextField intervalField;
	private JTextField startHourField;
	private JTextField startMinuteField;
	private JComboBox startBox;
	private JTextField slotField;
	private JButton applyButton;
	private JTabbedPane rightPane;
	private JPanel rightTimePanel;
	private JPanel leftPanel;
	private JLabel selectLabel;
	private JPanel selectPanel;
	private JButton deleteButton;
	private JTable activityPlanTable;
	private JScrollPane activityPlanPane;
	
	private DocumentListener minuteStartDocListener = null;
	private DocumentListener hourStartDocListener = null;
	private DocumentListener intervalDocListener = null;
	private DocumentListener slotDocListener = null;
	
	private final PresetOptionsPanel thisReference = this;
	private final ArrayList<PresetPanel> presetPanelList = new ArrayList<PresetPanel>();
	
	// true means it will update
	private boolean updateRightPanelFlag = false;
	
	public PresetOptionsPanel(){
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// left panel creation
		leftPanel = new JPanel();
		JPanel addPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JLabel presetLabel = new JLabel("Presets");
		presetLabel.setFont(Resources.Fonts.HEADER_TITLE);
		
		final JTextField addField = new JTextField(9);
		JButton addButton = new JButton("Add");
		addField.setFont(Resources.Fonts.STANDARD);
		addButton.setFont(Resources.Fonts.BUTTON_STANDARD);		
		
		final JPanel addPresetScrollPanel = new JPanel();
		addPresetScrollPanel.setLayout(new BoxLayout(addPresetScrollPanel, BoxLayout.Y_AXIS));
		final JScrollPane addPresetScrollPane = new JScrollPane(addPresetScrollPanel);
		addPresetScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		leftPanel.setPreferredSize(Resources.Layout.QUAD_PANEL);
		addPanel.setPreferredSize(Resources.Layout.QUAD_ADDER_PANEL);
		addPresetScrollPane.setPreferredSize(Resources.Layout.QUAD_SCROLL_PANEL_ADDER);
		
		// add to panel
		addPanel.add(addField);
		addPanel.add(addButton);
		
		presetLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		leftPanel.add(presetLabel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(addPanel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(addPresetScrollPane);
		leftPanel.add(Box.createVerticalGlue());
		
		// right panel creation
		rightPane = new JTabbedPane();
		rightPane.setPreferredSize(new Dimension(540,279));
		rightPane.setFont(Resources.Fonts.BUTTON_STANDARD);
		
		JPanel timePanel = new JPanel();
		JPanel activityPanel = new JPanel();
		
		rightPane.addTab(TIMING_SETUP, timePanel);
		rightPane.addTab(ACTIVITY_PLAN, activityPanel);
		
		timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
		
		// timing panel setup
		JPanel leftTimePanel = new JPanel();
		rightTimePanel = new JPanel();
		rightTimePanel.setLayout(new BoxLayout(rightTimePanel, BoxLayout.Y_AXIS));
		JScrollPane rightTimePane = new JScrollPane(rightTimePanel);
		rightTimePane.getVerticalScrollBar().setUnitIncrement(16);
		leftTimePanel.setLayout(new BoxLayout(leftTimePanel, BoxLayout.Y_AXIS));
		
		selectPanel = new JPanel();
		selectLabel = new JLabel("");
		selectPanel.add(selectLabel);
		selectPanel.setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		JPanel deleteButtonPanel = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButtonPanel.add(deleteButton);
		
		JPanel startPanel = new JPanel();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
		JLabel startLabel = new JLabel("Start Time");
		JPanel startFieldPanel = new JPanel();
		startFieldPanel.setLayout(new BoxLayout(startFieldPanel, BoxLayout.X_AXIS));
		startHourField = new JTextField(3);
		startMinuteField = new JTextField(3);
		startBox = new JComboBox();
		JLabel colonLabel = new JLabel(":");
		startHourField.setMaximumSize(new Dimension(40,38));
		startMinuteField.setMaximumSize(new Dimension(40,38));
		startBox.setMaximumSize(new Dimension(60,56));
		startFieldPanel.add(Box.createHorizontalGlue());
		startFieldPanel.add(startHourField);
		startFieldPanel.add(Box.createHorizontalGlue());
		startFieldPanel.add(colonLabel);
		startFieldPanel.add(Box.createHorizontalGlue());
		startFieldPanel.add(startMinuteField);
		startFieldPanel.add(Box.createHorizontalGlue());
		startFieldPanel.add(Box.createHorizontalGlue());
		startFieldPanel.add(startBox);
		startFieldPanel.add(Box.createHorizontalGlue());
		startPanel.add(startLabel);
		startPanel.add(startFieldPanel);
		
		startBox.addItem("AM");
		startBox.addItem("PM");
		
		JLabel intervalLabel = new JLabel("Interval Time");
		JPanel intervalPanel = new JPanel();
		intervalField = new JTextField(2);
		JLabel intervalMins = new JLabel("Minutes");
		intervalPanel.add(intervalField);
		intervalPanel.add(intervalMins);
		
		JPanel slotPanel = new JPanel();
		JLabel slotLabel = new JLabel("Number of slots");
		JLabel slots = new JLabel("Slots");
		slotField = new JTextField(2);
		applyButton = new JButton("Apply");
		applyButton.setMinimumSize(new Dimension(120,70));
		applyButton.setMaximumSize(new Dimension(120,70));
		applyButton.setPreferredSize(new Dimension(120,70));
		slotPanel.add(slotField);
		slotPanel.add(slots);
		addButton.setMinimumSize(new Dimension(300,150));
		
		// left panel fonts
		deleteButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		selectLabel.setFont(Resources.Fonts.BOLD_SMALL_TITLE);
		startLabel.setFont(Resources.Fonts.SMALL_TITLE);
		startHourField.setFont(Resources.Fonts.STANDARD);
		startMinuteField.setFont(Resources.Fonts.STANDARD);
		startBox.setFont(Resources.Fonts.STANDARD);
		intervalLabel.setFont(Resources.Fonts.SMALL_TITLE);
		intervalField.setFont(Resources.Fonts.STANDARD);
		intervalMins.setFont(Resources.Fonts.STANDARD);
		slotLabel.setFont(Resources.Fonts.SMALL_TITLE);
		slots.setFont(Resources.Fonts.STANDARD);
		slotField.setFont(Resources.Fonts.STANDARD);
		applyButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		
		// add to left panel
		
		leftTimePanel.add(Box.createVerticalGlue());
		leftTimePanel.add(selectPanel);
		leftTimePanel.add(Box.createVerticalGlue());
		leftTimePanel.add(deleteButtonPanel);
		leftTimePanel.add(Box.createVerticalGlue());
		leftTimePanel.add(startPanel);
		leftTimePanel.add(Box.createVerticalGlue());
		leftTimePanel.add(intervalLabel);
		leftTimePanel.add(intervalPanel);
		leftTimePanel.add(slotLabel);
		leftTimePanel.add(slotPanel);
		leftTimePanel.add(Box.createVerticalGlue());
		leftTimePanel.add(applyButton);
		leftTimePanel.add(Box.createVerticalGlue());
		
		// center of the universe
		selectPanel.setAlignmentX(CENTER_ALIGNMENT);
		selectLabel.setAlignmentX(CENTER_ALIGNMENT);
		startLabel.setAlignmentX(CENTER_ALIGNMENT);
		deleteButtonPanel.setAlignmentX(CENTER_ALIGNMENT);
		startFieldPanel.setAlignmentX(CENTER_ALIGNMENT);
		intervalLabel.setAlignmentX(CENTER_ALIGNMENT);
		intervalPanel.setAlignmentX(CENTER_ALIGNMENT);
		slotLabel.setAlignmentX(CENTER_ALIGNMENT);
		slotField.setAlignmentX(CENTER_ALIGNMENT);
		applyButton.setAlignmentX(CENTER_ALIGNMENT);
		
		presetPointer = new PresetPointer(Data.getData().getPresetList().get(0));
		
		// activity plan panel
		//Vector 
		
		
		// set time panel sizes
		leftTimePanel.setPreferredSize(new Dimension(180,279));
		rightTimePane.setPreferredSize(new Dimension(360,279));
		
		// add to time panel
		timePanel.add(leftTimePanel);
		timePanel.add(rightTimePane);
		
		// add the panels
		add(leftPanel);
		add(rightPane);
		
		// listeners
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(addField.getText().length() > 0){
					Preset newPreset = new Preset(addField.getText(), Data.getData().getGroupList());
					Data.getData().getPresetList().add(newPreset);
					PresetPanel presetPanel = new PresetPanel(newPreset, addPresetScrollPane, presetPointer, thisReference);
					addPresetScrollPanel.add(presetPanel);
					addField.setText("");
					presetPanelList.add(presetPanel);
					validate();
				}	
			}
		};
		
		addField.addActionListener(addListener);
		addButton.addActionListener(addListener);
		
		for(Preset preset : Data.getData().getPresetList()){
			PresetPanel presetPanel = new PresetPanel(preset, addPresetScrollPane, presetPointer, this);
			addPresetScrollPanel.add(presetPanel);
			presetPanelList.add(presetPanel);
		}
		
		updateRightPanel();
		validate();
		
	}
	
	public void update(){
		//System.err.println("update" + updateRightPanelFlag);
		if(updateRightPanelFlag){
			updateTime();
			presetPointer.preset.setSlotNumber(Integer.parseInt("0" + slotField.getText()));
			presetPointer.preset.setIntervalTime((Integer.parseInt("0" + intervalField.getText())));
			updateRightPanel();
		}
		
	}
	
	public void selectTimingSetupPanel(){
		rightPane.setSelectedIndex(0);
	}
	
	private void updateTime(){
		
		LocalTime startTime = TimeConverter.stringToLocalTime(startHourField.getText(), startMinuteField.getText(), startBox.getSelectedItem().toString().equals("AM") ? true : false);
		presetPointer.preset.setStartTime(startTime);
		
	}
	
	public void updateRightPanel(){
		
		updateRightPanelFlag = false;
		
		if(applyButton.getActionListeners().length > 0){
			for(ActionListener listen : applyButton.getActionListeners()){
				applyButton.removeActionListener(listen);
				
			}
		}
		
		if(deleteButton.getActionListeners().length > 0){
			for(ActionListener listen : deleteButton.getActionListeners()){
				deleteButton.removeActionListener(listen);
				
			}
		}
			
		if(startBox.getActionListeners().length > 0){
			for(ActionListener listen : startBox.getActionListeners()){
				startBox.removeActionListener(listen);
			}
		}
		
		PresetPanel foundPanelToRemove = null;
		
		for(PresetPanel currentPresetPanel : presetPanelList){
			if(currentPresetPanel.getPreset() == presetPointer.preset){
				foundPanelToRemove = currentPresetPanel;
				break;
			}
		}
		
		selectLabel.setText(presetPointer.preset.getName());
		final PresetPanel panelToRemove = foundPanelToRemove;	
		
		// start time
		int hourTime = presetPointer.preset.getStartTime().getHour();
		startHourField.setHorizontalAlignment(SwingConstants.CENTER);
		startMinuteField.setHorizontalAlignment(SwingConstants.CENTER);
		intervalField.setHorizontalAlignment(SwingConstants.CENTER);
		slotField.setHorizontalAlignment(SwingConstants.CENTER);
		
		boolean morningAM = true;
		if(hourTime > 12){
			hourTime -= 12;
			morningAM = false;
		}
		if(hourTime == 12)
			morningAM = false;
		if(hourTime == 0)
			hourTime += 12;
		
		
		startHourField.setText(Integer.toString(hourTime));
		startMinuteField.setText(Integer.toString(presetPointer.preset.getStartTime().getMinute()).length() == 1 ?
				"0" + Integer.toString(presetPointer.preset.getStartTime().getMinute()) : 
						Integer.toString(presetPointer.preset.getStartTime().getMinute()));
		if(morningAM)
			startBox.setSelectedIndex(0);
		else
			startBox.setSelectedIndex(1);
		
		intervalField.setText(Integer.toString(presetPointer.preset.getIntervalTime()));
		slotField.setText(Integer.toString(presetPointer.preset.getSlotNumber()));
		
		applyButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean check = true;
				if(!validate(startHourField, 12, 1)){
					check = false;
					startHourField.setForeground(Color.red);
				}
				else{
					startHourField.setForeground(Resources.Colors.TEXT);
				}
				if(!validate(startMinuteField, 59, 0)){
					check = false;
					startMinuteField.setForeground(Color.red);
				}
				else{
					startMinuteField.setForeground(Resources.Colors.TEXT);
				}
				if(!validate(intervalField, 720, 1)){
					check = false;
					intervalField.setForeground(Color.red);
				}
				else{
					intervalField.setForeground(Resources.Colors.TEXT);
				}
				if(!validate(slotField, 99, 1)){
					check = false;
					slotField.setForeground(Color.red);
				}
				else{
					slotField.setForeground(Resources.Colors.TEXT);
				}
				
				if(check)
					update();
			}
			
			private boolean validate(JTextField field, int maxNumber, int minNumber){
				try{
					int currentNumber = Integer.parseInt(field.getText());
					if(currentNumber <= maxNumber && currentNumber >= minNumber)
						return true;
					return false;
				}
				catch(NumberFormatException e) {
					return false;
				}				
			}			
		});
		
		deleteButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Data.getData().getPresetList().remove(presetPointer.preset);
				panelToRemove.getParent().remove(panelToRemove);
				presetPointer.preset = presetPanelList.get(0).getPreset();
				updateRightPanel();
				leftPanel.validate();
			}
			
		});
		
		startBox.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				updateTime();
				updateRightPanel();
			}
			
		});
		
		// update preview on the right panel
		
		rightTimePanel.removeAll();
		ArrayList<TimePanel> timePanelList = TimePanel.getTimePanelList(presetPointer.preset);
		for(TimePanel timePanel : timePanelList){
			rightTimePanel.add(timePanel);
		}
		
		updateRightPanelFlag = true;
		validate();
	}
	
	public class PresetPointer{
		
		public Preset preset;
		
		public PresetPointer(Preset preset){
			this.preset = preset;
		}	
	}
	
}
