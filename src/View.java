
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class View extends JFrame implements ActionListener {

	public JPanel window = new JPanel();
	public JButton[][] squares = new JButton[8][8];
	public JPanel board = new JPanel(new GridLayout(8, 8));
	public JToolBar tools = new JToolBar();
	public JButton play = new JButton("Play");
	public JButton resign = new JButton("Resign");
	public JButton connect = new JButton("Connect");
	public Pieces token = new Pieces();

	public View() {
		initialize();
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
				squares[j][i] = button;
			}
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				board.add(squares[j][i]);
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
		
		squares[1][0].add(new Pieces().getRed());
		squares[3][0].add(new Pieces().getRed());
		squares[5][0].add(new Pieces().getRed());
		squares[7][0].add(new Pieces().getRed());
		
		squares[2][7].add(new Pieces().getWhite());
		squares[4][7].add(new Pieces().getWhite());
		squares[6][7].add(new Pieces().getWhite());
		squares[0][7].add(new Pieces().getWhite());
		
		// test
		
	}

	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		View frame = new View();
	}

}
