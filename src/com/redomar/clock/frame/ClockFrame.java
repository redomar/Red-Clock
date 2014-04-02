package com.redomar.clock.frame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ClockFrame extends Canvas {

	private static final long serialVersionUID = 1L;

	private static JFrame frame;

	/*
	 * This creates the Application window. This can be used for other windows
	 * too.
	 */
	public ClockFrame(int WIDTH, int HEIGHT, int SCALE, String NAME) {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		setFrame(new JFrame(NAME));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setLayout(new BorderLayout());
		getFrame().add(this, BorderLayout.CENTER);
		getFrame().pack();
		getFrame().setResizable(false);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		ClockFrame.frame = frame;
	}

	// This stops the displaying of the frame/window.
	public void stopFrame() {
		getFrame().dispose();
	}

}
