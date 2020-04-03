
public class Game {

	int round;
	Player player;
	Player currentPlayerTurn;

	public Game(String name, int colour) {
		this.player = new Player(name, colour);
		this.round = 0;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int n) {
		this.round = n;
	}

	public void nextRound() {
		this.round = this.round + 1;
	}

	public void resetRound() {
		this.round = 0;
	}

	public Player getcurrentPlayerTurn() {
		return this.currentPlayerTurn;
	}
	
	public void setPlayerTurn() {
		if (this.round % 2 == 0 && this.player.getPlayerToken() == 0) {
			this.currentPlayerTurn = this.player;
		} else if (this.round % 2 != 0 && this.player.getPlayerToken() != 0) {
			this.currentPlayerTurn = this.player;
		}
	}

	public void checkLoser() {
		if (this.player.getTokensCounter() == 0) {
			System.out.println("Player 1 wins.");
		} else if(this.player.getTokensCounter() == 0) {
			System.out.println("Player 2 wins.");
		}
	}
}
