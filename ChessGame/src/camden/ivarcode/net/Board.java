package camden.ivarcode.net;

public class Board {

	private Piece[][] board = new Piece[8][8];
	private Piece whiteKing, blackKing;
	private String turn;
	
	public Board() {
		//initialize nothing
	}
	
	public Board(Piece wKing, Piece wQueen, Piece wBishop, Piece wKnight, Piece wRook, Piece wPawn,
			Piece bKing, Piece bQueen, Piece bBishop, Piece bKnight, Piece bRook, Piece bPawn) {
		for (int j = 0; j < 8; j++) {
			board[1][j] = wPawn;
			board[6][j] = bPawn;
		}
		turn = "white";
		whiteKing = wKing;
		blackKing = bKing;
		board[0][4] = whiteKing;
		board[7][4] = blackKing;
		board[0][3] = wQueen;
		board[7][3] = bQueen;
		board[0][2] = wBishop;
		board[0][5] = wBishop;
		board[7][2] = bBishop;
		board[7][5] = bBishop;
		board[0][1] = wKnight;
		board[0][6] = wKnight;
		board[7][1] = bKnight;
		board[7][6] = bKnight;
		board[0][0] = wRook;
		board[0][7] = wRook;
		board[7][0] = bRook;
		board[7][7] = bRook;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	public void draw() {
		//TODO
		//DRAW BOARD
	}
	
}
