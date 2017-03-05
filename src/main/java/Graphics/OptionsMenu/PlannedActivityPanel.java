package Graphics.OptionsMenu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataTypes.PlannedActivity;

public class PlannedActivityPanel extends JPanel {

	private JLabel plannedActivityLabel;
	
	public PlannedActivityPanel(PlannedActivity plannedActivity){
		super();
		setLayout(new GridLayout(1,2));
		
		plannedActivityLabel = new JLabel(plannedActivity.getName());
		
		JButton useButton = new JButton("Select");
		
		plannedActivityLabel.setFont(Resources.Fonts.SMALL_TITLE);
		useButton.setFont(Resources.Fonts.BUTTON_STANDARD);
		
		plannedActivityLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		add(Box.createHorizontalGlue());
		add(plannedActivityLabel);
		add(Box.createHorizontalGlue());
		add(useButton);
		//add(Box.createHorizontalGlue());
		
		useButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		setMinimumSize(new Dimension(200,450));
		setPreferredSize(new Dimension(354,69));
	}
	
}
