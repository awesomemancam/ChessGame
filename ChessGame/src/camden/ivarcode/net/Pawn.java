package camden.ivarcode.net;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
	
	public Pawn(Location loc, String color, BufferedImage img) {
		super(loc, color, img);
		setID("Pawn");
		setPointValue(1);
	}

}
