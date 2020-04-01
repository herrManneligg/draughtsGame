import javax.swing.JButton;

public class SquareButton extends JButton {

	int row;
	int column;
	Token token;
	
	public SquareButton(int x, int y) {
		this.row = x;
		this.column = y;
		this.token = null;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setToken(Token token) {
		this.token = token;
		setSquareIcon();
	}
	
	public Token getToken() {
		return token;
	}

	public boolean isOccupied() {
		return this.token != null ? true : false;
	}
	
	public void setSquareIcon() {
		this.setIcon(token.getTokenIcon());
	}
	
	public void removeToken() {
		this.setIcon(null);
		this.token = null;
	}
}
