package Graphics.OptionsMenu.CampInfoTab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DataTypes.Group;
import Resources.Data;
import Resources.Images;

public class CampInfoPanel extends JPanel {

	public CampInfoPanel(){
		
		super();
		setLayout(new GridLayout(1,2));
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		// sizing
		leftPanel.setPreferredSize(Resources.Layout.DOUBLE_PANEL);
		rightPanel.setPreferredSize(Resources.Layout.DOUBLE_PANEL);
		leftPanel.setMaximumSize(Resources.Layout.DOUBLE_PANEL);
		rightPanel.setMaximumSize(Resources.Layout.DOUBLE_PANEL);
		leftPanel.setMinimumSize(Resources.Layout.DOUBLE_PANEL);
		rightPanel.setMinimumSize(Resources.Layout.DOUBLE_PANEL);
		
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JLabel campLabel = new JLabel(Resources.CampName.CAMP_NAME);
		campLabel.setFont(Resources.Fonts.HEADER_TITLE);
		leftPanel.add(campLabel);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(Box.createVerticalGlue());
		
		// TODO: Fix in jar 
		JLabel campLogo = new JLabel(new ImageIcon());//Images.getCampLogo()));
		leftPanel.add(campLogo);
		leftPanel.add(Box.createVerticalGlue());
		
		JLabel groupLabel = new JLabel("Groups");
		groupLabel.setFont(Resources.Fonts.HEADER_TITLE);
		final JPanel addPanel = new JPanel();
		addPanel.setPreferredSize(Resources.Layout.DOUBLE_ADDER_PANEL);
		final JTextField addField = new JTextField(15);
		addField.setFont(Resources.Fonts.STANDARD);
		JButton addButton = new JButton("Add");
		
		addButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		addPanel.add(addField);
		addPanel.add(addButton);
		
		final JPanel groupScrollPanel = new JPanel();
		groupScrollPanel.setLayout(new BoxLayout(groupScrollPanel, BoxLayout.Y_AXIS));
		final JScrollPane groupScrollPane = new JScrollPane(groupScrollPanel);
		
		groupScrollPane.setPreferredSize(Resources.Layout.DOUBLE_SCROLL_PANEL_ADDER);
		groupScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		rightPanel.add(groupLabel);
		rightPanel.add(addPanel);
		rightPanel.add(groupScrollPane);
		
		groupLabel.setAlignmentX(CENTER_ALIGNMENT);
		addPanel.setAlignmentX(CENTER_ALIGNMENT);
		groupScrollPane.setAlignmentX(CENTER_ALIGNMENT);
		
		campLabel.setAlignmentX(CENTER_ALIGNMENT);
		campLogo.setAlignmentX(CENTER_ALIGNMENT);
		
		add(leftPanel);
		add(rightPanel);
		
		ActionListener addListener = new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(addField.getText().length() > 0){
					Group newGroup = new Group(addField.getText(), 1);
					Data.getData().getGroupList().add(newGroup);
					GroupPanel groupPanel = new GroupPanel(newGroup, groupScrollPane);
					groupScrollPanel.add(groupPanel);
					
					// loop through the presets and add a column to the end of the preset array
					
					addField.setText("");							
					validate();
				}	
			}
		};
		
		addField.addActionListener(addListener);
		addButton.addActionListener(addListener);
		
		for(Group group : Data.getData().getGroupList()){
			GroupPanel groupPanel = new GroupPanel(group, groupScrollPane);
			groupScrollPanel.add(groupPanel);
		}
		
		validate();
	}
	
}
