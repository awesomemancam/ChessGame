package net.ivarcode.camden;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JApplet;


/*
 * ChessGame
 * Camden I Wagner
 * Main.java 
 */


public class Main extends JApplet {
	
	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private boolean inDrag = false;
	private int clickX,clickY,currX,currY,releaseX,releaseY;

	public void init() {

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				clickX = e.getX ();
				clickY = e.getY ();
				
				inDrag = true;
			}
			public void mouseReleased(MouseEvent e) {
				releaseX = e.getX ();
				releaseY = e.getY ();
				
				if (inDrag)
					inDrag = false;
			}
		});
		// Attach a mouse motion listener to the applet. That listener listens
		// for mouse drag events.
		addMouseMotionListener (new MouseMotionAdapter ()
		{
			public void mouseDragged (MouseEvent e)
			{
				
			}
		});
	}

	public void paint(Graphics g) {
		
	}

}
