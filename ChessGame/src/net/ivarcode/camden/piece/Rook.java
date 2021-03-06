package net.ivarcode.camden.piece;

import java.awt.image.BufferedImage;

import net.ivarcode.camden.Location;

public class Rook extends Piece {
	
	public Rook(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Rook");
		setPointValue(5);
		setHasMoved(false);
	}

}
