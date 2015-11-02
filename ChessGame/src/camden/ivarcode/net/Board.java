package camden.ivarcode.net;

import java.awt.Color;
import java.awt.Graphics;

public class Board {

	private Piece[][] board = new Piece[8][8];
	private boolean[][] whiteThreats = new boolean[8][8];
	private boolean[][] blackThreats = new boolean[8][8];
	private Piece whiteKing, blackKing;
	private String turn;

	public Board() {
		//initialize nothing
	}

	public Board(Piece wRook1, Piece wKnight1, Piece wBishop1, Piece wQueen,
			Piece wKing, Piece wBishop2, Piece wKnight2, Piece wRook2, 
			Piece wPawn1, Piece wPawn2, Piece wPawn3, Piece wPawn4, 
			Piece wPawn5, Piece wPawn6, Piece wPawn7, Piece wPawn8, 
			Piece bRook1, Piece bKnight1, Piece bBishop1, Piece bQueen,
			Piece bKing, Piece bBishop2, Piece bKnight2, Piece bRook2, 
			Piece bPawn1, Piece bPawn2, Piece bPawn3, Piece bPawn4, 
			Piece bPawn5, Piece bPawn6, Piece bPawn7, Piece bPawn8) {

		whiteKing = wKing;
		blackKing = bKing;
		turn = "white";
		
		place(whiteKing, whiteKing.getLoc());
		place(blackKing, blackKing.getLoc());
		place(wQueen, wQueen.getLoc());
		place(bQueen, bQueen.getLoc());
		place(wBishop1, wBishop1.getLoc());
		place(wBishop2, wBishop2.getLoc());
		place(bBishop1, bBishop1.getLoc());
		place(bBishop2, bBishop2.getLoc());
		place(wKnight1, wKnight1.getLoc());
		place(wKnight2, wKnight2.getLoc());
		place(bKnight1, bKnight1.getLoc());
		place(bKnight2, bKnight2.getLoc());
		place(wRook1, wRook1.getLoc());
		place(wRook2, wRook2.getLoc());
		place(bRook1, bRook1.getLoc());
		place(bRook2, bRook2.getLoc());

		place(wPawn1, wPawn1.getLoc());
		place(wPawn2, wPawn2.getLoc());
		place(wPawn3, wPawn3.getLoc());
		place(wPawn4, wPawn4.getLoc());
		place(wPawn5, wPawn5.getLoc());
		place(wPawn6, wPawn6.getLoc());
		place(wPawn7, wPawn7.getLoc());
		place(wPawn8, wPawn8.getLoc());
		place(bPawn1, bPawn1.getLoc());
		place(bPawn2, bPawn2.getLoc());
		place(bPawn3, bPawn3.getLoc());
		place(bPawn4, bPawn4.getLoc());
		place(bPawn5, bPawn5.getLoc());
		place(bPawn6, bPawn6.getLoc());
		place(bPawn7, bPawn7.getLoc());
		place(bPawn8, bPawn8.getLoc());

		print();
		refreshThreats();
	}

	private void place(Piece piece, Location loc) {
		board[loc.getFileByInt()][loc.getRank()] = piece;
	}

	private void refreshThreats() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				whiteThreats[i][j] = whiteThreatens(i,j);
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				blackThreats[i][j] = blackThreatens(i,j);
			}
		}
	}

	public Piece getPiece(char file, int rank) {
		int i = -1; //throws error if case not 'a'-'h'
		if (file == 'a') {
			i = 0;
		} else if (file == 'b') {
			i = 1;
		} else if (file == 'c') {
			i = 2;
		} else if (file == 'd') {
			i = 3;
		} else if (file == 'e') {
			i = 4;
		} else if (file == 'f') {
			i = 5;
		} else if (file == 'g') {
			i = 6;
		} else if (file == 'h') {
			i = 7;
		} 
		return board[i][rank];
	}

	public Piece getPiece(int fileByInt, int rank) {
		return board[fileByInt][rank];
	}

	public void movePiece(Location src, Location dest) {
		//TODO
	}

	private boolean isTurn(String color) {
		if (turn == color) {
			return true;
		} else {
			return false;
		}
	}

	private boolean kingInCheck() {
		refreshThreats();
		if (isTurn("white")) {
			return blackThreats[whiteKing.getLoc().getFileByInt()][whiteKing.getLoc().getRank()];
		} else {
			return whiteThreats[blackKing.getLoc().getFileByInt()][blackKing.getLoc().getRank()];
		}
	}

	private boolean blackThreatens(int r, int f) {
		boolean isThreat = false;
		int tR = r, tF = f;
		//up
		while (tR-1 > -1) {
			tR -= 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down
		tR = r; tF = f;
		while (tR+1 < 8) {
			tR += 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//right
		tR = r; tF = f;
		while (tF+1 < 8) {
			tF += 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//left
		tR = r; tF = f;
		while (tF-1 > -1) {
			tF -= 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//up-right
		tR = r; tF = f;
		while (tR-1 > -1 && tF+1 < 8) {
			tR -= 1;
			tF += 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//up-left
		tR = r; tF = f;
		while (tR-1 > -1 && tF-1 > -1) {
			tR -= 1;
			tF -= 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down-right
		tR = r; tF = f;
		while (tR+1 < 8 && tF+1 < 8) {
			tR += 1;
			tF += 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down-left
		tR = r; tF = f;
		while (tR+1 < 8 && tF-1 > -1) {
			tR += 1;
			tF -= 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("black")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//knight
		if (r+2 < 8 && f+1 < 8)	{
			if (board[r+2][f+1] instanceof Knight 
					&& board[r+2][f+1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r+2 < 8 && f-1 > -1) {
			if (board[r+2][f-1] instanceof Knight 
					&& board[r+2][f-1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f+2 < 8)	{
			if (board[r+1][f+2] instanceof Knight 
					&& board[r+1][f+2].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f-2 > -1) {
			if (board[r+1][f-2] instanceof Knight 
					&& board[r+1][f-2].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f+2 < 8) {
			if (board[r-1][f+2] instanceof Knight 
					&& board[r-1][f+2].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f-2 > -1) {
			if (board[r-1][f-2] instanceof Knight 
					&& board[r-1][f-2].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f+1 < 8) {
			if (board[r-2][f+1] instanceof Knight 
					&& board[r-2][f+1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f-1 > -1) {
			if (board[r-2][f-1] instanceof Knight 
					&& board[r-2][f-1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		//around (king-check) && include pawn check here
		if (r-1 > -1) {
			if (board[r-1][f] == blackKing) {
				isThreat = true;
			}
		}
		if (r+1 < 8) {
			if (board[r+1][f] == blackKing) {
				isThreat = true;
			}
		}
		if (f+1 < 8) {
			if (board[r][f+1] == blackKing) {
				isThreat = true;
			}
		}
		if (f-1 > -1) {
			if (board[r][f-1] == blackKing) {
				isThreat = true;
			}
		}
		//up-right
		if (r+1 < 8 && f+1 < 8) {
			if ((board[r+1][f+1] == blackKing
					|| board[r+1][f+1] instanceof Pawn)
					&& board[r+1][f+1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		//up-left
		if (r+1 < 8 && f-1 > -1) {
			if ((board[r+1][f-1] == blackKing
					|| board[r+1][f-1] instanceof Pawn)
					&& board[r+1][f-1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		//down-right
		if (r-1 > -1 && f+1 < 8) {
			if (board[r-1][f+1] == blackKing) {
				isThreat = true;
			}
		}
		//down-left
		if (r-1 > -1 && f-1 > -1) {
			if (board[r-1][f-1] == blackKing) {
				isThreat = true;
			}
		}
		return isThreat;
	}

	private boolean whiteThreatens(int r, int f) {
		boolean isThreat = false;
		int tR = r, tF = f;
		//up
		while (tR-1 > -1) {
			tR -= 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down
		tR = r; tF = f;
		while (tR+1 < 8) {
			tR += 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//right
		tR = r; tF = f;
		while (tF+1 < 8) {
			tF += 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//left
		tR = r; tF = f;
		while (tF-1 > -1) {
			tF -= 1;
			if ((board[tR][tF] instanceof Rook 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//up-right
		tR = r; tF = f;
		while (tR-1 > -1 && tF+1 < 8) {
			tR -= 1;
			tF += 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//up-left
		tR = r; tF = f;
		while (tR-1 > -1 && tF-1 > -1) {
			tR -= 1;
			tF -= 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down-right
		tR = r; tF = f;
		while (tR+1 < 8 && tF+1 < 8) {
			tR += 1;
			tF += 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down-left
		tR = r; tF = f;
		while (tR+1 < 8 && tF-1 > -1) {
			tR += 1;
			tF -= 1;
			if ((board[tR][tF] instanceof Bishop 
					|| board[tR][tF] instanceof Queen) 
					&& board[tR][tF].getColor().equals("white")) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//knight
		if (r+2 < 8 && f+1 < 8)	{
			if (board[r+2][f+1] instanceof Knight 
					&& board[r+2][f+1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r+2 < 8 && f-1 > -1) {
			if (board[r+2][f-1] instanceof Knight 
					&& board[r+2][f-1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f+2 < 8)	{
			if (board[r+1][f+2] instanceof Knight 
					&& board[r+1][f+2].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f-2 > -1) {
			if (board[r+1][f-2] instanceof Knight 
					&& board[r+1][f-2].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f+2 < 8) {
			if (board[r-1][f+2] instanceof Knight 
					&& board[r-1][f+2].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f-2 > -1) {
			if (board[r-1][f-2] instanceof Knight 
					&& board[r-1][f-2].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f+1 < 8) {
			if (board[r-2][f+1] instanceof Knight 
					&& board[r-2][f+1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f-1 > -1) {
			if (board[r-2][f-1] instanceof Knight 
					&& board[r-2][f-1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		//around (king-check) && include pawn check here
		if (r-1 > -1) {
			if (board[r-1][f] == whiteKing) {
				isThreat = true;
			}
		}
		if (r+1 < 8) {
			if (board[r+1][f] == whiteKing) {
				isThreat = true;
			}
		}
		if (f+1 < 8) {
			if (board[r][f+1] == whiteKing) {
				isThreat = true;
			}
		}
		if (f-1 > -1) {
			if (board[r][f-1] == whiteKing) {
				isThreat = true;
			}
		}
		//up-right
		if (r+1 < 8 && f+1 < 8) {
			if (board[r+1][f+1] == whiteKing) {
				isThreat = true;
			}
		}
		//up-left
		if (r+1 < 8 && f-1 > -1) {
			if (board[r+1][f-1] == whiteKing) {
				isThreat = true;
			}
		}
		//down-right
		if (r-1 > -1 && f+1 < 8) {
			if ((board[r-1][f+1] == whiteKing
					|| board[r-1][f+1] instanceof Pawn)
					&& board[r-1][f+1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		//down-left
		if (r-1 > -1 && f-1 > -1) {
			if ((board[r-1][f-1] == whiteKing
					|| board[r-1][f-1] instanceof Pawn)
					&& board[r-1][f-1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		return isThreat;
	}

	public void print() {
		// I know, bad practice, I was just too lazy to Find/Replace and switch all the board calls
		for (int j = 0; j < 8; j++) {
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
	}
}
