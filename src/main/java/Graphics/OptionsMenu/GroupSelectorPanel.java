package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataTypes.Group;
import DataTypes.Location;
import DataTypes.RandomActivity;
import Graphics.OptionsMenu.LocationSelectorPanel.GetLocationSelectorInfo;
import Resources.Data;

public class GroupSelectorPanel extends JPanel {

	private JScrollPane groupScrollPane;
	private ArrayList<GroupCheckBox> groupList = new ArrayList<GroupCheckBox>();
	private RandomActivity randomActivity;
	
	private JPanel scrollPanel;
	
	private JButton applyButton;
	private JButton deleteButton;
	private JPanel buttonPanel;
	private RandomActivity currentRandomActivity;
	private ActionListener saveListener;
	private ActionListener deleteListener;
	private RandomActivityOptionsPanel randomActivityOptionsPanel;
	private LocationSelectorPanel locationSelectorPanel;
	
	public GroupSelectorPanel(RandomActivity currentRandomActivity, RandomActivityOptionsPanel randomActivityOptionsPanel, RandomActivity randomActivity, LocationSelectorPanel locationSelectorPanel){
		this.randomActivityOptionsPanel = randomActivityOptionsPanel;
		this.randomActivity = randomActivity;
		this.currentRandomActivity = currentRandomActivity;
		this.locationSelectorPanel = locationSelectorPanel;
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		applyButton = new JButton("Apply");
		deleteButton = new JButton("Delete");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(applyButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());
		add(buttonPanel);
		
		scrollPanel = new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
		groupScrollPane = new JScrollPane(scrollPanel);
		add(groupScrollPane);
		
		for(Group group : Data.getData().getGroupList()){
			GroupCheckBox groupCheckBox = new GroupCheckBox(group, randomActivity);
			scrollPanel.add(groupCheckBox);
			groupList.add(groupCheckBox);
		}
		groupScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		validate();
	}
	
	class GetGroupSelectorInfo{
		
		public ArrayList<Group> groupList;
		public GetGroupSelectorInfo(ArrayList<GroupCheckBox> groupCheckBoxList){
			for(GroupCheckBox groupCheck : groupCheckBoxList){
				if(groupCheck.isSelected()){
					groupList.add(groupCheck.group);
				}
			}
		}
	}
	
	public void updateRandomActivity(RandomActivity randomActivity){
		
		this.randomActivity = randomActivity;
		for(GroupCheckBox groupBoxes : groupList){
			groupBoxes.updateRandomActivity(randomActivity);
			
		}
	}
	
	public void setCurrentRandomActivity(RandomActivity randomActivity){
		applyButton.removeActionListener(saveListener);
		deleteButton.removeActionListener(deleteListener);

		currentRandomActivity = randomActivity;
		
		saveListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GetLocationSelectorInfo locationSelector = locationSelectorPanel.getLocationSelectorInfo();
				GetGroupSelectorInfo groupSelector = new GetGroupSelectorInfo(groupList);
				currentRandomActivity.setName(locationSelector.name);
				currentRandomActivity.setLocation(locationSelector.location);
				currentRandomActivity.setGroup(groupSelector.groupList);
				randomActivityOptionsPanel.updateRandomActivityList(currentRandomActivity);
			}
		};
		deleteListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Data.getData().getRandomActivityList().remove(Data.getData().getRandomActivityList().indexOf(currentRandomActivity));
				randomActivityOptionsPanel.updateRandomActivityList(currentRandomActivity);
			}
		};
		
		applyButton.addActionListener(saveListener);
		deleteButton.addActionListener(deleteListener);
	}
	
	private class GroupCheckBox extends JCheckBox{
		
		private Group group;
		private RandomActivity randomActivity;
		
		public GroupCheckBox(Group group, RandomActivity randomActivity){
			
			super(group.getName(), randomActivity.groupIsIngroupList(group));
			
			this.group = group;
			this.randomActivity = randomActivity;
			
		}
		
		public void updateRandomActivity(RandomActivity randomActivity){
			
			this.randomActivity = randomActivity;
			setSelected(randomActivity.groupIsIngroupList(group));
			
		}
	}
}
