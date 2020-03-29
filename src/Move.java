import java.util.Arrays;

public class Move {

	Model token;
	Integer prevRow;
	Integer prevColumn;
	View gameView;
	int[][] board;

	public Move() {
		this.board = new int[8][8];
		resetLoc();
		recreateBoard();
	}

	public void recreateBoard() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = 0;
			}
		}
		populateBoard();
	}

	private void populateBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0) {
					this.board[i][j] = 1;
				} else {
					this.board[i + 5][j] = 3;
				}
			}
		}
		
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public void prevLocation(int x, int y) {
		this.prevRow = x;
		this.prevColumn = y;
	}

	public void moveNewLocation(Token player, int x, int y) {

	}

	public void resetLoc() {
		this.prevColumn = 0;
		this.prevRow = 0;
	}
}
