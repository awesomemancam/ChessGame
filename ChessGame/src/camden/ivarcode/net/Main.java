/*
 * Camden I. Wagner
 * Chess Game
 * project started on October 25 2015
 */

package camden.ivarcode.net;

import java.awt.*;

import javax.swing.JApplet;

public class Main extends JApplet {
	
	private static int sqDim = 75;
	private static int sideWidth = 150;
	private static int topHeight = 50;

	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		//drawing board
		drawBoard(g);
		//setting up board
		setupBoard(g);
		
		
	}
	
	public static void drawBoard(Graphics g) {
		g.drawRect(sideWidth, topHeight, sqDim*8, sqDim*8);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.fillRect(sideWidth+sqDim+(sqDim*2*j), 
						topHeight+(sqDim*2*i), 
						sqDim, sqDim);
				g.fillRect(sideWidth+(sqDim*2*j), 
						topHeight+sqDim+(sqDim*2*i), 
						sqDim, sqDim);
			}
		}
	}
	
	public static void setupBoard(Graphics g) {
		
	}
	
}
