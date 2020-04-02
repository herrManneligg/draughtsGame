
public class Player {
	String name;
	int playerToken; // 0 = RED TOKENS, 1 = WHITE TOKENS
	int tokensCounter; // There is 12 tokens for each player. This counts how many tokens each player has. Once it reaches 0, the player loses.
	
	public Player(String name) {
		this.name = name;
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
}
