/*
 * Camden I. Wagner
 * Chess Game
 * currently not functioning, chess game designed to be embedded on a webpage
 * (ivarcode.net)
 * project started on October 25 2015
 */

package camden.ivarcode.net;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
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

	public void paint(Graphics g) {
		loadImages();
		loadPieces();
		Board board = new Board(wRook1, wKnight1, wBishop1, wQueen, wKing, wBishop2, wKnight2, wRook2, 
				wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8,
				bRook1, bKnight1, bBishop1, bQueen, bKing, bBishop2, bKnight2, bRook2, 
				bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8, g);
		//TODO
		//continue and loop for multiple games option

	}

	private void loadPieces() {
		wKing = new King(new Location(0,'e'), "white", wKingi);
		wQueen = new Queen(new Location(0,'d'), "white", wQueeni);
		wBishop1 = new Bishop(new Location(0,'c'), "white", wBishopi);
		wBishop2 = new Bishop(new Location(0,'f'), "white", wBishopi);
		wKnight1 = new Knight(new Location(0,'b'), "white", wKnighti);
		wKnight2 = new Knight(new Location(0,'g'), "white", wKnighti);
		wRook1 = new Rook(new Location(0,'a'), "white", wRooki);
		wRook2 = new Rook(new Location(0,'h'), "white", wRooki);
		wPawn1 = new Pawn(new Location(1,'a'), "white", wPawni);
		wPawn2 = new Pawn(new Location(1,'b'), "white", wPawni);
		wPawn3 = new Pawn(new Location(1,'c'), "white", wPawni);
		wPawn4 = new Pawn(new Location(1,'d'), "white", wPawni);
		wPawn5 = new Pawn(new Location(1,'e'), "white", wPawni);
		wPawn6 = new Pawn(new Location(1,'f'), "white", wPawni);
		wPawn7 = new Pawn(new Location(1,'g'), "white", wPawni);
		wPawn8 = new Pawn(new Location(1,'h'), "white", wPawni);
		
		bKing = new King(new Location(7,'e'), "black", bKingi);
		bQueen = new Queen(new Location(7,'d'), "black", bQueeni);
		bBishop1 = new Bishop(new Location(7,'c'), "black", bBishopi);
		bBishop2 = new Bishop(new Location(7,'f'), "black", bBishopi);
		bKnight1 = new Knight(new Location(7,'b'), "black", bKnighti);
		bKnight2 = new Knight(new Location(7,'g'), "black", bKnighti);
		bRook1 = new Rook(new Location(7,'a'), "black", bRooki);
		bRook2 = new Rook(new Location(7,'h'), "black", bRooki);
		bPawn1 = new Pawn(new Location(6,'a'), "black", bPawni);
		bPawn2 = new Pawn(new Location(6,'b'), "black", bPawni);
		bPawn3 = new Pawn(new Location(6,'c'), "black", bPawni);
		bPawn4 = new Pawn(new Location(6,'d'), "black", bPawni);
		bPawn5 = new Pawn(new Location(6,'e'), "black", bPawni);
		bPawn6 = new Pawn(new Location(6,'f'), "black", bPawni);
		bPawn7 = new Pawn(new Location(6,'g'), "black", bPawni);
		bPawn8 = new Pawn(new Location(6,'h'), "black", bPawni);
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

}
