package net.ivarcode.camden;

import java.util.ArrayList;

import net.ivarcode.camden.piece.Piece;

public class Game {

	private String turn;
	private Board board, checkBoard;
	private ArrayList<Move> record;
	private int sideWidth, topHeight, sqDim;
	Piece[] pieceStore, promoPieces;
	private boolean promoting;


	public Game(int sW, int tH, int sD) {
		System.out.println("Preparing new game...");
		setTurn("white");
		promoting = false;
		pieceStore = new Piece[4];
		promoPieces = new Piece[4];
		board = new Board();
		checkBoard = new Board();
		record = new ArrayList<Move>();
		this.sideWidth = sW;
		this.topHeight = tH;
		this.sqDim = sD;
		System.out.println("Ready to play.");
	}

	
	public boolean makeMove(Move move) {
		boolean promoting = false;
		/*if (isLegalMove(move)) {
			boolean check = false, checkmate = false, stalemate = false, capture = false, castling = false;
			if (getPiece(move.getDest()) != null) {
				capture = true;
			}
			Piece piece = getPiece(move.getSrc());
			if (getPiece(move.getSrc()) instanceof King
					&& Math.abs(move.getSrc().getFileByInt() - move.getDest().getFileByInt()) == 2) {
				castling = true;
				Location rookSrc;
				Location rookDest;
				if (move.getDest().getFileByInt() == 6) {
					rookSrc = new Location(7, move.getSrc().getRank());
					rookDest = new Location(5, move.getSrc().getRank());
				} else if (move.getDest().getFileByInt() == 2)  {
					rookSrc = new Location(0, move.getSrc().getRank());
					rookDest = new Location(3, move.getSrc().getRank());
				}
				board.movePiece(rookSrc, rookDest);
			}
			if (getPiece(move.getSrc()) instanceof Pawn) {
				if (getPiece(move.getDest()) == null
						&& move.getSrc().getFileByInt() != move.getDest().getFileByInt()) {
					capture = true;
				}
				if (move.getDest().getRank() == 0 || move.getDest().getRank() == 7) {
					promoting = true;
				}
			}
			changeTurn();
			board.movePiece(move.getSrc(), move.getDest());
			getPiece(new Location(move.getDest().getFile(), move.getDest().getRank())).setHasMoved(true);

			if (kingInCheck(board)) {
				check = true;
			}
			ArrayList<Move> moveOptions = getMoveOptions();
			for (int i = 0; i < moveOptions.size(); i++) {
				boardCopy(board, checkBoard);
				checkBoard.movePiece(moveOptions.get(i).getSrc(), moveOptions.get(i).getDest());
				if (kingInCheck(checkBoard)) {
					moveOptions.remove(i);
					i--;
				}
			}
			if (moveOptions.size() == 0) {
				if (check) {
					checkmate = true;
					System.out.print("checkmate");
				} else {
					stalemate = true;
					System.out.print("stalemate");
				}
			}*/
			//
			
			if (promoting) {
				return true;
			} else {
				//recordMove(move, piece, check, checkmate, stalemate, capture, castling);
				//TODO check what to return
			}
			return false;
		//}
	}
	
	
	
	
	
	
	
	//Getters & Setters
	public Piece getPiece(Location src) {
		if (src.getFileByInt() < 8 && src.getFileByInt() > -1
				&& src.getRank() < 8 && src.getRank() > -1) {
			return board.getPiece(src);
		} else {
			return null;
		}
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public void changeTurn() {
		if (getTurn().equals("white")) {
			setTurn("black");
		} else {
			setTurn("white");
		}
		//System.out.println(turn + " turn");
	}
	public Move getMove(int i) {
		if (i <= record.size()) {
			return record.get(i);
		}
		return null;
	}
	//

}
