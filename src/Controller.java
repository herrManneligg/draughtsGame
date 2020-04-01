import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;

public class Controller implements ActionListener {

	private View gameView;
	private SquareButton prevSquare;
	private List<SquareButton> possibleTokenMovements;

	int row;
	int column;

	public Controller(View view) {
		gameView = view;
		prevSquare = null;
	}

	public void actionPerformed(ActionEvent e) {

//		Checking whether the clicked button is an instance of a SquareButton.
		if (e.getSource() instanceof SquareButton) {

			SquareButton squareButton = ((SquareButton) e.getSource());

/*
 * 			The SquareButton is not null = the player selected a token.
 * 			*Further implementation; check if the currentPlayer selected their token*
 */
			if (squareButton.getToken() != null) {

/*
 * 			The player changes his mind and selects a different token.
 */
				if (this.prevSquare != null) {
					this.prevSquare.setBackground(Color.black);
					this.prevSquare = null;
				}
/*
 * 			If the player previously selected a king, the possible moves
 * 			are showed in green. This turns the squares back to black and
 * 			resets the possible moves list for the king.
 */
				if (possibleTokenMovements != null) {
					
					for(int i = 0; i < possibleTokenMovements.size(); i++) {
						this.possibleTokenMovements.get(i).setBackground(Color.black);
					}
					
				}
/*
 * 			///////////////////////////////////////////////////////////
 */
				this.possibleTokenMovements = squareButton.getToken().checkPosibleMoves(squareButton, this.gameView);
			
/*
 * 			Highlight the token selected and store the SquareButton as the
 * 			previouSquare until the player selects an empty square.
 */
				squareButton.setBackground(Color.yellow);
				this.prevSquare = squareButton;
			}
/*
 * 			The player selects a new squareButton that it is empty. If the
 * 			previousSquare is not empty, it means that the player wants to
 * 			move the previous token to that new position.
 */
			if (squareButton.getToken() == null && prevSquare != null) {

/*
 * 				Checking whether the movement is legal for Men or King. For the Kings it compares the next
 * 				movement proposed by the player with a list of possible moves determined by the previous SquareButton
 * 				selected. *Further implementation - Men should have a similar implementation.*
 * 
 */
//				if (this.prevSquare.getToken().checkPosibleMoves(prevSquare, this.gameView).contains(squareButton)) {
				if (this.possibleTokenMovements.contains(squareButton)) {
					
//					Adding implementation for King's kills - Men will be implemented in the same way
					if (this.prevSquare.getToken().hasAKill(squareButton) != null) {
						System.out.println("Hoooliiii");
						prevSquare.getToken().hasAKill(squareButton).removeToken();
					}
					
					place(squareButton);
					move(this.prevSquare);
					
				
					if (possibleTokenMovements != null) {
						for(int i = 0; i < possibleTokenMovements.size(); i++) {
							possibleTokenMovements.get(i).setBackground(Color.black);
						}
						possibleTokenMovements = null;
					}
				} 
				
				 

			}
			if (squareButton.getToken() != null && squareButton.getToken().getColour() < 2) {
				if (squareButton.getToken().getColour() == 0 && squareButton.getRow() == 7) {
					placeKing(squareButton, 2);
				} else if (squareButton.getToken().getColour() == 1 && squareButton.getRow() == 0) {
					placeKing(squareButton, 3);
				}
			}
			System.out.println("Move: " + "r: " + squareButton.getRow() + ", c: " + squareButton.getColumn() + "\n");
		}

//		Trying to check other buttons
//		 else if (e.getSource() instanceof JButton) {
//			JButton toolButton = (JButton)e.getSource();
//			if (toolButton.getText().equals("Play")) {
//			gameView.addInitialTokens();
//			gameView.repaint();
//			}
//		 }

	}

	private void placeKing(SquareButton kingSquare, int type) {
		kingSquare.setToken(new King(type));
	}

	private void place(SquareButton nextLoc) {
		nextLoc.setToken(this.prevSquare.getToken());
		this.prevSquare.setBackground(Color.black);
	}

	public void move(SquareButton jbutton) {
		jbutton.removeToken();
		this.prevSquare = null;
	}

	public int getColDirection(int prevCol, int nextCol) {
		return nextCol < prevCol ? -1 : 1;
	}
}
