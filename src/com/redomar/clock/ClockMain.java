package com.redomar.clock;

import com.redomar.clock.frame.ClockFrame;

public class ClockMain implements Runnable {

	// Application variables. Version and Name etc.
	public final String VERSION = "1.0";
	public final String NAME = "RedClock";

	// Window size variables.
	public final int WIDTH = 150;
	public final int HEIGHT = (WIDTH / 6 * 3);
	public final int SCALE = 3;

	// These these variables are self explanatory.
	public boolean running = false;
	public static ClockFrame frame;

	// When start() is called it starts the application within a thread.
	public synchronized void start() {
		running = true;
		setFrame(new ClockFrame(WIDTH, HEIGHT, SCALE, NAME));
		new Thread(this, this.NAME).start();
	}

	public synchronized void stop() {
		running = false;
	}

	// This is where the window will be rendered.
	public void run() {

	}

	// This is where you can start the application from
	public static void main(String[] args) {
		/*
		 * Uses the implement of running to start a thread in which the
		 * application is running off of.
		 */
		new ClockMain().start();
	}

	// Here onwards is where the getters and setters are.
	
	public static ClockFrame getFrame() {
		return frame;
	}

	public static void setFrame(ClockFrame frame) {
		ClockMain.frame = frame;
	}

}
