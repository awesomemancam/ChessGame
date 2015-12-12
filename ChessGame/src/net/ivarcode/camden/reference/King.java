package net.ivarcode.camden.reference;

import java.awt.image.BufferedImage;

public class King extends Piece {
	
	public King(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("King");
		setHasMoved(false);
	}

}