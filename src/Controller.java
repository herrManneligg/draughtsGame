import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Controller implements ActionListener {

	private View gameView;
	private Move moves;
	private SquareButton prevSquare;
	
	int row;
	int column;

	public Controller(View view) {
		gameView = view;
//		prevSquare = null;
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof SquareButton) {
			
			SquareButton squareButton = ((SquareButton) e.getSource());
			System.out.println("Move: " + "r: " + squareButton.getRow() + ", c: " + squareButton.getColumn() + "\n");
			
			if (squareButton.getIcon() != null) {
				this.prevSquare = squareButton;
				squareButton.setBackground(Color.yellow);
			
			} else if (squareButton.getIcon() == null && prevSquare != null) {
				place(squareButton, prevSquare);
//				move(squareButton);
				
			} else if (squareButton.getText().equals("Play")) {
				gameView.startGame();
				gameView.repaint();
			}
		}
	}

	private void place(SquareButton nextLoc, SquareButton prevLoc) {
		nextLoc.setToken(prevLoc.getToken());
		prevLoc.setBackground(Color.black);
		this.prevSquare = null;
//		gameView.repaint();
	}

	public void move(SquareButton jbutton) {
		jbutton.removeToken();
	}
	
	public void setMove(Move m) {
		this.moves = m;
	}
}
