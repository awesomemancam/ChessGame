package camden.ivarcode.net.piece;

import java.awt.image.BufferedImage;

import camden.ivarcode.net.Location;

public class Bishop extends Piece {
	
	public Bishop(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Bishop");
		setPointValue(3);
	}

}
