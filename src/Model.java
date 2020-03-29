
import javax.swing.ImageIcon;

public class Model {
	
	ImageIcon imageWhite = new ImageIcon("white.png");;
	ImageIcon imageRed = new ImageIcon("red.png");;
	ImageIcon imageWhiteKing = new ImageIcon("white_king.png");;
	ImageIcon imageRedKing = new ImageIcon("Red_king.png");;
	
	public Model() {

	}
	
	public ImageIcon getRedKing() {
		return this.imageRedKing;
	}
	
	public ImageIcon getRed() {
		return this.imageRed;
	}
	
	public ImageIcon getWhiteQueen() {
		return this.imageWhiteKing;
	}
	
	public ImageIcon getWhite() {
		return this.imageWhite;
	}
	
//	Refactor
	public ImageIcon getToken(int n) {
		if (n == 0) {
			return this.imageRed;
		} else if (n == 1) {
			return this.imageRedKing;
		} else if (n == 2) {
			return this.imageWhite;
		} else {
			return this.imageWhiteKing;
		}
	}
}
