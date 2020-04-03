import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

public class Men implements Token, Serializable {

	int colour;
	Model model;
	ImageIcon tokenIcon;
	HashMap<SquareButton, SquareButton> killerMovemenets; // Stores the location of the movement that can kill and the
	private static final long serialVersionUID = 1L;
															// location of that contains the killed token

	public Men(int type) {
		this.colour = type;
		this.model = new Model();
		this.killerMovemenets = new HashMap<>();
		setImage();
	}

	public void setImage() {
		if (this.colour == 0) {
			this.tokenIcon = model.getRed();
		} else if (this.colour == 1) {
			this.tokenIcon = model.getWhite();
		}
	}

	public int getColour() {
		return this.colour == 0 ? 0 : 1; // RED = 0 : WHITE = 1
	}

	public int getType() {
		return this.colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.tokenIcon;
	}

	public int rowDirection() {
		return this.colour == 0 ? 1 : -1;
	}
	
	public HashMap<SquareButton, SquareButton> getKillerMovemenets() {
		return killerMovemenets;
	}
	
	@Override
	public SquareButton hasAKill(SquareButton iskillingPosition) {
		return this.killerMovemenets.containsKey(iskillingPosition) ? this.killerMovemenets.get(iskillingPosition): null;
	}
	
	@Override
	public List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, Client gameView) {

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

			} else if ((pieceLocation.column - 2) >= 0 && (rowStep + rowDirection() <= 7) && (rowStep + rowDirection() >= 0)) {

				jumpButton = gameView.squares[rowStep + rowDirection()][pieceLocation.column - 2];

				if (jumpButton != null && jumpButton.isOccupied() == false
						&& leftSquare.getToken().getColour() != this.colour) {

					possibleMoves.add(jumpButton);
					this.killerMovemenets.put(jumpButton, leftSquare);
					jumpButton.setBackground(Color.RED);
				}
			}
		}

		if (pieceLocation.column + 1 <= 7) {

			rightSquare = gameView.squares[rowStep][pieceLocation.column + 1];

			if (rightSquare.isOccupied() == false) {

				possibleMoves.add(rightSquare);
				rightSquare.setBackground(Color.green);
				
			} else if ((pieceLocation.column + 2) < 8 && (rowStep + rowDirection() <= 7) && (rowStep + rowDirection() >= 0)) {

				jumpButton = gameView.squares[rowStep + rowDirection()][pieceLocation.column + 2];

				if (jumpButton != null && jumpButton.isOccupied() == false && rightSquare.getToken().getColour() != this.colour) {

					possibleMoves.add(jumpButton);
					this.killerMovemenets.put(jumpButton, rightSquare);
					jumpButton.setBackground(Color.RED);
				}
			}
		}

		return possibleMoves;
	}
}
