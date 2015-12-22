package net.ivarcode.camden;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JApplet;

import net.ivarcode.camden.piece.Piece;


/*
 * ChessGame
 * Camden I Wagner
 * Main.java 
 */


public class Main extends JApplet {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;

	private static final String HELP_LOG = "#HELP#\n"
			+ "play (p)     Play a game.\n"
			+ "   -t [set time control]\n"
			+ "record (r)   Record a game for your database.\n"
			+ "analyze (a)  Analyze a game from your database.\n";
	private static int sqDim = 60;
	private static int sideWidth = 150;
	private static int topHeight = 50;
	private Piece clickedPiece;
	private Game game;
	private Location src, dest;
	private Location old_dest;
	boolean promoting = false, inDrag = false;
	int mouseX, mouseY;

	boolean inGame = false, vs_bot = false, vs_human = false; //TODO use these vars
	String human_color = null, bot_color;
	Scanner scanner = new Scanner(new InputStreamReader(System.in));
	boolean killed = false;
	String USER = null;

	public void init() {

		String input = null;
		while (!killed) {
			/*while (true) {
				System.out.print("LOGIN> ");
				input = scanner.next();
				if (input.equals("l")) {
					System.out.print(":: ");
					input = scanner.next();
					if (input.equals("admin")) {
						USER = input;
						break;
					}
				}


			}*/

			System.out.println("\nWelcome to Cam's Chess Engine.");
			System.out.println("Type 'HELP' or 'h' for help.");

			/*
			 * By default, USER == input because both are initialized to null
			 * This will change when I implement a userbase and login function
			 */
			while (USER == input && !killed) {
				bash();
			}
		}

	}

	public void GameTypeOne() {
		game = new Game(sideWidth, topHeight, sqDim);

		addMouseListener(new MouseAdapter() {
			int a = -1000, b = -1000;
			int c = -1000, d = -1000;
			Piece nullPiece = new Piece();

			public void mousePressed(MouseEvent e) {
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

				if (a < 8 && a > -1 && b < 8 && b > -1) {
					src = new Location(a,b);
					dest = src;
					old_dest = src;
					Piece nullPiece = new Piece();
					if (game.getPiece(src) != null) {
						clickedPiece = game.getPiece(src);
					} else {
						clickedPiece = nullPiece;
					}
				}
				inDrag = true;
			}
			public void mouseReleased(MouseEvent e)
			{

				if (!promoting) {
					//promoting = game.makeMove(new Move(src,dest));					
				} else {
					//game.promote
				}

				a = -1000;
				b = -1000; 
				c = -1000;
				d = -1000;
				clickedPiece = nullPiece;

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
					int a = -1000, b = -1000;
					int x = e.getX();
					int y = e.getY();

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

					if (a < 8 && a > -1 && b < 8 && b > -1) {
						if (a != dest.getFileByInt() || 
								b != dest.getRank()) {
							old_dest = dest;
							dest = new Location(a,b);
							//System.out.println(a + " " + b);
							repaint();
						}
					}

				}
			}
		});		
	}


	private void bash() {
		System.out.print("\nCHESS> ");
		String in = scanner.next();
		switch (in) {
		case "HELP":
		case "h": {
			System.out.print(HELP_LOG);
			System.out.print("\n");
		}
		case "logout":
		case "l": {
			USER = null;
		}
		case "exit": {
			System.out.print("Program killed.");
			//System.out.print("\nReload webpage to restart the program.");
			killed = true;
		}
		case "play":
		case "p": {
			//play a game
		}
		default: {
			System.out.print("\n!~  ERROR:");
			System.out.print("\n    Your command '"+in+"' was not recognized.");
			System.out.print("\n    Type 'HELP' or 'h' for help.");
			System.out.print("\n!~\n");
		}
		}

	}


	/*
	public void paint(Graphics g) {


		if (inDrag) {
			updateSquares(g);
		} else {
			//System.out.println("PAINTING NOW");
			Dimension d = getSize();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, d.width, d.height);
			game.drawBoard(g);
			drawPieces(g);
		}
	}
	private void drawPieces(Graphics g) {
		Location loc;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				loc = new Location(i,j);
				if (game.getPiece(loc) != null) {
					game.drawPiece(g, new Location(i,j));
				}
			}
		}
	}

	public void updateSquares(Graphics g) {
		game.drawSquare(g, old_dest);
		game.drawSquareTinted(g, dest);
		game.drawPiece(g, old_dest);
		game.drawSquare(g, src);
		game.drawPieceAt(g, src, dest);
	}
	 */
}
