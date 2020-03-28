import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class Controller implements ActionListener {

	private Pieces token;
	private View gameView;

	public Controller(View view) {
		gameView = view;

	}

	public void actionPerformed(ActionEvent e) {
		
		JButton btn = ((JButton) e.getSource());
		System.out.println("Move: " + "r: " + btn.getClientProperty("row") + ", c: " + btn.getClientProperty("column") + "\n");

		if (e.getSource() instanceof JButton && ((JButton) e.getSource()).getIcon() != null) {
			move((JButton) e.getSource());
		} else if (e.getSource() instanceof JButton && ((JButton) e.getSource()).getIcon() == null) {
			place((JButton) e.getSource());
		}

		if (e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Play")) {
			gameView.startGame();
		}
	}

	private void place(JButton jbutton) {
		jbutton.setIcon(gameView.token.getRedQueen());
	}

	public void move(JButton jbutton) {

		jbutton.setIcon(null);			
	}
}
