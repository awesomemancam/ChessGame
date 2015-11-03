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
	private Board board;
	private Piece 
	bRook1, bRook2, wRook1, wRook2, 
	bKnight1, bKnight2, wKnight1, wKnight2, 
	bBishop1, bBishop2, wBishop1, wBishop2,
	bQueen, wQueen,
	bKing, wKing,
	bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8, 
	wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8;
	private static BufferedImage  
	bRooki, wRooki, 
	bKnighti, wKnighti, 
	bBishopi, wBishopi,
	bQueeni, wQueeni,
	bKingi, wKingi,
	bPawni, wPawni;
	boolean inDrag = false;
	int mouseX, mouseY;
	private Piece clickedPiece;

	public void init() {

		loadImages();
		loadPieces();
		
		Piece nullPiece = new Piece();
		clickedPiece = nullPiece;

		board = new Board(wRook1, wKnight1, wBishop1, wQueen, wKing, wBishop2, wKnight2, wRook2, 
				wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8,
				bRook1, bKnight1, bBishop1, bQueen, bKing, bBishop2, bKnight2, bRook2, 
				bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8);

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
				
				board.movePiece(a,b,c,d);
				if (board.inCheckmate().equals("white")) {
					System.out.println("White wins by checkmate!");
				} else if (board.inCheckmate().equals("black")) {
					System.out.println("Black wins by checkmate!");
				} else if (board.inCheckmate().equals("draw")) {
					System.out.println("Draw by stalemate.");
				}
				repaint();
				
				a = -1000;
				b = -1000;
				c = -1000;
				d = -1000;
				clickedPiece = nullPiece;
				
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
		drawBoard(g);
		drawPieces(g);
	}

	public void update(Graphics g) {
		paint(g);
	}

	private void loadPieces() {
		wKing = new King(new Location('e',0), "white", wKingi);
		wQueen = new Queen(new Location('d',0), "white", wQueeni);
		wBishop1 = new Bishop(new Location('c',0), "white", wBishopi);
		wBishop2 = new Bishop(new Location('f',0), "white", wBishopi);
		wKnight1 = new Knight(new Location('b',0), "white", wKnighti);
		wKnight2 = new Knight(new Location('g',0), "white", wKnighti);
		wRook1 = new Rook(new Location('a',0), "white", wRooki);
		wRook2 = new Rook(new Location('h',0), "white", wRooki);
		wPawn1 = new Pawn(new Location('a',1), "white", wPawni);
		wPawn2 = new Pawn(new Location('b',1), "white", wPawni);
		wPawn3 = new Pawn(new Location('c',1), "white", wPawni);
		wPawn4 = new Pawn(new Location('d',1), "white", wPawni);
		wPawn5 = new Pawn(new Location('e',1), "white", wPawni);
		wPawn6 = new Pawn(new Location('f',1), "white", wPawni);
		wPawn7 = new Pawn(new Location('g',1), "white", wPawni);
		wPawn8 = new Pawn(new Location('h',1), "white", wPawni);

		bKing = new King(new Location('e',7), "black", bKingi);
		bQueen = new Queen(new Location('d',7), "black", bQueeni);
		bBishop1 = new Bishop(new Location('c',7), "black", bBishopi);
		bBishop2 = new Bishop(new Location('f',7), "black", bBishopi);
		bKnight1 = new Knight(new Location('b',7), "black", bKnighti);
		bKnight2 = new Knight(new Location('g',7), "black", bKnighti);
		bRook1 = new Rook(new Location('a',7), "black", bRooki);
		bRook2 = new Rook(new Location('h',7), "black", bRooki);
		bPawn1 = new Pawn(new Location('a',6), "black", bPawni);
		bPawn2 = new Pawn(new Location('b',6), "black", bPawni);
		bPawn3 = new Pawn(new Location('c',6), "black", bPawni);
		bPawn4 = new Pawn(new Location('d',6), "black", bPawni);
		bPawn5 = new Pawn(new Location('e',6), "black", bPawni);
		bPawn6 = new Pawn(new Location('f',6), "black", bPawni);
		bPawn7 = new Pawn(new Location('g',6), "black", bPawni);
		bPawn8 = new Pawn(new Location('h',6), "black", bPawni);
	}

	public static void loadImages() {
		try {
			bRooki = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_Rook.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wRooki = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_Rook.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			bKnighti = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_Knight.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wKnighti = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_Knight.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			bBishopi = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_Bishop.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wBishopi = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_Bishop.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			bQueeni = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_Queen.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wQueeni = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_Queen.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			bKingi = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_King.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wKingi = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_King.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			bPawni = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/b_Pawn.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }
		try {
			wPawni = ImageIO.read(new URL(
					"http://www.ivarcode.net/apps/chessgame/assets/w_Pawn.png"));
		} catch (IOException e) {
			System.out.println("ERROR: img not found"); }

	}

	public void drawBoard(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.drawRect(sideWidth, topHeight, sqDim*8, sqDim*8);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.fillRect(sideWidth+sqDim+(sqDim*2*j), 
						topHeight+(sqDim*2*i), 
						sqDim, sqDim);
				g.fillRect(sideWidth+(sqDim*2*j), 
						topHeight+sqDim+(sqDim*2*i), 
						sqDim, sqDim);
			}
		}
	}

	public void drawPieces(Graphics g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getPiece(i,j) != null && clickedPiece != board.getPiece(i, j)) {
					g.drawImage(board.getPiece(i,j).getImg(), 
							sideWidth+(board.getPiece(i,j).getLoc().getFileByInt()*sqDim), 
							topHeight+((7-board.getPiece(i,j).getLoc().getRank())*sqDim), null);
				}
				if (clickedPiece == board.getPiece(i, j)) {
					g.drawImage(board.getPiece(i, j).getImg(), mouseX-(sqDim/2), mouseY-(sqDim/2), null);
				}
			}
		}
	}

}
