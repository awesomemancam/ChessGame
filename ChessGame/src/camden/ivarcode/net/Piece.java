package camden.ivarcode.net;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Piece {

	private Location loc;
	private String color;
	private BufferedImage img;
	
	public Piece(Location loc, String color, BufferedImage img, Graphics g) {
		setLoc(loc);
		setColor(color);
		setImg(img);
		updateGraphics(g);
	}

	private void updateGraphics(Graphics g) {
		//TODO
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
}