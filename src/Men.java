import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

public class Men implements Token {

	Model model;
	ImageIcon menIcon;
	int colour;
	HashMap<SquareButton, SquareButton> killerMovemenets; // Stores the location of the movement that can kill and the
															// location of that contains the killed token

	public Men(int type) {

		this.colour = type;
		this.killerMovemenets = new HashMap<>();
		this.model = new Model();
		setImage();
	}

	public void setImage() {
		if (this.colour == 0) {
			this.menIcon = model.getRed();
		} else if (this.colour == 1) {
			this.menIcon = model.getWhite();
		}
	}

	public int getColour() {
		return this.colour == 0 ? 0 : 1;
	}

	public int getType() {
		return this.colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.menIcon;
	}

	public int rowDirection() {
		return this.colour == 0 ? 1 : -1;
	}

	@Override
	public List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, View gameView) {

		List<SquareButton> possibleMoves = new ArrayList<>();
		int rowStep = pieceLocation.row + rowDirection();

		SquareButton leftSquare;
		SquareButton rightSquare;
		SquareButton jumpButton;

		if (pieceLocation.column - 1 >= 0) {
			leftSquare = gameView.squares[rowStep][pieceLocation.column - 1];
			if (leftSquare.isOccupied() == false) {

				possibleMoves.add(leftSquare);
				leftSquare.setBackground(Color.green);
				System.out.println("A");

			} else if ((pieceLocation.column - 2) >= 0 && (rowStep + rowDirection() <= 7)
					&& (rowStep + rowDirection() >= 0)) {

				jumpButton = gameView.squares[rowStep + rowDirection()][pieceLocation.column - 2];

				if (jumpButton != null && jumpButton.isOccupied() == false
						&& leftSquare.getToken().getColour() != this.colour) {

					possibleMoves.add(jumpButton);
					this.killerMovemenets.put(jumpButton, leftSquare);
					jumpButton.setBackground(Color.RED);
					System.out.println("B");
				}
			}
		}

		if (pieceLocation.column + 1 <= 7) {

			rightSquare = gameView.squares[rowStep][pieceLocation.column + 1];

			if (rightSquare.isOccupied() == false) {

				possibleMoves.add(rightSquare);
				rightSquare.setBackground(Color.green);
				System.out.println("C");
				
			} else if ((pieceLocation.column + 2) < 8 && (rowStep + rowDirection() <= 7)
					&& (rowStep + rowDirection() >= 0)) {

				jumpButton = gameView.squares[rowStep + rowDirection()][pieceLocation.column + 2];

				if (jumpButton != null && jumpButton.isOccupied() == false && rightSquare.getToken().getColour() != this.colour) {

					possibleMoves.add(jumpButton);
					this.killerMovemenets.put(jumpButton, rightSquare);
					jumpButton.setBackground(Color.RED);
					System.out.println("D");
				}
			}
		}

		return possibleMoves;
	}

	@Override
	public SquareButton hasAKill(SquareButton iskillingPosition) {
		return this.killerMovemenets.containsKey(iskillingPosition) ? this.killerMovemenets.get(iskillingPosition): null;
	}
}
