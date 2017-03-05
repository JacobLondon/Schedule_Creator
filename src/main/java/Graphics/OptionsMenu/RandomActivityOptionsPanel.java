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
	
	
	public RandomActivityOptionsPanel(){
		
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,3));
		
		// Sub-panel creation
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		centerPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		rightPanel.setPreferredSize(Resources.Layout.TRIPLE_PANEL);
		
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
		
		for(RandomActivity randomActivity : Data.getData().getRandomActivityList()){
			RandomActivityPanel randomActivityPanel = new RandomActivityPanel(randomActivity);
			addScrollPanel.add(randomActivityPanel);
			randomActivityPanelList.add(randomActivityPanel);
		}
		
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(addField.getText().length() > 0){
					RandomActivity newRandomActivity = new RandomActivity(addField.getText(), Data.getData().getLocationList());
					Data.getData().getRandomActivityList().add(newRandomActivity);
					RandomActivityPanel randomActivityPanel = new RandomActivityPanel(newRandomActivity);
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
	
}
