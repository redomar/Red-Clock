package com.redomar.clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
		// Getting the current time
		long nanoTime = System.nanoTime();
		long milliTime = System.currentTimeMillis();
		// Dividing every second by 30 (for 30FPS)
		double nsPerTick = 1000000000D / 30D;
		double delta = 0;

		int ticks = 0;
		int frames = 0;

		while (isRunning()) {

			// Delta goes above 1 when the time is 1/30 of a second.
			long updatedNanoTime = System.nanoTime();
			delta += (updatedNanoTime - nanoTime) / nsPerTick;
			nanoTime = updatedNanoTime;
			boolean shouldRender = false;

			/*
			 * Makes shouldRender true for 1/30 of a second. Sleeps for 2
			 * milliseconds and the checks If shouldRender is true; then the
			 * frames can be rendered
			 */
			while (delta >= 1) {
				ticks++;
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			// Prints the FPS and TPS out in the console every second.
			if (System.currentTimeMillis() - milliTime >= 1000) {
				milliTime = System.currentTimeMillis();
				System.out.println(frames + " Frames in " + ticks + " Ticks");
				frames = 0;
				ticks = 0;
			}
		}
	}

	private void render() {

		BufferStrategy bs = frame.getBufferStrategy();
		if(bs == null){
			frame.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getTotalWidth(), getTotalhight());
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Arial", java.awt.Font.BOLD, 20));
		g.drawString(NAME, 0, getTotalhight()/2);
		bs.show();
		g.dispose();
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

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getWIDTH() {
		return WIDTH;
	}
	
	public int getTotalWidth(){
		return (WIDTH*SCALE)+10;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
	
	public int getTotalhight(){
		return (HEIGHT*SCALE)+10;
	}

	public int getSCALE() {
		return SCALE;
	}

}
