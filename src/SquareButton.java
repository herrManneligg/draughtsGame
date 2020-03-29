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
		if (this.token == null) {
			this.token = token;
			setSquareIcon();
		} else {
//			Needs to be refactored. The intention here is to delete the token if another token jumps over this one.
			removeToken();
		}
	}
	
	public Token getToken() {
		return token;
	}

	public boolean isOccupied() {
		return this.token == null ? false : true;
	}
	
	public void setSquareIcon() {
		this.setIcon(token.getTokenIcon());
	}
	
	public void removeToken() {
		this.token = null;
		this.setIcon(null);
	}
}
