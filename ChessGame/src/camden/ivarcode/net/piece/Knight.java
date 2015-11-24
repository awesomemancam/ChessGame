package camden.ivarcode.net.piece;

import java.awt.image.BufferedImage;

import camden.ivarcode.net.Location;

public class Knight extends Piece {
	
	public Knight(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Knight");
		setPointValue(3);
	}

}
