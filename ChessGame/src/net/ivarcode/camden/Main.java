package net.ivarcode.camden;

import java.awt.*;

import javax.swing.JApplet;

public class Main extends JApplet {
	
	
	
	
	
	
	
	
	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width, d.height);
		
		/*if (inDrag) {
			updateSquares(g);
		} else {
			//System.out.println("PAINTING NOW");
			Dimension d = getSize();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, d.width, d.height);
			game.drawBoard(g);
			drawPieces(g);
		}*/
	}
	private void drawPieces(Graphics g) {
		/*Location loc;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				loc = new Location(i,j);
				if (game.getPiece(loc) != null) {
					game.drawPiece(g, new Location(i,j));
				}
			}
		}*/
	}

	public void updateSquares(Graphics g) {
		/*game.drawSquare(g, old_dest);
		game.drawSquareTinted(g, dest);
		game.drawPiece(g, old_dest);
		game.drawSquare(g, src);
		game.drawPieceAt(g, src, dest);*/
	}
	
	
}
