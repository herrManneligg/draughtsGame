import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pieces {
	
	JLabel red;
	JLabel white;
	JLabel redQueen;
	JLabel whiteQueen;
	
	public Pieces() {
//		ImageIcon imageRedQueen = new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\Red_king.png");
//		ImageIcon imageRedQueen = new ImageIcon(ImageIO.readgetClass().getResource("\\src\\Red_king.png")));
//		this.redQueen = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\Red_king.png"));
		
//		ImageIcon imageRed = new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\red.png");
//		this.red = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\red.png"));
		
//		ImageIcon imageWhiteQueen = new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white_king.png");
//		this.whiteQueen = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white_king.png"));
		
//		ImageIcon imageWhite = new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white.png");
//		this.white = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white.png"));
	}
	
	public JLabel getRedQueen() {
		return this.redQueen = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\Red_king.png"));
	}
	
	public JLabel getRed() {
		return this.red = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\red.png"));
	}
	
	public JLabel getWhiteQueen() {
		return this.whiteQueen = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white_king.png"));
	}
	
	public JLabel getWhite() {
		return this.white = new JLabel(new ImageIcon("C:\\Users\\p_ber\\Workspace\\AdvancedProgrammingAssessment\\src\\white.png"));
	}
}
