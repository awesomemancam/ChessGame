package camden.ivarcode.net;

public class Game {

	private String turn;
	
	public Game() {
		setTurn("white");
	}
	
	public void play() {
		//TODO
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
	}
	
}
