package camden.ivarcode.net;

import java.awt.Graphics;

public class Game {
	
	private Piece currentPiece;
	private Graphics g;
	private String turn;
	private Board board;
	
	public Game(Graphics g) {
		this.g = g;
		setTurn("white");
		board = new Board(loadPieces());
	}
	private Piece[] loadPieces() {
		
	}
	
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	public void setCurrentPiece(Piece currentPiece) {
		this.currentPiece = currentPiece;
	}
	

	public void play() {
		//TODO		
		
	}
	
	public void changeTurn() {
		if (getTurn().equals("white")) {
			setTurn("black");
		} else {
			setTurn("white");
		}
	}

	public void makeMove(Location src, Location dest) {
		// TODO Auto-generated method stub
		
	}

	public Piece findPiece(Location src) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void paintGame() {
		
	}

}
