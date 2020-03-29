
public class Game {

	Player white;
	Player black;
	Player currentPlayerTurn;
	int round;

	public Game(String name, String name2) {
		this.white = new Player(name);
		this.black = new Player(name2);
		this.round = 0;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int n) {
		this.round = n;
	}

	public void nextRound() {
		this.round += this.round;
	}

	public void resetRound() {
		this.round = 0;
	}

	public Player getcurrentPlayerTurn() {
		return this.currentPlayerTurn;
	}
	
	public void setPlayerTurn() {
		if (this.round % 2 == 0) {
			this.white = currentPlayerTurn;
		} else {
			this.black = currentPlayerTurn;
		}
	}
	
	public void checkLoser() {
		if (this.white.getTokensCounter() == 0) {
			System.out.println("Player 1 loses. \nPlayer 2 wins.");
		} else if(this.black.getTokensCounter() == 0) {
			System.out.println("Player 2 loses. \nPlayer 1 wins.");
		}
	}
}
