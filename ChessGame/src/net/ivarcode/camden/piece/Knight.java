package net.ivarcode.camden.piece;

import java.awt.image.BufferedImage;

import net.ivarcode.camden.Location;

public class Knight extends Piece {
	
	public Knight(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Knight");
		setPointValue(3);
	}

}