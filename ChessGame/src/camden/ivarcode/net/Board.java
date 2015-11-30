package camden.ivarcode.net;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import camden.ivarcode.net.piece.*;

public class Board {

	private Piece[][] board;
	private BufferedImage wKingi, bKingi, wQueeni, bQueeni, wBishopi, bBishopi,
	wKnighti, bKnighti, wRooki, bRooki, wPawni, bPawni;
	private Location lastSrc, lastDest;
	private Piece lastCapPiece;

	public Board() {
		board = new Piece[8][8];
		loadPieceImages();
		loadPieces();
	}

	protected void print() {
		System.out.println("------------------------");
		for (int j = 7; j > -1; j--) {
			for (int i = 0; i < 8; i++) {
				if (board[i][j] instanceof King) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[K]");
					} else {
						System.out.print("[k]");
					}
				} else if (board[i][j] instanceof Queen) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[Q]");
					} else {
						System.out.print("[q]");
					}
				} else if (board[i][j] instanceof Bishop) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[B]");
					} else {
						System.out.print("[b]");
					}
				} else if (board[i][j] instanceof Knight) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[N]");
					} else {
						System.out.print("[n]");
					}
				} else if (board[i][j] instanceof Rook) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[R]");
					} else {
						System.out.print("[r]");
					}
				} else if (board[i][j] instanceof Pawn) {
					if (board[i][j].getColor().equals("white")) {
						System.out.print("[P]");
					} else {
						System.out.print("[p]");
					}
				} else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}

	public Piece getPiece(Location src) {
		if (src.getFileByInt() < 8 && src.getFileByInt() > -1
				&& src.getRank() < 8 && src.getRank() > -1) {
			return board[src.getFileByInt()][src.getRank()];
		}
		return null;
	}

	public void movePiece(Location src, Location dest) {
		//TODO move piece to array displaying captured pieces
		placePiece(getPiece(src),dest);
		placePiece(null,src);
		getPiece(new Location(dest.getFile(),dest.getRank())).setHasMoved(true);
	}
	public void takeBack() {
		placePiece(getPiece(lastDest),lastSrc);
		placePiece(lastCapPiece,lastDest);
	}

	public void placePiece(Piece piece, Location loc) {
		board[loc.getFileByInt()][loc.getRank()] = piece;
	}

	private void loadPieceImages() {
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
	private void loadPieces() {
		board[4][0] = new King(new Location('e',0), "white", wKingi);
		board[3][0] = new Queen(new Location('d',0), "white", wQueeni);
		board[2][0] = new Bishop(new Location('c',0), "white", wBishopi);
		board[5][0] = new Bishop(new Location('f',0), "white", wBishopi);
		board[1][0] = new Knight(new Location('b',0), "white", wKnighti);
		board[6][0] = new Knight(new Location('g',0), "white", wKnighti);
		board[0][0] = new Rook(new Location('a',0), "white", wRooki);
		board[7][0] = new Rook(new Location('h',0), "white", wRooki);
		board[0][1] = new Pawn(new Location('a',1), "white", wPawni);
		board[1][1] = new Pawn(new Location('b',1), "white", wPawni);
		board[2][1] = new Pawn(new Location('c',1), "white", wPawni);
		board[3][1] = new Pawn(new Location('d',1), "white", wPawni);
		board[4][1] = new Pawn(new Location('e',1), "white", wPawni);
		board[5][1] = new Pawn(new Location('f',1), "white", wPawni);
		board[6][1] = new Pawn(new Location('g',1), "white", wPawni);
		board[7][1] = new Pawn(new Location('h',1), "white", wPawni);

		board[4][7] = new King(new Location('e',7), "black", bKingi);
		board[3][7] = new Queen(new Location('d',7), "black", bQueeni);
		board[2][7] = new Bishop(new Location('c',7), "black", bBishopi);
		board[5][7] = new Bishop(new Location('f',7), "black", bBishopi);
		board[1][7] = new Knight(new Location('b',7), "black", bKnighti);
		board[6][7] = new Knight(new Location('g',7), "black", bKnighti);
		board[0][7] = new Rook(new Location('a',7), "black", bRooki);
		board[7][7] = new Rook(new Location('h',7), "black", bRooki);
		board[0][6] = new Pawn(new Location('a',6), "black", bPawni);
		board[1][6] = new Pawn(new Location('b',6), "black", bPawni);
		board[2][6] = new Pawn(new Location('c',6), "black", bPawni);
		board[3][6] = new Pawn(new Location('d',6), "black", bPawni);
		board[4][6] = new Pawn(new Location('e',6), "black", bPawni);
		board[5][6] = new Pawn(new Location('f',6), "black", bPawni);
		board[6][6] = new Pawn(new Location('g',6), "black", bPawni);
		board[7][6] = new Pawn(new Location('h',6), "black", bPawni);
	}

}

