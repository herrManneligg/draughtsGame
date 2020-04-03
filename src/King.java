import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;

public class King extends Men {

	public King(int type) {
		super(type);
		setImage();
	}

	@Override
	public void setImage() {
		if (this.colour == 2) {
			this.tokenIcon = model.getRedKing();
		} else if (this.colour == 3) {
			this.tokenIcon = model.getWhiteQueen();
		}
	}

	@Override
	public int getColour() {
		return this.colour == 2 ? 0 : 1;
	}

	public int rowDirection(int n) {
		return n == 0 ? 1 : -1;
	}

	@Override
	public List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, Client gameView) {

		List<SquareButton> possibleMoves = new ArrayList<>();

		int rowStep = 0;

		for (int i = 0; i <= 1; i++) {
			
			rowStep = pieceLocation.row + rowDirection(i);
			
			System.out.println(i);
			System.out.println(rowStep);			
			
			SquareButton leftSquare;
			SquareButton rightSquare;
			SquareButton jumpButton;

		
			if (rowStep >= 0 && rowStep < 8) {

				if (pieceLocation.column - 1 >= 0) {
					System.out.println("Heyy");

					leftSquare = gameView.squares[rowStep][pieceLocation.column - 1];
					if (leftSquare.isOccupied() == false) {

						possibleMoves.add(leftSquare);
						leftSquare.setBackground(Color.green);

					} else if ((pieceLocation.column - 2) >= 0 && (rowStep + rowDirection(i) <= 7) && (rowStep + rowDirection(i) >= 0)) {

						jumpButton = gameView.squares[rowStep + rowDirection(i)][pieceLocation.column - 2];

						if (jumpButton != null && jumpButton.isOccupied() == false && leftSquare.getToken().getColour() != this.getColour()) {

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

					} else if ((pieceLocation.column + 2) < 8 && (rowStep + rowDirection(i) <= 7) && (rowStep + rowDirection(i) >= 0)) {

						jumpButton = gameView.squares[rowStep + rowDirection(i)][pieceLocation.column + 2];

						if (jumpButton != null && jumpButton.isOccupied() == false && rightSquare.getToken().getColour() != this.getColour()) {

							possibleMoves.add(jumpButton);
							this.killerMovemenets.put(jumpButton, rightSquare);
							jumpButton.setBackground(Color.RED);
						}
					}
				}
			}
		}
		return possibleMoves;
	}
}
