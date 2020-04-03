
public class Game {

	int round;
	boolean agaisntCPU;
	Player player;
	CPU playerCPU;
	Player currentPlayerTurn;

	public Game(String name, int colour) {
		this.player = new Player(name, colour);
		this.round = 0;
	}
	
	public Game(String name, int colour, Client gameView, Controller c) {
		this.playerCPU = new CPU(name, colour, gameView, c);
		this.agaisntCPU = true;
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
	
	public boolean isAgaisntCPU() {
		return this.agaisntCPU;
	}
	
	public void setPlayerTurn() {
		if (this.round % 2 == 0 && this.player.getPlayerToken() == 0) {
			System.out.println("holaaaaaa");
			this.currentPlayerTurn = this.player;
			
		} else if (isAgaisntCPU() == true) {
			System.out.println("hey");
			playerCPU.cpuMove();
			this.currentPlayerTurn = this.playerCPU;
			
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
