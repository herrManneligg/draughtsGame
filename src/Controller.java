import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JButton;

public class Controller implements ActionListener {

	private Game game;
	private Client gameView;
	private Socket server = null;
	private SquareButton prevSquare;
	private ObjectOutputStream outputStream;

	private List<SquareButton> possibleTokenMovements;

	int row;
	int column;

	public Controller(Client view) {
		this.gameView = view;
		prevSquare = null;
	}

	public void actionPerformed(ActionEvent e) {

//		Checking whether the clicked button is an instance of a SquareButton.
		if (e.getSource() instanceof SquareButton && this.game.currentPlayerTurn == this.game.player) {

			SquareButton squareButton = ((SquareButton) e.getSource());

			/*
			 * The SquareButton is not null = the player selected a token. *Further
			 * implementation; check if the currentPlayer selected their token*
			 */
			
			if (squareButton.getToken() != null && squareButton.getToken().getColour() == this.game.player.getPlayerToken()) {

				/*
				 * The player changes his mind and selects a different token.
				 */
				if (this.prevSquare != null) {
					this.prevSquare.setBackground(Color.black);
					this.prevSquare = null;
				}
				/*
				 * If the player previously selected a king, the possible moves are showed in
				 * green. This turns the squares back to black and resets the possible moves
				 * list for the king.
				 */
				if (possibleTokenMovements != null) {

					for (int i = 0; i < possibleTokenMovements.size(); i++) {
						this.possibleTokenMovements.get(i).setBackground(Color.black);
					}

				}

				this.possibleTokenMovements = squareButton.getToken().checkPosibleMoves(squareButton, this.gameView);

				/*
				 * Highlight the token selected and store the SquareButton as the previouSquare
				 * until the player selects an empty square.
				 */
				squareButton.setBackground(Color.yellow);
				this.prevSquare = squareButton;
			}
			/*
			 * The player selects a new squareButton that it is empty. If the previousSquare
			 * is not empty, it means that the player wants to move the previous token to
			 * that new position.
			 */
			else if (squareButton.getToken() == null && prevSquare != null) {

				/*
				 * Checking whether the movement is legal for Men or King. For the Kings it
				 * compares the next movement proposed by the player with a list of possible
				 * moves determined by the previous SquareButton selected. *Further
				 * implementation - Men should have a similar implementation.*
				 * 
				 */
//				if (this.prevSquare.getToken().checkPosibleMoves(prevSquare, this.gameView).contains(squareButton)) {
				if (this.possibleTokenMovements.contains(squareButton)) {

//					Adding implementation for King's kills - Men will be implemented in the same way
					if (this.prevSquare.getToken().hasAKill(squareButton) != null) {

						this.prevSquare.getToken().hasAKill(squareButton).removeToken();
						this.prevSquare.getToken().getKillerMovemenets().clear();

					} else if (!this.prevSquare.getToken().getKillerMovemenets().isEmpty()) {
						this.prevSquare.getToken().getKillerMovemenets().clear();
					}
					
					System.out.println(prevSquare.toString());
					System.out.println(squareButton.toString());

					if (server != null) {

						try {
							outputStream.writeObject(new MovementUpdate(prevSquare, squareButton));
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}

					place(squareButton);
					remove(this.prevSquare);
					
					passRound();

					if (possibleTokenMovements != null) {
						for (int i = 0; i < possibleTokenMovements.size(); i++) {
							possibleTokenMovements.get(i).setBackground(Color.black);
						}
						possibleTokenMovements = null;
					}
				}
			}
			if (squareButton.getToken() != null && squareButton.getToken().getType() < 2) {
				if (squareButton.getToken().getType() == 0 && squareButton.getRow() == 7) {
					placeKing(squareButton, 2);
				} else if (squareButton.getToken().getType() == 1 && squareButton.getRow() == 0) {
					placeKing(squareButton, 3);
				}
			}
			System.out.println("Move: " + "r: " + squareButton.getRow() + ", c: " + squareButton.getColumn() + "\n");
		}

		else if (e.getSource() instanceof JButton) {
			JButton toolButton = (JButton) e.getSource();
			if (toolButton.getText().equals("Restart") || toolButton.getText().equals("Play")) {

				removeAllTokens();
				addInitialTokens();
				this.prevSquare = null;
				gameView.restart.setText("Restart");
				if (this.possibleTokenMovements != null) {
					this.possibleTokenMovements.clear();
				}
				

			} else if (toolButton.getText().equals("Connect")) {
				this.gameView.infoScreen.setText("Connecting...");
				
				connect();
				
				try {
					outputStream = new ObjectOutputStream(server.getOutputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				ReadWorker rw = new ReadWorker(server, this);
				rw.execute();

			} else if (toolButton.getActionCommand().equals("Player")) {
				this.game = new Game(toolButton.getText(), toolButton.getText().equals("Player 1") ? 0 : 1);
				this.gameView.infoScreen.setText("Welcome " + toolButton.getText() + "!");
				this.game.setPlayerTurn();
				enableButtons();
				System.out.println(game.player);
			} 
		}
	}

	private void connect() {
		
		try {
			
			server = new Socket("127.0.0.1", 8765);
			this.gameView.infoScreen.setText("Connected!");
			this.gameView.connect.setEnabled(false);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			this.gameView.infoScreen.setText("Unnable to connect...");
		}
	}

	private void placeKing(SquareButton kingSquare, int type) {
		kingSquare.setToken(new King(type));
	}

	private void place(SquareButton nextLoc) {
		nextLoc.setToken(this.prevSquare.getToken());
		this.prevSquare.setBackground(Color.black);
	}

	public void remove(SquareButton jbutton) {
		jbutton.removeToken();
		this.prevSquare = null;
	}

	public void move(SquareButton prev, SquareButton next) {
		next.setToken(prev.getToken());
		prev.removeToken();
	}

	public int getColDirection(int prevCol, int nextCol) {
		return nextCol < prevCol ? -1 : 1;
	}

	public Client getView() {
		return this.gameView;
	}

	public void addInitialTokens() {

		for (SquareButton blackButton : gameView.blackButtons) {
			if (blackButton.row < 3) {
				blackButton.setToken(new Men(0));
			} else if (blackButton.row > 4) {
				blackButton.setToken(new Men(1));
			}
		}
	}

	public void removeAllTokens() {
		for (SquareButton blackButton : gameView.blackButtons) {
			blackButton.removeToken();
		}
	}
	
	public void enableButtons() {
		gameView.resign.setEnabled(true);
		gameView.restart.setEnabled(true);
		gameView.connect.setEnabled(true);
		gameView.player1.setEnabled(false);
		gameView.player2.setEnabled(false);
	}
	
	public void passRound() {
		this.game.nextRound();
		this.game.setPlayerTurn();
	}
}
