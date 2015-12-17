package net.ivarcode.camden.piece;

import java.awt.image.BufferedImage;

import net.ivarcode.camden.Location;

public class Bishop extends Piece {
	
	public Bishop(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Bishop");
		setPointValue(3);
	}

}
