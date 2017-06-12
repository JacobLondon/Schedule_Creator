package Graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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
		panel = new SchedulePanel(preset, data);
		add(panel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		onCloseCallback.onClose();
	}
}
