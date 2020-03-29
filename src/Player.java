
public class Player {
	String name;
	Token playerToken;
	int tokensCounter;
	
	public Player(String name) {
		this.name = name;
		this.tokensCounter = 12;
	}

	public Token getPlayerToken() {
		return playerToken;
	}

	public void setPlayerToken(Token playerToken) {
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
