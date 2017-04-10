package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Backend.Updateable;
import Backend.FieldDocuments.IntegerInputDocument;
import DataTypes.Group;
import Resources.Data;
import Resources.Fonts;

public class GroupPanel extends JPanel{

	private Group group;
	
	public GroupPanel(final Group group, final JScrollPane parentPane){
		super();
		this.group = group;
		
		final GroupPanel thisReference = this;
		
		/*
		 * name
		 * label
		 * number of subgroups
		 * delete button
		 */
		
		JLabel nameLabel = new JLabel(group.getName());
		JLabel subgroupLabel = new JLabel("Number of subgroups");
		
		final JTextField subgroupField = new JTextField(3);	
		subgroupField.setDocument(new IntegerInputDocument(2));
		subgroupField.setText("" +  group.getSubGroupCount());
		
		
		JButton deleteButton = new JButton("Delete");
		
		nameLabel.setFont(Fonts.STANDARD);
		subgroupLabel.setFont(Fonts.STANDARD);
		subgroupField.setFont(Fonts.STANDARD);
		deleteButton.setFont(Fonts.BUTTON_STANDARD);
		
		setLayout(new BorderLayout());
		
		JPanel subgroupPanel = new JPanel();
		JPanel gridPanel = new JPanel();
		
		subgroupPanel.add(subgroupLabel);
		subgroupPanel.add(subgroupField);
		
		gridPanel.setLayout(new GridLayout(2,1));
		gridPanel.add(nameLabel);
		gridPanel.add(subgroupPanel);
		
		add(gridPanel, BorderLayout.WEST);
		add(deleteButton, BorderLayout.EAST);
		
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		deleteButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				int groupIndex = Data.getData().getGroupList().indexOf(group);
				for(int traverse = 0; traverse < Data.getData().getPresetList().size(); traverse++){
					Data.getData().getPresetList().get(traverse).removeColumn(groupIndex);
				}
				
				thisReference.getParent().remove(thisReference);
				Data.getData().getGroupList().remove(group);
				parentPane.validate();
			}
			
		});
		
		subgroupField.getDocument().addDocumentListener(new DocumentListener(){

			public void insertUpdate(DocumentEvent e) {
				update();
			}

			public void removeUpdate(DocumentEvent e) {
				update();
			}

			public void changedUpdate(DocumentEvent e) {
				update();
			}
			
			public void update(){
				try{
					int newSubgroupCount = Integer.parseInt(subgroupField.getText());
					if(newSubgroupCount > 0){
						group.setSubGroupCount(newSubgroupCount);
					}
				} catch(NumberFormatException e) {
					
				}
				
			}
			
		});
	}
	
}
