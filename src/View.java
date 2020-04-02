
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class View extends JFrame {

	/**
	 * 
	 */
	public JPanel window = new JPanel();
	public SquareButton[][] squares = new SquareButton[8][8];
	public List<SquareButton> blackButtons;
	public JPanel board = new JPanel(new GridLayout(8, 8));
	public JToolBar tools = new JToolBar();
	public JButton restart = new JButton("Play");
	public JButton resign = new JButton("Resign");
	public JButton connect = new JButton("Connect");
	public JLabel infoScreen;
	public Model token = new Model();
	

	public Controller control;

	public View() {
		this.control = new Controller(this);
		blackButtons = new ArrayList<>();
		createBoard();
		initialize();
		this.control.addInitialTokens();
	}

	public void initialize() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		board.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(7.0f)));
		board.setSize(700, 700);

		this.add(board);

		this.setSize(600, 600);
		this.setVisible(true);
		this.setResizable(false);

		tools.addSeparator();
		tools.setFloatable(false);
		tools.setBackground(Color.darkGray);

		infoScreen = new JLabel("Let's play!");
		infoScreen.setForeground(Color.white);
		restart.setEnabled(true);
		restart.addActionListener(this.control);
		tools.add(restart);
		tools.add(resign);
		connect.addActionListener(this.control);
		tools.add(connect);
		tools.addSeparator();
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
				squares[i][j] = button;
				button.addActionListener(this.control);
			}
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				board.add(squares[i][j]);
			}
		}
	}
	

	public JButton[][] get_square_button() {
		return squares;
	}
	
	public static void main(String[] args) throws IOException {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new View();
			}});
	}
}
