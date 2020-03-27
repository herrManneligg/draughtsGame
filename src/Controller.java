

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Controller implements ActionListener{

	private View gameView;
	
	public Controller() {
		this.gameView = new View(this);
		gameView.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == gameView.get_square_button()) {
			 move((JButton)e.getSource());
//            ((JButton)e.getSource());
        }
	}
	
	
	public void move(JButton jbutton) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jbutton.setBackground(Color.RED);
				
			}
			
		});
	}

}
