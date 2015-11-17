package camden.ivarcode.net;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import camden.ivarcode.net.piece.Bishop;
import camden.ivarcode.net.piece.King;
import camden.ivarcode.net.piece.Knight;
import camden.ivarcode.net.piece.Pawn;
import camden.ivarcode.net.piece.Queen;
import camden.ivarcode.net.piece.Rook;

public class dump {

	/*
	
	
	
	
	private Piece[][] board = new Piece[8][8];
	private boolean[][] whiteThreats = new boolean[8][8];
	private boolean[][] blackThreats = new boolean[8][8];
	private Piece whiteKing, blackKing, whiteQueen, blackQueen,
					whiteBishop1, whiteBishop2, blackBishop1, blackBishop2, 
					whiteKnight1, whiteKnight2, blackKnight1, blackKnight2, 
					whiteRook1, whiteRook2, blackRook1, blackRook2, 
					whitePawn1, whitePawn2, whitePawn3, whitePawn4, 
					whitePawn5, whitePawn6, whitePawn7, whitePawn8, 
					blackPawn1, blackPawn2, blackPawn3, blackPawn4, 
					blackPawn5, blackPawn6, blackPawn7, blackPawn8;
	private Image whiteKing_img, blackKing_img, whiteQueen_img, blackQueen_img,
					whiteBishop1_img, whiteBishop2_img, blackBishop1_img, blackBishop2_img, 
					whiteKnight1_img, whiteKnight2_img, blackKnight1_img, blackKnight2_img, 
					whiteRook1_img, whiteRook2_img, blackRook1_img, blackRook2_img, 
					whitePawn1_img, whitePawn2_img, whitePawn3_img, whitePawn4_img,
					whitePawn5_img, whitePawn6_img, whitePawn7_img, whitePawn8_img, 
					blackPawn1_img, blackPawn2_img, blackPawn3_img, blackPawn4_img, 
					blackPawn5_img, blackPawn6_img, blackPawn7_img, blackPawn8_img;
	
	
	public Board(Graphics g) {
s
		loadImages();
		loadPieces();
		
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

	public void place(Piece piece, Location loc) {
		//TODO remove any piece from underlying loc
		board[loc.getFileByInt()][loc.getRank()] = piece;
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

	public void movePiece(int srcX, int srcY, int destX, int destY) {
		Move move = new Move(new Location(srcX,srcY), new Location(destX,destY));
		Piece piece = getPiece(srcX,srcY);
		ArrayList<Move> moves;
		if (piece instanceof King) {
			moves = kingMoves(new Location(srcX,srcY));
		} else if (piece instanceof Queen) {
			moves = queenMoves(new Location(srcX,srcY));
		} else if (piece instanceof Bishop) {
			moves = bishopMoves(new Location(srcX,srcY));
		} else if (piece instanceof Knight) {
			moves = knightMoves(new Location(srcX,srcY));
		} else if (piece instanceof Rook) {
			moves = rookMoves(new Location(srcX,srcY));
		} else if (piece instanceof Pawn) {
			moves = pawnMoves(new Location(srcX,srcY), turn);
		} else {
			moves = null;
		}
		
		Piece takenPiece;

		if (moves != null) {
			for (int i = 0; i < moves.size(); i++) {
				if (move.getDest().getFile() == moves.get(i).getDest().getFile()
						&& move.getDest().getRank() == moves.get(i).getDest().getRank()) {
					System.out.print("LEGAL MOVE   ");
					move.print();
					takenPiece = getPiece(destX,destY);
					place(piece,new Location(destX,destY));
					place(null,new Location(srcX,srcY));
					if (kingInCheck()) {
						place(piece,new Location(srcX,srcY));
						place(takenPiece,new Location(destX,destY));
						System.out.println("board = tempBoard;");
					} else {
						if (isTurn("white")) {
							turn = "black";
						} else {
							turn = "white";
						}
					}
					break;
				}
			}
		}
		print();

		if (isLegalMove(srcX, srcY, destX, destY)) {
			Piece tempDest = getPiece(destX,destY);
			Piece tempSrc = getPiece(srcX,srcY);
			tempSrc.setLoc(new Location(destX,destY));
			place(null, new Location(srcX,srcY));
			place(tempSrc, tempSrc.getLoc());
			refreshThreats();
			if (kingInCheck()) {
				tempSrc.setLoc(new Location(srcX,srcY));
				place(tempDest, new Location(destX,destY));
				place(tempSrc, tempSrc.getLoc());
			} else {
				if (isTurn("white")) {
					turn = "black";
				} else {
					turn = "white";
				}
				System.out.println("turn = " + turn);
				print();
			}
		}
	}

	private boolean isLegalMove(int srcX, int srcY, int destX, int destY) {
		try {
			if (getPiece(srcX,srcY).getColor() != turn) {
				return false;
			}
		} catch (Exception e) {
			//do nothing ::     e.printStackTrace();
		}

		Piece piece = getPiece(srcX,srcY);
		Piece victimPiece = getPiece(destX,destY);
		if (victimPiece != null && victimPiece.getColor().equals(turn)) {
			return false;
		}
		int sf = srcX;
		int sr = srcY;
		int df = destX;
		int dr = destY;
		int tempf = sf, tempr = sr;
		if (piece instanceof King) {
			if ((sf+1 == df && sr == dr)
					|| (sf == df && sr+1 == dr)
					|| (sf-1 == df && sr == dr)
					|| (sf == df && sr-1 == dr)
					|| (sf+1 == df && sr+1 == dr)
					|| (sf+1 == df && sr-1 == dr)
					|| (sf-1 == df && sr+1 == dr)
					|| (sf-1 == df && sr-1 == dr)) {
				return true;
			} 
		} else if (piece instanceof Queen) {
			while (tempf < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
			}
			tempf = sf; tempr = sr;
			while (tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempf < 8 && tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1 && tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf--;
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempf < 8 && tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
				tempr--;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1 && tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf--;
				tempr--;
			}
			tempf = sf; tempr = sr;
		} else if (piece instanceof Bishop) {
			while (tempf < 8 && tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1 && tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf--;
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempf < 8 && tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
				tempr--;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1 && tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf--;
				tempr--;
			}
			tempf = sf; tempr = sr;
		} else if (piece instanceof Knight) {
			if ((sf+1 == df && sr+2 == dr)
					|| (sf+1 == df && sr-2 == dr)
					|| (sf-1 == df && sr+2 == dr)
					|| (sf-1 == df && sr-2 == dr)
					|| (sf+2 == df && sr-1 == dr)
					|| (sf+2 == df && sr+1 == dr)
					|| (sf-2 == df && sr+1 == dr)
					|| (sf-2 == df && sr-1 == dr)) {
				return true;
			} 
		} else if (piece instanceof Rook) {
			while (tempf < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
			}
			tempf = sf; tempr = sr;
			while (tempf > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempf++;
			}
			tempf = sf; tempr = sr;
			while (tempr < 8) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempr++;
			}
			tempf = sf; tempr = sr;
			while (tempr > -1) {
				if (tempf == df && tempr == dr) {
					return true;
				}				
				tempr++;
			}
		} else if (piece instanceof Pawn) {
			if (turn == "white") {
				if (sf-1 == df && sr+1 == dr) {
					return true;
				} else if (sf+1 == df && sr+1 == dr) {
					return true;
				} else if (sr+1 == dr && sf == df) {
					return true;
				} else if (sr+2 == dr && dr == 3 && sf == df) {
					return true;
				}
			} else {
				if (sf-1 == df && sr-1 == dr) {
					return true;
				} else if (sf+1 == df && sr-1 == dr) {
					return true;
				} else if (sr-1 == dr && sf == df) {
					return true;
				} else if (sr-2 == dr && dr == 4 && sf == df) {
					return true;
				}
			}
			//TODO implement queening
		} else {}

		return false;
	}

	private boolean isTurn(String color) {
		if (turn == color) {
			return true;
		} else {
			return false;
		}
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
			if (board[r+1][f+1] == blackKing) {
				isThreat = true;
			}
			if (board[r+1][f+1] instanceof Pawn 
					&& board[r+1][f+1].getColor().equals("black")) {
				isThreat = true;
			}
		}
		//down-right
		if (r+1 < 8 && f-1 > -1) {
			if (board[r+1][f-1] == blackKing) {
				isThreat = true;
			}
		}
		//up-left
		if (r-1 > -1 && f+1 < 8) {
			if (board[r-1][f+1] == blackKing) {
				isThreat = true;
			}
			if (board[r-1][f+1] instanceof Pawn 
					&& board[r-1][f+1].getColor().equals("black")) {
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
		//down-right
		if (r+1 < 8 && f-1 > -1) {
			if (board[r+1][f-1] == whiteKing) {
				isThreat = true;
			}
			if (board[r+1][f-1] instanceof Pawn 
					&& board[r+1][f-1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		//up-left
		if (r-1 > -1 && f+1 < 8) {
			if (board[r-1][f+1] == whiteKing) {
				isThreat = true;
			}
		}
		//down-left
		if (r-1 > -1 && f-1 > -1) {
			if (board[r-1][f-1] == whiteKing) {
				isThreat = true;
			}
			if (board[r-1][f-1] instanceof Pawn 
					&& board[r-1][f-1].getColor().equals("white")) {
				isThreat = true;
			}
		}
		return isThreat;
	}

	private ArrayList<Move> kingMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();

		if (file+1 < 8 && rank+1 < 8) {
			Location l = new Location(file+1,rank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank-1 > -1) {
			Location l = new Location(file+1,rank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8) {
			Location l = new Location(file+1,rank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (rank+1 < 8) {
			Location l = new Location(file,rank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (rank-1 > -1) {
			Location l = new Location(file,rank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank+1 < 8) {
			Location l = new Location(file-1,rank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank-1 > -1) {
			Location l = new Location(file-1,rank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1) {
			Location l = new Location(file-1,rank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}

		return arr;
	}
	private ArrayList<Move> queenMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();
		int tempfile = file, temprank = rank;

		while (tempfile+1 < 8 && temprank+1 < 8) {
			Location l = new Location(tempfile+1,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8 && temprank-1 > -1) {
			Location l = new Location(tempfile+1,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8) {
			Location l = new Location(tempfile+1,temprank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank+1 < 8) {
			Location l = new Location(tempfile-1,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank-1 > -1) {
			Location l = new Location(tempfile-1,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1) {
			Location l = new Location(tempfile-1,temprank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
		}
		tempfile = file; temprank = rank;
		while (temprank+1 < 8) {
			Location l = new Location(tempfile,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (temprank-1 > -1) {
			Location l = new Location(tempfile,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			temprank--;
		}

		return arr;
	}
	private ArrayList<Move> bishopMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();
		int tempfile = file, temprank = rank;

		while (tempfile+1 < 8 && temprank+1 < 8) {
			Location l = new Location(tempfile+1,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8 && temprank-1 > -1) {
			Location l = new Location(tempfile+1,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank+1 < 8) {
			Location l = new Location(tempfile-1,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank-1 > -1) {
			Location l = new Location(tempfile-1,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank--;
		}

		return arr;
	}
	private ArrayList<Move> knightMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();

		if (file+2 < 8 && rank+1 < 8) {
			Location l = new Location(file+2,rank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+2 < 8 && rank-1 > -1) {
			Location l = new Location(file+2,rank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-2 > -1 && rank+1 < 8) {
			Location l = new Location(file-2,rank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-2 > -1 && rank-1 > -1) {
			Location l = new Location(file-2,rank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank+2 < 8) {
			Location l = new Location(file+1,rank+2);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank-2 > -1) {
			Location l = new Location(file+1,rank-2);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank+2 < 8) {
			Location l = new Location(file-1,rank+2);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank-2 > -1) {
			Location l = new Location(file-1,rank-2);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}

		return arr;
	}
	private ArrayList<Move> rookMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();
		int tempfile = file, temprank = rank;

		while (tempfile+1 < 8) {
			Location l = new Location(tempfile+1,temprank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1) {
			Location l = new Location(tempfile-1,temprank);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			tempfile--;
		}
		tempfile = file; temprank = rank;
		while (temprank+1 < 8) {
			Location l = new Location(tempfile,temprank+1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (temprank-1 > -1) {
			Location l = new Location(tempfile,temprank-1);
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece 
					&& getPiece(l.getFileByInt(),l.getRank()).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l.getFileByInt(), l.getRank()) instanceof Piece) {
				break;
			}
			temprank--;
		}

		return arr;
	}
	private ArrayList<Move> pawnMoves(Location loc, String color) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();

		if (color == "white") {
			if (rank == 1) {
				arr.add(new Move(loc,new Location(file,rank+2)));
			}
			arr.add(new Move(loc,new Location(file,rank+1)));
			if (file+1 < 8 && rank+1 < 8) {
				if (getPiece(file+1,rank+1) != null 
						&& getPiece(file+1,rank+1).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file+1,rank+1)));
				}
			}
			if (file-1 > -1 && rank+1 < 8) {
				if (getPiece(file-1,rank+1) != null 
						&& getPiece(file-1,rank+1).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file-1,rank+1)));
				}
			}
		}
		if (color == "black") {
			if (rank == 6) {
				arr.add(new Move(loc,new Location(file,rank-2)));
			}
			arr.add(new Move(loc,new Location(file,rank-1)));
			if (file-1 > -1 && rank-1 > -1) {
				if (getPiece(file-1,rank-1) != null 
						&& getPiece(file-1,rank-1).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file-1,rank-1)));
				}
			}
			if (file+1 < 8 && rank-1 > -1) {
				if (getPiece(file+1,rank-1) != null 
						&& getPiece(file+1,rank-1).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file+1,rank-1)));
				}
			}
		}

		//TODO implement en passant
		return arr;
	}

	public void print() {
		// I know, bad practice, I was just too lazy to Find/Replace and switch all the board calls
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
		System.out.println("turn = "+turn);
	}

	public String inCheckmate() {
		Board tempBoard = this;
		ArrayList<Move> moves = getMoveOptions();
		for (int i = 0; i < moves.size(); i++) {
			Piece tempPiece = tempBoard.getPiece(moves.get(i).getSrc().getFileByInt(),moves.get(i).getSrc().getRank());
			tempBoard.place(tempPiece,new Location(moves.get(i).getDest().getFileByInt(),moves.get(i).getDest().getRank()));
			tempBoard.place(null,new Location(moves.get(i).getSrc().getFileByInt(),moves.get(i).getSrc().getRank()));
			if (kingInCheck()) {
				//do nothing
			} else {
				tempBoard = this;
				return "NOT";
			}
			tempBoard = this;
		}
		if (!kingInCheck()) {
			return "draw";
		} else if (turn == "white") {
			return "white";
		} else {
			return "black";
		}
	}

	public ArrayList<Move> getMoveOptions() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = getPiece(i,j);
				if (piece != null && piece.getColor().equals(turn)) {				
					if (piece instanceof King) {
						moves.addAll(kingMoves(new Location(i,j)));
					} else if (piece instanceof Queen) {
						moves.addAll(queenMoves(new Location(i,j)));
					} else if (piece instanceof Bishop) {
						moves.addAll(bishopMoves(new Location(i,j)));
					} else if (piece instanceof Knight) {
						moves.addAll(knightMoves(new Location(i,j)));
					} else if (piece instanceof Rook) {
						moves.addAll(rookMoves(new Location(i,j)));
					} else if (piece instanceof Pawn) {
						moves.addAll(pawnMoves(new Location(i,j),turn));
					}
				}
			}
		}
		return moves;
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
		//TODO add white square draw
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
	
	*/
	
}

