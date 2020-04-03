
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Client extends JFrame {

	public JPanel window = new JPanel();
	public JPanel board = new JPanel(new GridLayout(8, 8));
	public JLabel infoScreen;
	public JToolBar tools = new JToolBar();

	public JButton restart;
	public JButton connect;
	public JButton resign;
	public JButton player1;
	public JButton player2;
	public JButton CPU;

	public SquareButton[][] squares = new SquareButton[8][8];
	public List<SquareButton> blackButtons;
	public Model token = new Model();

	public Controller control;

	public Client() {
		this.control = new Controller(this);
		blackButtons = new ArrayList<>();
		createBoard();
		initialize();
	}

	public void initialize() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		board.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7.0f)));
		board.setSize(700, 700);

		this.add(board);

		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);

		tools.setFloatable(false);
		tools.setBackground(Color.darkGray);

		restart = makeButton("Play");
		resign = makeButton("Resign");
		connect = makeButton("Connect");
		CPU = makeButton("CPU");
		player1 = makeButton("Player 1");
		player2 = makeButton("Player 2");
		
		player1.setActionCommand("Player");
		player2.setActionCommand("Player");
		
		tools.addSeparator();
		infoScreen = new JLabel("Let's play!");
		infoScreen.setForeground(Color.white);
		tools.add(infoScreen, BorderLayout.PAGE_END);

		this.add(tools, BorderLayout.PAGE_START);
	}

	private void createBoard() {

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {

				SquareButton button = new SquareButton(i, j);
				button.setPreferredSize(new Dimension(9, 9));

				if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {

					button.setBackground(Color.WHITE);
					button.setEnabled(false);

				} else {

					button.setBackground(Color.BLACK);
					blackButtons.add(button);
				}

				button.setVisible(true);
				button.addActionListener(this.control);

				squares[i][j] = button;
				board.add(squares[i][j]);
			}
		}
	}

	public JButton[][] get_square_button() {
		return squares;
	}

	public JButton makeButton(String text) {
		JButton button = new JButton(text);
		button.addActionListener(this.control);
		button.setEnabled(text.equals("Player 1") || text.equals("Player 2") ? true : false);
		this.tools.add(button);
		return button;
	}
	

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Client();
			}
		});
	}

}
