import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;

public class King implements Token {

	Model model;
	ImageIcon kingIcon;
	int colour;
	HashMap<SquareButton, SquareButton> killerMovemenets; // Stores the location of the movement that can kill and the
															// location of that contains the killed token

	public King(int type) {
		this.colour = type;
		this.model = new Model();
		this.killerMovemenets = new HashMap<>();
		setImage();
	}

	public void setImage() {
		if (this.colour == 2) {
			kingIcon = model.getRedKing();
		} else if (this.colour == 3) {
			kingIcon = model.getWhiteQueen();
		}
	}

	public ImageIcon getKingIcon() {
		return kingIcon;
	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.kingIcon;
	}

	@Override
	public int getColour() {
		return this.colour == 2 ? 0 : 1;
	}

	public int getType() {
		return this.colour;
	}

	public HashMap<SquareButton, SquareButton> getKillerMovemenets() {
		return killerMovemenets;
	}

	public void setKillerMovemenets(HashMap<SquareButton, SquareButton> killerMovemenets) {
		this.killerMovemenets = killerMovemenets;
	}

	@Override
	public int rowDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SquareButton hasAKill(SquareButton iskillingPosition) {
		return this.killerMovemenets.containsKey(iskillingPosition) ? this.killerMovemenets.get(iskillingPosition)
				: null;
	}

	public List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, View gameView) {
		List<SquareButton> possibleMoves = new ArrayList<>();

		int row = pieceLocation.row;
		int column = pieceLocation.getColumn();
		System.out.println(row + ", " + column);

		SquareButton rowsUp;
		SquareButton rowsDown;
		boolean isCheckingUp = true;
		boolean isCheckingDown = true;

		boolean diagonalUpLeftIsBlocked = false;
		boolean diagonalUpRightIsBlocked = false;
		boolean diagonalDownRightIsBlocked = false;
		boolean diagonalDownLefttIsBlocked = false;

		for (int rowUp = row - 1, rowDown = row + 1, columnLeft = column - 1, columnRight = column
				+ 1; (isCheckingUp == true)
						|| (isCheckingDown == true); rowUp--, rowDown++, columnLeft--, columnRight++) {

//			Checks the rows up from the position provided
			if (rowUp >= 0 && isCheckingUp == true) {

//				Checks the diagonal up left
				if (diagonalUpLeftIsBlocked == false && columnLeft >= 0) {

//					Variable for checking ROW UP : COLUMN LEFT
					rowsUp = gameView.squares[rowUp][columnLeft];

					if (rowsUp != null) {
						if (rowsUp.isOccupied() == false) {

							rowsUp.setBackground(Color.green);
							possibleMoves.add(rowsUp);

						} else if (rowsUp.getToken().getColour() == pieceLocation.getToken().getColour()) {

							diagonalUpLeftIsBlocked = true;

						} else if (((rowUp - 1) >= 0) && ((columnLeft - 1) >= 0) && gameView.squares[rowUp - 1][columnLeft - 1].getToken() == null) {

							gameView.squares[rowUp - 1][columnLeft - 1].setBackground(Color.red);
							this.killerMovemenets.put(gameView.squares[rowUp - 1][columnLeft - 1], rowsUp);
							possibleMoves.add(gameView.squares[rowUp - 1][columnLeft - 1]);
							diagonalUpLeftIsBlocked = true;

						} else {

							diagonalUpLeftIsBlocked = true;
						}
					}
				}
//				Checks the diagonal up right
				if (diagonalUpRightIsBlocked == false && columnRight <= 7
						&& gameView.squares[rowUp][columnRight] != null) {

					if (gameView.squares[rowUp][columnRight].isOccupied() == false) {

						gameView.squares[rowUp][columnRight].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowUp][columnRight]);
						System.out.println(rowUp + ", " + columnRight + " diagonal up right is not occupied!");

					} else if (gameView.squares[rowUp][columnRight].getToken().getColour() == pieceLocation.getToken()
							.getColour()) {

						diagonalUpRightIsBlocked = true;

					} else if (((rowUp - 1) >= 0) && ((columnRight + 1) <= 7)
							&& gameView.squares[rowUp - 1][columnRight + 1].getToken() == null) {

						gameView.squares[rowUp - 1][columnRight + 1].setBackground(Color.red);
						this.killerMovemenets.put(gameView.squares[rowUp - 1][columnRight + 1],
								gameView.squares[rowUp][columnRight]);
						possibleMoves.add(gameView.squares[rowUp - 1][columnRight + 1]);
						diagonalUpRightIsBlocked = true;

					} else {

						diagonalUpRightIsBlocked = true;
					}
				}
			}

//			Checks the rows below the row from the position provided			
			if (rowDown <= 7 && isCheckingDown == true) {

//				Checks the diagonal down left
				if (columnLeft >= 0 && diagonalDownLefttIsBlocked == false
						&& gameView.squares[rowDown][columnLeft] != null) {

					if (gameView.squares[rowDown][columnLeft] != null
							&& gameView.squares[rowDown][columnLeft].isOccupied() == false) {

						gameView.squares[rowDown][columnLeft].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowDown][columnLeft]);
						System.out.println(rowDown + ", " + columnLeft + " diagonal down left is not occupied!");

					} else if (gameView.squares[rowDown][columnLeft].getToken().getColour() == pieceLocation.getToken()
							.getColour()) {

						diagonalDownLefttIsBlocked = true;

					} else if (((rowDown + 1) <= 7) && ((columnLeft - 1) >= 0)
							&& gameView.squares[rowDown + 1][columnLeft - 1].getToken() == null) {

						gameView.squares[rowDown + 1][columnLeft - 1].setBackground(Color.red);
						this.killerMovemenets.put(gameView.squares[rowDown + 1][columnLeft - 1],
								gameView.squares[rowDown][columnLeft]);
						possibleMoves.add(gameView.squares[rowDown + 1][columnLeft - 1]);
						diagonalDownLefttIsBlocked = true;

					} else {

						diagonalDownLefttIsBlocked = true;
					}
				}

//				Checks the diagonal down right
				if (columnRight <= 7 && diagonalDownRightIsBlocked == false
						&& gameView.squares[rowDown][columnRight] != null) {

					if (gameView.squares[rowDown][columnRight] != null
							&& gameView.squares[rowDown][columnRight].isOccupied() == false) {

						gameView.squares[rowDown][columnRight].setBackground(Color.green);
						possibleMoves.add(gameView.squares[rowDown][columnRight]);
						System.out.println(rowDown + ", " + columnRight + " diagonal down right is not occupied!");

					} else if (gameView.squares[rowDown][columnRight].getToken().getColour() == pieceLocation.getToken()
							.getColour()) {

						diagonalDownRightIsBlocked = true;

					} else if (((rowDown + 1) <= 7) && ((columnRight + 1) <= 7)
							&& gameView.squares[rowDown + 1][columnRight + 1].getToken() == null) {

						gameView.squares[rowDown + 1][columnRight + 1].setBackground(Color.red);
						this.killerMovemenets.put(gameView.squares[rowDown + 1][columnRight + 1],
								gameView.squares[rowDown][columnRight]);
						possibleMoves.add(gameView.squares[rowDown + 1][columnRight + 1]);
						diagonalDownRightIsBlocked = true;

					} else {

						diagonalDownRightIsBlocked = true;
					}
				}
			}

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
