
public class Player {
	String name;
	int playerToken; // 0 = RED TOKENS, 1 = WHITE TOKENS
	int tokensCounter; // There is 12 tokens for each player. This counts how many tokens each player has. Once it reaches 0, the player loses.
	
	public Player(String name, int tokenscolour) {
		this.name = name;
		this.playerToken = tokenscolour;
		this.tokensCounter = 12;
	}

	public int getPlayerToken() {
		return playerToken;
	}

	public void setPlayerToken(int playerToken) {
		this.playerToken = playerToken;
	}

	public int getTokensCounter() {
		return tokensCounter;
	}

	public void setTokensCounter(int tokensCounter) {
		this.tokensCounter = tokensCounter;
	}
	
	public void restCounter() {
		this.tokensCounter -= tokensCounter;
	}
	
	public String getPlayerName() {
		return this.name;
	}
	
	public String toString() {
		return "I am " + this.name + "and my colour is " + (this.playerToken == 0 ? "red." : "white.");
	}
}
