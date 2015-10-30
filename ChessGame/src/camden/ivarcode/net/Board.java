package camden.ivarcode.net;

public class Board {

	private Piece[][] board = new Piece[8][8];
	private Piece whiteKing, blackKing,
	whiteQueen, blackQueen,
	whiteBishop, blackBishop,
	whiteKnight, blackKnight,
	whiteRook, blackRook,
	whitePawn, blackPawn;
	private String turn;

	public Board() {
		//initialize nothing
	}

	public Board(Piece wKing, Piece wQueen, Piece wBishop, Piece wKnight, Piece wRook, Piece wPawn,
			Piece bKing, Piece bQueen, Piece bBishop, Piece bKnight, Piece bRook, Piece bPawn) {
		whiteKing = wKing;
		blackKing = bKing;
		whiteQueen = wQueen;
		blackQueen = bQueen;
		whiteBishop = wBishop;
		blackBishop = bBishop;
		whiteKnight = wKnight;
		blackKnight = bKnight;
		whiteRook = wRook;
		blackRook = bRook;
		whitePawn = wPawn;
		blackPawn = bPawn;
		turn = "white";

		for (int j = 0; j < 8; j++) {
			board[1][j] = whitePawn;
			board[6][j] = blackPawn;
		}
		board[0][4] = whiteKing;
		board[7][4] = blackKing;
		board[0][3] = whiteQueen;
		board[7][3] = blackQueen;
		board[0][2] = whiteBishop;
		board[0][5] = whiteBishop;
		board[7][2] = blackBishop;
		board[7][5] = blackBishop;
		board[0][1] = whiteKnight;
		board[0][6] = whiteKnight;
		board[7][1] = blackKnight;
		board[7][6] = blackKnight;
		board[0][0] = whiteRook;
		board[0][7] = whiteRook;
		board[7][0] = blackRook;
		board[7][7] = blackRook;
	}

	public Piece getPiece(int rank, char file) {
		int i = -1; //throws error if case not 'a'-'h'
		switch (file) {
		case 'a':	i = 0;
		case 'b':	i = 1;
		case 'c':	i = 2;
		case 'd':	i = 3;
		case 'e':	i = 4;
		case 'f':	i = 5;
		case 'g':	i = 6;
		case 'h':	i = 7;
		}
		return board[i][file];
	}

	public void movePiece(Piece piece, int orig_rank, char orig_file, 
			int new_rank, char new_file) {
		if (moveAllowed(piece, orig_rank, orig_file, new_rank, new_file)) {
			//TODO move piece			
		}
	}

	public boolean moveAllowed(Piece piece, int orig_rank, char orig_file, 
			int new_rank, char new_file) {
		if ((getPiece(orig_rank, orig_file) != piece) 
				|| (ableToMove(new_rank, new_file)) 
				|| (kingInCheck())
				|| (!isTurn(piece.getColor()))) {
			return false;
		}

		return true;
	}

	private boolean ableToMove(int new_rank, char new_file) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isTurn(String color) {
		if (turn == color) {
			return true;
		} else {
			return false;
		}
	}

	private boolean kingInCheck() {
		int r;
		int f;
		if (isTurn("white")) {
			r = whiteKing.getLoc().getRank();
			f = whiteKing.getLoc().getFileByInt();
		} else {
			r = blackKing.getLoc().getRank();
			f = blackKing.getLoc().getFileByInt();
		}
		if (isTurn("white")) {
			return whiteThreatens(r, f);
		} else {
			return blackThreatens(r, f);
		}
	}

	private boolean blackThreatens(int r, int f) {
		boolean isThreat = false;
		int tR = r, tF = f;
		//up
		while (tR-1 > -1) {
			tR -= 1;
			if (board[tR][tF] == whiteRook 
					|| board[tR][tF] == whiteQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down
		tR = r; tF = f;
		while (tR+1 < 8) {
			tR += 1;
			if (board[tR][tF] == whiteRook 
					|| board[tR][tF] == whiteQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//right
		tR = r; tF = f;
		while (tF+1 < 8) {
			tF += 1;
			if (board[tR][tF] == whiteRook 
					|| board[tR][tF] == whiteQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//left
		tR = r; tF = f;
		while (tF-1 > -1) {
			tF -= 1;
			if (board[tR][tF] == whiteRook 
					|| board[tR][tF] == whiteQueen) {
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
			if (board[tR][tF] == whiteBishop 
					|| board[tR][tF] == whiteQueen) {
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
			if (board[tR][tF] == whiteBishop 
					|| board[tR][tF] == whiteQueen) {
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
			if (board[tR][tF] == whiteBishop 
					|| board[tR][tF] == whiteQueen) {
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
			if (board[tR][tF] == whiteBishop 
					|| board[tR][tF] == whiteQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//knight
		if (r+2 < 8 && f+1 < 8)	{
			if (board[r+2][f+1] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r+2 < 8 && f-1 > -1) {
			if (board[r+2][f-1] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f+2 < 8)	{
			if (board[r+1][f+2] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f-2 > -1) {
			if (board[r+1][f-2] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f+2 < 8) {
			if (board[r-1][f+2] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f-2 > -1) {
			if (board[r-1][f-2] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f+1 < 8) {
			if (board[r-2][f+1] == whiteKnight) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f-1 > -1) {
			if (board[r-2][f-1] == whiteKnight) {
				isThreat = true;
			}
		}
		//around (king-check) && include pawn check here
		//up
		if (r-1 > -1) {
			if (board[r-1][f] == whiteKing) {
				isThreat = true;
			}
		}
		//down
		if (r+1 < 8) {
			if (board[r+1][f] == whiteKing) {
				isThreat = true;
			}
		}
		//right
		if (f+1 < 8) {
			if (board[r][f+1] == whiteKing) {
				isThreat = true;
			}
		}
		//left
		if (f-1 > -1) {
			if (board[r][f-1] == whiteKing) {
				isThreat = true;
			}
		}
		//up-right
		if (r-1 > -1 && f+1 < 8) {
			if (board[r-1][f+1] == whiteKing) {
				isThreat = true;
			}
		}
		//up-left
		if (r-1 > -1 && f-1 > -1) {
			if (board[r-1][f-1] == whiteKing) {
				isThreat = true;
			}
		}
		//down-right
		if (r+1 < 8 && f+1 < 8) {
			if (board[r+1][f+1] == whiteKing
					|| board[r+1][f+1] == whitePawn) {
				isThreat = true;
			}
		}
		//down-left
		if (r+1 < 8 && f-1 > -1) {
			if (board[r+1][f-1] == whiteKing
					|| board[r+1][f-1] == whitePawn) {
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
			if (board[tR][tF] == blackRook 
					|| board[tR][tF] == blackQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//down
		tR = r; tF = f;
		while (tR+1 < 8) {
			tR += 1;
			if (board[tR][tF] == blackRook 
					|| board[tR][tF] == blackQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//right
		tR = r; tF = f;
		while (tF+1 < 8) {
			tF += 1;
			if (board[tR][tF] == blackRook 
					|| board[tR][tF] == blackQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//left
		tR = r; tF = f;
		while (tF-1 > -1) {
			tF -= 1;
			if (board[tR][tF] == blackRook 
					|| board[tR][tF] == blackQueen) {
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
			if (board[tR][tF] == blackBishop 
					|| board[tR][tF] == blackQueen) {
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
			if (board[tR][tF] == blackBishop 
					|| board[tR][tF] == blackQueen) {
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
			if (board[tR][tF] == blackBishop 
					|| board[tR][tF] == blackQueen) {
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
			if (board[tR][tF] == blackBishop 
					|| board[tR][tF] == blackQueen) {
				isThreat = true;
			} else if (board[tR][tF] != null) {
				break;
			}
		}
		//knight
		if (r+2 < 8 && f+1 < 8)	{
			if (board[r+2][f+1] == blackKnight) {
				isThreat = true;
			}
		}
		if (r+2 < 8 && f-1 > -1) {
			if (board[r+2][f-1] == blackKnight) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f+2 < 8)	{
			if (board[r+1][f+2] == blackKnight) {
				isThreat = true;
			}
		}
		if (r+1 < 8 && f-2 > -1) {
			if (board[r+1][f-2] == blackKnight) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f+2 < 8) {
			if (board[r-1][f+2] == blackKnight) {
				isThreat = true;
			}
		}
		if (r-1 > -1 && f-2 > -1) {
			if (board[r-1][f-2] == blackKnight) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f+1 < 8) {
			if (board[r-2][f+1] == blackKnight) {
				isThreat = true;
			}
		}
		if (r-2 > -1 && f-1 > -1) {
			if (board[r-2][f-1] == blackKnight) {
				isThreat = true;
			}
		}
		//around (king-check) && include pawn check here
		//up
		if (r-1 > -1) {
			if (board[r-1][f] == blackKing) {
				isThreat = true;
			}
		}
		//down
		if (r+1 < 8) {
			if (board[r+1][f] == blackKing) {
				isThreat = true;
			}
		}
		//right
		if (f+1 < 8) {
			if (board[r][f+1] == blackKing) {
				isThreat = true;
			}
		}
		//left
		if (f-1 > -1) {
			if (board[r][f-1] == blackKing) {
				isThreat = true;
			}
		}
		//up-right
		if (r-1 > -1 && f+1 < 8) {
			if (board[r-1][f+1] == blackKing
					|| board[r-1][f+1] == blackPawn) {
				isThreat = true;
			}
		}
		//up-left
		if (r-1 > -1 && f-1 > -1) {
			if (board[r-1][f-1] == blackKing
					|| board[r-1][f-1] == blackPawn) {
				isThreat = true;
			}
		}
		//down-right
		if (r+1 < 8 && f+1 < 8) {
			if (board[r+1][f+1] == blackKing) {
				isThreat = true;
			}
		}
		//down-left
		if (r+1 < 8 && f-1 > -1) {
			if (board[r+1][f-1] == blackKing) {
				isThreat = true;
			}
		}
		return isThreat;
	}

	public void draw() {
		//TODO
		//DRAW BOARD
	}

}