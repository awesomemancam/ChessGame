package camden.ivarcode.net;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import camden.ivarcode.net.piece.*;

public class Game {

	private String turn;
	private Board board, checkBoard;
	private int sideWidth, topHeight, sqDim;

	public Game(int sW, int tH, int sD) {
		setTurn("white");
		board = new Board();
		checkBoard = new Board();
		this.sideWidth = sW;
		this.topHeight = tH;
		this.sqDim = sD;
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
		System.out.println(turn + " turn");
	}
	//

	public void makeMove(Location src, Location dest) {
		if (isLegalMove(src,dest)) {
			System.out.println("isLegalMove");
			changeTurn();
			board.movePiece(src, dest);
			//TODO record move	
			//TODO check for mate
		}
	}

	private boolean isLegalMove(Location src, Location dest) {
		ArrayList<Move> moves = getMoveOptions();
		for (int i = 0; i < moves.size(); i++) {
			if (src.getFile() == moves.get(i).getSrc().getFile() && 
					src.getRank() == moves.get(i).getSrc().getRank() && 
					dest.getFile() == moves.get(i).getDest().getFile() && 
					dest.getRank() == moves.get(i).getDest().getRank()) {
				boardCopy(board,checkBoard);
				checkBoard.movePiece(src, dest);
				if (!kingInCheck(checkBoard)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public ArrayList<Move> getMoveOptions() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Location loc = new Location(i,j);
				Piece piece = getPiece(loc);
				if (piece != null && piece.getColor().equals(turn)) {				
					if (piece instanceof King) {
						moves.addAll(kingMoves(loc));
					} else if (piece instanceof Queen) {
						moves.addAll(queenMoves(loc));
					} else if (piece instanceof Bishop) {
						moves.addAll(bishopMoves(loc));
					} else if (piece instanceof Knight) {
						moves.addAll(knightMoves(loc));
					} else if (piece instanceof Rook) {
						moves.addAll(rookMoves(loc));
					} else if (piece instanceof Pawn) {
						moves.addAll(pawnMoves(loc,turn));
					}
				}
			}
		}
		return moves;
	}

	private boolean kingInCheck(Board b) {
		Location loc = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (b.getPiece(new Location(i,j)) instanceof King &&
						b.getPiece(new Location(i,j)).getColor() == turn) {
					loc = new Location(i,j);
					break;
				}
			}
		}
		if (loc == null) {
			System.out.println("NO [" + turn + "] KING ON BOARD");
			return false;
		}
		int file, rank;
		file = loc.getFileByInt()+1;
		rank = loc.getRank();
		while (file < 8) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Rook) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file++;
		}
		file = loc.getFileByInt()-1;
		rank = loc.getRank();
		while (file > -1) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Rook) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file--;
		}
		file = loc.getFileByInt();
		rank = loc.getRank()+1;
		while (rank < 8) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Rook) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			rank++;
		}
		file = loc.getFileByInt();
		rank = loc.getRank()-1;
		while (rank > -1) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Rook) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			rank--;
		}
		file = loc.getFileByInt()+1;
		rank = loc.getRank()+1;
		while (file < 8 && rank < 8) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Bishop) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file++;
			rank++;
		}
		file = loc.getFileByInt()+1;
		rank = loc.getRank()-1;
		while (file < 8 && rank > -1) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Bishop) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file++;
			rank--;
		}
		file = loc.getFileByInt()-1;
		rank = loc.getRank()+1;
		while (file > -1 && rank < 8) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Bishop) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file--;
			rank++;
		}
		file = loc.getFileByInt()-1;
		rank = loc.getRank()-1;
		while (file > -1 && rank > -1) {
			if (b.getPiece(new Location(file,rank)) instanceof Queen ||
					b.getPiece(new Location(file,rank)) instanceof Bishop) {
				if (b.getPiece(new Location(file,rank)).getColor() == turn) {
					break;
				} else {
					return true;
				}
			}
			file--;
			rank--;
		}

		file = loc.getFileByInt();
		rank = loc.getRank();

		//King
		if (b.getPiece(new Location(file+1,rank)) instanceof King
				|| b.getPiece(new Location(file-1,rank)) instanceof King
				|| b.getPiece(new Location(file,rank+1)) instanceof King
				|| b.getPiece(new Location(file,rank-1)) instanceof King
				|| b.getPiece(new Location(file+1,rank+1)) instanceof King
				|| b.getPiece(new Location(file+1,rank-1)) instanceof King
				|| b.getPiece(new Location(file-1,rank+1)) instanceof King
				|| b.getPiece(new Location(file-1,rank-1)) instanceof King) {
			return true;
		}

		//Knight
		if ((b.getPiece(new Location(file+1,rank+2)) instanceof Knight &&
				b.getPiece(new Location(file+1,rank+2)).getColor() != turn)
				|| (b.getPiece(new Location(file+1,rank-2)) instanceof Knight &&
						b.getPiece(new Location(file+1,rank-2)).getColor() != turn)
				|| (b.getPiece(new Location(file+2,rank+1)) instanceof Knight &&
						b.getPiece(new Location(file+2,rank+1)).getColor() != turn)
				|| (b.getPiece(new Location(file+2,rank-1)) instanceof Knight &&
						b.getPiece(new Location(file+2,rank-1)).getColor() != turn)
				|| (b.getPiece(new Location(file-1,rank+2)) instanceof Knight &&
						b.getPiece(new Location(file-1,rank+2)).getColor() != turn)
				|| (b.getPiece(new Location(file-1,rank-2)) instanceof Knight &&
						b.getPiece(new Location(file-1,rank-2)).getColor() != turn)
				|| (b.getPiece(new Location(file-2,rank+1)) instanceof Knight &&
						b.getPiece(new Location(file-2,rank+1)).getColor() != turn)
				|| (b.getPiece(new Location(file-2,rank-1)) instanceof Knight &&
						b.getPiece(new Location(file-2,rank-1)).getColor() != turn)) {
			return true;
		}

		//Pawn
		if (turn == "white") {
			if ((b.getPiece(new Location(file+1,rank+1)) instanceof Pawn &&
					b.getPiece(new Location(file+1,rank+1)).getColor() != turn)
					|| (b.getPiece(new Location(file-1,rank+1)) instanceof Pawn &&
							b.getPiece(new Location(file-1,rank+1)).getColor() != turn)) {
				return true;
			}
		} else {
			if ((b.getPiece(new Location(file+1,rank-1)) instanceof Pawn &&
					b.getPiece(new Location(file+1,rank-1)).getColor() != turn)
					|| (b.getPiece(new Location(file-1,rank-1)) instanceof Pawn &&
							b.getPiece(new Location(file-1,rank-1)).getColor() != turn)) {
				return true;
			}
		}

		return false;
	}

	//PieceMoves
	private ArrayList<Move> kingMoves(Location loc) {
		ArrayList<Move> arr = new ArrayList<Move>();
		int file = loc.getFileByInt();
		int rank = loc.getRank();

		if (file+1 < 8 && rank+1 < 8) {
			Location l = new Location(file+1,rank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank-1 > -1) {
			Location l = new Location(file+1,rank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8) {
			Location l = new Location(file+1,rank);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (rank+1 < 8) {
			Location l = new Location(file,rank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (rank-1 > -1) {
			Location l = new Location(file,rank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank+1 < 8) {
			Location l = new Location(file-1,rank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank-1 > -1) {
			Location l = new Location(file-1,rank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1) {
			Location l = new Location(file-1,rank);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
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
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8 && temprank-1 > -1) {
			Location l = new Location(tempfile+1,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8) {
			Location l = new Location(tempfile+1,temprank);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank+1 < 8) {
			Location l = new Location(tempfile-1,temprank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank-1 > -1) {
			Location l = new Location(tempfile-1,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1) {
			Location l = new Location(tempfile-1,temprank);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile--;
		}
		tempfile = file; temprank = rank;
		while (temprank+1 < 8) {
			Location l = new Location(tempfile,temprank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (temprank-1 > -1) {
			Location l = new Location(tempfile,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			temprank--;
		}

		//print
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).print();
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
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile+1 < 8 && temprank-1 > -1) {
			Location l = new Location(tempfile+1,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
			temprank--;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank+1 < 8) {
			Location l = new Location(tempfile-1,temprank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile--;
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1 && temprank-1 > -1) {
			Location l = new Location(tempfile-1,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
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
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+2 < 8 && rank-1 > -1) {
			Location l = new Location(file+2,rank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-2 > -1 && rank+1 < 8) {
			Location l = new Location(file-2,rank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-2 > -1 && rank-1 > -1) {
			Location l = new Location(file-2,rank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank+2 < 8) {
			Location l = new Location(file+1,rank+2);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file+1 < 8 && rank-2 > -1) {
			Location l = new Location(file+1,rank-2);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank+2 < 8) {
			Location l = new Location(file-1,rank+2);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				//don't add(l)
			} else {
				arr.add(new Move(loc,l));
			}			
		}
		if (file-1 > -1 && rank-2 > -1) {
			Location l = new Location(file-1,rank-2);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
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
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile++;
		}
		tempfile = file; temprank = rank;
		while (tempfile-1 > -1) {
			Location l = new Location(tempfile-1,temprank);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			tempfile--;
		}
		tempfile = file; temprank = rank;
		while (temprank+1 < 8) {
			Location l = new Location(tempfile,temprank+1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
				break;
			}
			temprank++;
		}
		tempfile = file; temprank = rank;
		while (temprank-1 > -1) {
			Location l = new Location(tempfile,temprank-1);
			if (getPiece(l) instanceof Piece 
					&& getPiece(l).getColor() == turn) {
				break;
			}
			arr.add(new Move(loc,l));
			if (getPiece(l) instanceof Piece) {
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
				if (getPiece(new Location(file+1,rank+1)) != null 
						&& getPiece(new Location(file+1,rank+1)).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file+1,rank+1)));
				}
			}
			if (file-1 > -1 && rank+1 < 8) {
				if (getPiece(new Location(file-1,rank+1)) != null 
						&& getPiece(new Location(file-1,rank+1)).getColor().equals(turn)) {
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
				if (getPiece(new Location(file-1,rank-1)) != null 
						&& getPiece(new Location(file-1,rank-1)).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file-1,rank-1)));
				}
			}
			if (file+1 < 8 && rank-1 > -1) {
				if (getPiece(new Location(file+1,rank-1)) != null 
						&& getPiece(new Location(file+1,rank-1)).getColor().equals(turn)) {
					//do nothing
				} else {
					arr.add(new Move(loc,new Location(file+1,rank-1)));
				}
			}
		}

		//TODO implement en passant
		return arr;
	}
	//

	//Draw
	public void drawBoard(Graphics g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				drawSquare(g, new Location(i,j));
			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(sideWidth, topHeight, sqDim*8, sqDim*8);
	}
	public void drawPiece(Graphics g, Location loc) {
		if (getPiece(loc) != null) {
			g.drawImage(getPiece(loc).getImg(), 
					sideWidth+(sqDim*loc.getFileByInt()), 
					topHeight+(sqDim*(7-loc.getRank())), 
					null);
		}
	}
	public void drawPieceAt(Graphics g, Location loc, Location newloc) {
		if (getPiece(loc) != null) {
			g.drawImage(getPiece(loc).getImg(), 
					sideWidth+(sqDim*newloc.getFileByInt()), 
					topHeight+(sqDim*(7-newloc.getRank())), 
					null);
		}
	}
	public void drawSquare(Graphics g, Location loc) {
		if ((loc.getFileByInt()+loc.getRank())%2 == 0) {
			g.setColor(Color.DARK_GRAY);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(sideWidth+(sqDim*loc.getFileByInt()), 
				topHeight+(sqDim*(7-loc.getRank())), 
				sqDim, sqDim);
	}
	public void drawSquareTinted(Graphics g, Location loc) {
		if ((loc.getFileByInt()+loc.getRank())%2 == 0) {
			g.setColor(Color.YELLOW); // TODO change
		} else {
			g.setColor(Color.YELLOW); // TODO change
		}
		g.fillRect(sideWidth+(sqDim*loc.getFileByInt()), 
				topHeight+(sqDim*(7-loc.getRank())), 
				sqDim, sqDim);
	}
	//

	public void boardCopy(Board a, Board b) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Location loc = new Location(i,j);
				b.placePiece(a.getPiece(loc), loc);
			}
		}
	}

}
