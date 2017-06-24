package Graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import DataTypes.Preset;
import Graphics.OnCloseCallback;
import Resources.Data;

public class ScheduleWindow extends JFrame {
	
	private OnCloseCallback onCloseCallback;
	private Data data = Data.getData();
	private SchedulePanel panel;
	
	public ScheduleWindow(Preset preset, OnCloseCallback onCloseCallback) {
		this.onCloseCallback = onCloseCallback;
		
		setLayout(new BorderLayout());
		try {
			panel = new SchedulePanel(preset, data);
			JScrollPane scrollPane = new JScrollPane(panel);
			add(scrollPane, BorderLayout.CENTER);
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			setVisible(true);
			pack();
			setLocationRelativeTo(null);
		} catch(NotEnoughRandomActivitiesException e){
			JOptionPane.showMessageDialog(this,
					e.getMessage(),
				    "Could Not Generate Schedule",
				    JOptionPane.WARNING_MESSAGE);
			dispose();
		}
		
		
	}

	@Override
	public void dispose()
	{
		super.dispose();
		onCloseCallback.onClose();
	}
}
