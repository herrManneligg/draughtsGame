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
	private List<SquareButton> possibleKingMoves;

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
				if (this.prevSquare != null && (squareButton.getToken().getColour() == this.prevSquare.getToken().getColour())) {
					this.prevSquare.setBackground(Color.black);
				}
/*
 * 			If the player previously selected a king, the possible moves
 * 			are showed in green. This turns the squares back to black and
 * 			resets the possible moves list for the king.
 */
				if (possibleKingMoves != null) {
					
					for(int i = 0; i < possibleKingMoves.size(); i++) {
						possibleKingMoves.get(i).setBackground(Color.black);
					}
					possibleKingMoves = null;
				}
/*
 * 			If the player selects a King, the possible moves are displayed
 * 			and stored in a variable.
 */
				if ((squareButton.getToken().getType() > 1)) {
					possibleKingMoves = checkPosibleMoves(squareButton);
				}
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
 * 			Storing the button that has been jumped. For the moment, this works for
 * 			Men tokens. *Further implementation to King tokens missing*
 * 			
 */
				SquareButton jumpedButton = gameView.squares[prevSquare.getRow()
						+ prevSquare.getToken().rowDirection()][prevSquare.getColumn()
								+ getColDirection(prevSquare.getColumn(), squareButton.getColumn())];

/*
 * 
 * 				Checking whether the movement is legal for Men or King. For the Kings it compares the next
 * 				movement proposed by the player with a list of possible moves determined by the previous SquareButton
 * 				selected. *Further implementation - Men should have a similar implementation.*
 * 
 */
				if (prevSquare.getToken().getType() < 2 && prevSquare.getToken().isNormalMove(prevSquare, squareButton)
					|| (prevSquare.getToken().getType() > 1) && checkPosibleMoves(prevSquare).contains(squareButton)) {

					place(squareButton);
					move(this.prevSquare);
					if (possibleKingMoves != null) {
						for(int i = 0; i < possibleKingMoves.size(); i++) {
							possibleKingMoves.get(i).setBackground(Color.black);
						}
						possibleKingMoves = null;
					}

				} else if (jumpedButton.getToken() != null
						&& prevSquare.getToken().isJumpMove(prevSquare, squareButton)) {
					if (jumpedButton.getToken().getColour() != prevSquare.getToken().getColour()) {

						place(squareButton);
						jumpedButton.removeToken();
						move(this.prevSquare);
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
//		} else if (e.getSource() instanceof JButton) {
//			if ((JButton)e.getSource().getText().equals("Play") != null) {
//			gameView.addInitialTokens();
//			gameView.repaint();
//			}

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

	public List<SquareButton> checkPosibleMoves(SquareButton pieceLocation) {
		List<SquareButton> possibleMoves = new ArrayList<>();

		int row = pieceLocation.row;
		int column = pieceLocation.getColumn();
		System.out.println(row + ", " + column);
		
		boolean isCheckingUp = true;
		boolean isCheckingDown = true;
		
		boolean diagonalUpRightIsBlocked = false;
		boolean diagonalUpLeftIsBlocked = false;
		boolean diagonalDownRightIsBlocked = false;
		boolean diagonalDownLefttIsBlocked = false;
		
		for (int rowUp = row - 1, rowDown = row + 1, columnLeft = column - 1, columnRight = column + 1; (isCheckingUp == true) || (isCheckingDown == true); rowUp--, rowDown++) {
			
			if (rowUp >= 0 && isCheckingUp == true) {
				if (columnLeft >= 0) {
					if (gameView.squares[rowUp][columnLeft] != null && gameView.squares[rowUp][columnLeft].isOccupied() == false) {
						gameView.squares[rowUp][columnLeft].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowUp][columnLeft]);
						System.out.println(rowUp + ", " + columnLeft + " diagonal up left is not occupied!");
					}
				}
				
				if (columnRight <= 7 && diagonalUpRightIsBlocked == false) {
					
					if (gameView.squares[rowUp][columnRight] != null && gameView.squares[rowUp][columnRight].isOccupied() == false) {
						gameView.squares[rowUp][columnRight].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowUp][columnRight]);
						System.out.println(rowUp + ", " + columnRight + " diagonal up right is not occupied!");
						
					} else {
						diagonalUpRightIsBlocked = true;
					}
				}
			} 
			
			if (rowDown <= 7 && isCheckingDown == true) {
				if (columnLeft >= 0) {
					if (gameView.squares[rowDown][columnLeft] != null && gameView.squares[rowDown][columnLeft].isOccupied() == false) {
						gameView.squares[rowDown][columnLeft].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowDown][columnLeft]);
						System.out.println(rowDown + ", " + columnLeft + " diagonal down left is not occupied!");
					}
				}
				
				if (columnRight <= 7) {
					if (gameView.squares[rowDown][columnRight] != null && gameView.squares[rowDown][columnRight].isOccupied() == false) {
						gameView.squares[rowDown][columnRight].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowDown][columnRight]);
						System.out.println(rowDown + ", " + columnRight + " diagonal down right is not occupied!");
					}
				}
			}
			columnLeft--;
			columnRight++;
			
			if (rowUp < 0) {
				isCheckingUp = false;
			}
			if (rowDown > 7) {
				isCheckingDown = false;
			}
		}
		
		return possibleMoves;
	}
}
