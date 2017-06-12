package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataTypes.Location;
import DataTypes.RandomActivity;
import Resources.Data;

public class RandomActivityOptionsPanel extends JPanel {

	private JTextField addField;
	private JButton addButton;
	private JPanel addScrollPanel;
	private JScrollPane addScrollPane;
	private ArrayList<RandomActivityPanel> randomActivityPanelList = new ArrayList<RandomActivityPanel>();
	private JTextField nameField;
	private JButton applyButton;
	private JButton deleteButton;
	private LocationSelectorPanel centerPanel;
	private GroupSelectorPanel rightPanel;
	
	private RandomActivity currentRandomActivity;
	
	public RandomActivityOptionsPanel(){
		
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3));
		
		// Sub-panel creation
		JPanel leftPanel = new JPanel();
		
		//TODO: no location and no group cases
		centerPanel = new LocationSelectorPanel(Data.getData().getRandomActivityList().get(0), this);
		rightPanel = new GroupSelectorPanel(Data.getData().getRandomActivityList().get(0), this, currentRandomActivity, centerPanel);
		
		leftPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		centerPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		centerPanel.setMinimumSize(Resources.Layout.TRIPLE_PANEL);
		rightPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		rightPanel.setMinimumSize(Resources.Layout.TRIPLE_PANEL);
		
		// left panel setup
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JLabel activityLabel = new JLabel("    Random Activities");
		JPanel addPanel = new JPanel();
		addButton = new JButton("Add");
		addField = new JTextField(12);
		addScrollPanel = new JPanel();
		addScrollPanel.setLayout(new BoxLayout(addScrollPanel, BoxLayout.Y_AXIS));
		addScrollPane = new JScrollPane(addScrollPanel);
		addScrollPane.setAlignmentX(CENTER_ALIGNMENT);
		addScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		addPanel.setPreferredSize(Resources.Layout.TRIPLE_ADDER_PANEL);
		addScrollPane.setPreferredSize(Resources.Layout.TRIPLE_SCROLL_PANEL_ADDER);
		
		// left panel text standardizing
		activityLabel.setFont(Resources.Fonts.HEADER_TITLE);
		addButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		addField.setFont(Resources.Fonts.STANDARD);
		
		addPanel.add(addField);
		addPanel.add(addButton);
		
		
		leftPanel.add(addPanel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(addScrollPane);
		leftPanel.add(Box.createVerticalGlue());
		
		mainPanel.add(leftPanel);
		mainPanel.add(centerPanel);
		mainPanel.add(rightPanel);
		
		add(activityLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		for(final RandomActivity randomActivity : Data.getData().getRandomActivityList()){
			
			
			if(currentRandomActivity == null){
				currentRandomActivity = randomActivity;
			}
			RandomActivityPanel randomActivityPanel = new RandomActivityPanel(randomActivity, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					selectRandomActivity(randomActivity);
				}
			});
			
			addScrollPanel.add(randomActivityPanel);
			randomActivityPanelList.add(randomActivityPanel);
		}
		
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(addField.getText().length() > 0){
					final RandomActivity newRandomActivity = new RandomActivity(addField.getText(), Data.getData().getLocationList(), Data.getData().getGroupList());
					Data.getData().getRandomActivityList().add(newRandomActivity);
					RandomActivityPanel randomActivityPanel = new RandomActivityPanel(newRandomActivity, new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							selectRandomActivity(newRandomActivity);
						}
					});
					addScrollPanel.add(randomActivityPanel);
					addField.setText("");
					validate();
				}
			}
		};
		
		
		addButton.addActionListener(addListener);
		addField.addActionListener(addListener);
		
		validate();
		
	}

	private void selectRandomActivity(RandomActivity randomActivity) {
		rightPanel.setCurrentRandomActivity(randomActivity);
		centerPanel.setCurrentRandomActivity(randomActivity);
		
	}
	
	public void updateRandomActivityList(RandomActivity currentRandomActivity) {
		for(RandomActivityPanel panel : randomActivityPanelList){
			if(panel.getCurrentRandomActivity().equals(currentRandomActivity)){
				remove(panel);
			} else {
				panel.updateRandomActivity();
			}
		}
		validate();
	}

}
