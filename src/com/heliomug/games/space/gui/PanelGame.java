package com.heliomug.games.space.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.heliomug.game.utils.WeidertPanel;
import com.heliomug.games.space.SpaceGame;

public class PanelGame extends WeidertPanel implements Runnable {
	private static final long serialVersionUID = -4501673998714242701L;
	private static final int SLEEP_TIME = 1;
	
	public PanelGame() {
		super(640, 480, - SpaceGame.WIDTH / 2, SpaceGame.WIDTH / 2, - SpaceGame.HEIGHT / 2, SpaceGame.HEIGHT / 2);
		
		setFocusable(true);
		setDoubleBuffered(true);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				SpaceFrame.getFrame().handleKey(e.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				SpaceFrame.getFrame().handleKey(e.getKeyCode(), false);
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
		setBackground(Color.BLACK);
		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.out.println("Thread Interrupted");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		SpaceGame game = SpaceFrame.getClient().getThing();
		if (game != null) {
			Graphics2D g2 = (Graphics2D)g;
			super.setScreenBounds(game.getBounds());
			game.draw(g2);
		}
	}
}
