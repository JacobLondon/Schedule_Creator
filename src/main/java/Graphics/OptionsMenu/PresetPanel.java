package Graphics.OptionsMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataTypes.Preset;
import Graphics.OptionsMenu.PresetOptionsPanel.PresetPointer;
import Resources.Fonts;

public class PresetPanel extends JPanel {

	private Preset preset;
	
	public PresetPanel(final Preset preset, final JScrollPane parentPane, final PresetPointer presetPointer, final PresetOptionsPanel presetOptionsPanel){
		super();
		this.preset = preset;
		
		JLabel nameLabel = new JLabel(preset.getName());	
		JButton useButton = new JButton("Select");
		
		nameLabel.setFont(Fonts.STANDARD);
		useButton.setFont(Fonts.BUTTON_STANDARD);
		
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		setLayout(new BorderLayout());
		
		add(nameLabel, BorderLayout.WEST);
		add(useButton, BorderLayout.EAST);
		
		this.setMinimumSize(new Dimension(200,100));
		
		setBorder(BorderFactory.createLineBorder(Resources.Colors.PANEL_BORDER));
		
		useButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				presetPointer.preset = preset;
				presetOptionsPanel.updateRightPanel();
				presetOptionsPanel.selectTimingSetupPanel();
			}
			
		});
	}
	
	public Preset getPreset(){
		return preset;
	}
	
}
