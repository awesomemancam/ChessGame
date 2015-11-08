/*
 * Camden I. Wagner
 * Chess Game
 * currently not functioning, chess game designed to be embedded on a webpage
 * (ivarcode.net)
 * project started on October 25 2015
 */

package camden.ivarcode.net;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;

import camden.ivarcode.net.piece.Bishop;
import camden.ivarcode.net.piece.King;
import camden.ivarcode.net.piece.Knight;
import camden.ivarcode.net.piece.Pawn;
import camden.ivarcode.net.piece.Queen;
import camden.ivarcode.net.piece.Rook;

public class Main extends JApplet {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * inactive Global variables
	 * private static String currentDirectory = new File("").getAbsolutePath();
	 */

	private static int sqDim = 60;
	private static int sideWidth = 150;
	private static int topHeight = 50;
	private Graphics g;
	private Game game;
	boolean inDrag = false;
	int mouseX, mouseY;

	public void init() {

		game = new Game(g);

		addMouseListener(new MouseAdapter() {
			int a = -1000, b = -1000;
			int c = -1000, d = -1000;

			public void mousePressed (MouseEvent e) {
				// Obtain mouse coordinates at time of press.
				int x = e.getX ();
				int y = e.getY ();

				if (x < sideWidth) {
					//do nothing mouse off screen
				} else if (x < sideWidth+(sqDim*8)) {
					a = x-sideWidth;
					a = a/sqDim;
				} else {
					//do nothing as of now
				}
				if (y < topHeight) {
					//do nothing mouse off screen
				} else if (y < topHeight+(sqDim*8)) {
					b = y-topHeight;
					b = b/sqDim;
					//flips board so that 0,0 is in bottom left corner
					b = 7-b;
				} else {
					//do nothing as of now
				}
				if (a != -1000 && b != -1000) {
					if (board.getPiece(a, b) != null) {
						clickedPiece = board.getPiece(a, b);
					}
				}
				inDrag = true;
			}
			public void mouseReleased(MouseEvent e)
			{
				int x = e.getX ();
				int y = e.getY ();

				if (x < sideWidth) {
					//do nothing mouse off screen
				} else if (x < sideWidth+(sqDim*8)) {
					c = x-sideWidth;
					c = c/sqDim;
				} else {
					//do nothing as of now
				}
				if (y < topHeight) {
					//do nothing mouse off screen
				} else if (y < topHeight+(sqDim*8)) {
					d = y-topHeight;
					d = d/sqDim;
					//flips board so that 0,0 is in bottom left corner
					d = 7-d;
				} else {
					//do nothing as of now
				}

				if (a == c && b == d) {
					//do nothing;
				} else {
					board.movePiece(a,b,c,d);
/*
					if (board.inCheckmate().equals("NOT")) {
						//none
					} else if (board.inCheckmate().equals("white")) {
						System.out.println("White wins by checkmate!");
					} else if (board.inCheckmate().equals("black")) {
						System.out.println("Black wins by checkmate!");
					} else if (board.inCheckmate().equals("draw")) {
						System.out.println("Draw by stalemate.");
					}*/
					
				}

				a = -1000;
				b = -1000; 
				c = -1000;
				d = -1000;
				clickedPiece = nullPiece;
				
				board.print();
				System.out.println("got to repaint()");
				repaint();

				// When mouse is released, clear inDrag (to
				// indicate no drag in progress) if inDrag is
				// already set.
				if (inDrag)
					inDrag = false;

			}
		});
		// Attach a mouse motion listener to the applet. That listener listens
		// for mouse drag events.
		addMouseMotionListener (new MouseMotionAdapter ()
		{
			public void mouseDragged (MouseEvent e)
			{
				if (inDrag)
				{
					mouseX = e.getX();
					mouseY = e.getY();
					repaint();
				}
			}
		});
	}

	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width, d.height);
		System.out.println("PAINTING NOW");
		drawBoard(g);
		drawPieces(g);
	}

	public void update(Graphics g) {
		paint(g);
	}

}
