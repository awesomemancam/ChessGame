package net.ivarcode.camden.piece;

import java.awt.image.BufferedImage;

import net.ivarcode.camden.Location;

public class King extends Piece {
	
	public King(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("King");
		setHasMoved(false);
	}

}
