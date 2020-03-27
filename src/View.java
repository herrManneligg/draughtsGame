
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class View extends JFrame {

	public JPanel window = new JPanel();
	public JButton[][] squares = new JButton[8][8];
	public JPanel board = new JPanel(new GridLayout(8, 8));
	public JToolBar tools = new JToolBar();
	public JButton play = new JButton("Play");
	public JButton resign = new JButton("Resign");
	public JButton connect = new JButton("Connect");
	public Pieces token = new Pieces();
	
	public Controller control;

	public View(Controller gc) {
		initialize();
		startGame();
		this.control = gc;
	}

	public void initialize() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		board.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7.0f)));
		board.setSize(700, 700);

		Insets buttonMargin = new Insets(0, 0, 0, 0);

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(9, 9));
				button.setMargin(buttonMargin);
				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
					button.setBackground(Color.WHITE);
				} else {
					button.setBackground(Color.BLACK);
				}
				button.setVisible(true);
				button.addActionListener(control);
				squares[i][j] = button;
			}
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				board.add(squares[i][j]);
			}
		}

		this.add(board);

		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);

		tools.addSeparator();
		tools.setFloatable(false);
		tools.setBackground(Color.darkGray);

		tools.add(play);
		tools.add(resign);
		this.add(tools, BorderLayout.PAGE_START);
	}
	
	
/*
 * 	Filling the board with the pieces. Using invokeLater to rendering the pieces images.  
 */
	public void startGame() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 8; j++) {
						if (i % 2 == 0 && j % 2 != 0) {
							squares[i][j].setIcon(token.getRed());
						} else if (i % 2 != 0 && j % 2 == 0) {
							squares[i][j].setIcon(token.getRed());
						} else {
							squares[i + 5][j].setIcon(token.getWhite());
						}
					}
				}
			}
		});
	}
	

	
	public Object get_square_button() {
		return squares;
	}
	
	

	public static void main(String[] args) throws IOException {
		Controller gameControl = new Controller();
	}

}
