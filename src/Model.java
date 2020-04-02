
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Model implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imageWhite = new ImageIcon("white.png");;
	ImageIcon imageRed = new ImageIcon("red.png");;
	ImageIcon imageWhiteKing = new ImageIcon("white_king.png");;
	ImageIcon imageRedKing = new ImageIcon("red_king.png");;
	
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
}
