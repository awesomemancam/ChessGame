/*
 * Camden I. Wagner
 * Chess Game
 * project started on October 25 2015
 */

package camden.ivarcode.net;

import java.awt.*;

import javax.swing.JApplet;

public class Main extends JApplet {

	public void paint(Graphics g) {
		
		int sqDim = 75;
		int sideWidth = 150;
		int topHeight = 50;
		
		g.setColor(Color.BLACK);
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
}
