import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

	private View gameView;
	private SquareButton prevSquare;

	int row;
	int column;

	public Controller(View view) {
		gameView = view;
		prevSquare = null;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof SquareButton) {

			SquareButton squareButton = ((SquareButton) e.getSource());

			if (squareButton.getToken() != null) {
				if (this.prevSquare != null && (squareButton.getToken().getColour() == this.prevSquare.getToken().getColour())) {
					this.prevSquare.setBackground(Color.black);
				}

				squareButton.setBackground(Color.yellow);
				this.prevSquare = squareButton;
				
			} else if (squareButton.getText().equals("Play")) {
				gameView.addInitialTokens();
				gameView.repaint();
			} 

			if (squareButton.getToken() == null && prevSquare != null) {

					SquareButton jumpedButton = gameView.squares[prevSquare.getRow()
							+ prevSquare.getToken().rowDirection()][prevSquare.getColumn()
									+ getColDirection(prevSquare.getColumn(), squareButton.getColumn())];

					if (prevSquare.getToken().isNormalMove(prevSquare, squareButton)) {

						place(squareButton);
						move(this.prevSquare);

					} else if (jumpedButton.getToken() != null
							&& prevSquare.getToken().isJumpMove(prevSquare, squareButton)) {

						if (jumpedButton.getToken().getColour() != prevSquare.getToken().getColour()) {

							place(squareButton);
							jumpedButton.removeToken();
							move(this.prevSquare);
						}
					}
					
			} if (squareButton.getToken() != null && squareButton.getToken().getColour() < 2) {
				if (squareButton.getToken().getColour() == 0 && squareButton.getRow() == 7) {
					placeKing(squareButton, 2);
				} else if (squareButton.getToken().getColour() == 1 && squareButton.getRow() == 0) {
					placeKing(squareButton, 3);
				}
			}
			System.out.println("Move: " + "r: " + squareButton.getRow() + ", c: " + squareButton.getColumn() + "\n");
		}
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
