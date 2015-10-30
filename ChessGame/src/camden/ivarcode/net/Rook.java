package camden.ivarcode.net;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Rook extends Piece {
	
	public int pointValue = 5;
	
	public Rook(Location loc, String color, BufferedImage img, Graphics g) {
		super(loc, color, img, g);
	}

}
