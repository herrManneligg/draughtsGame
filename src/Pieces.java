import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pieces {
	
	ImageIcon imageWhite;
	ImageIcon imageRed;
	ImageIcon imageWhiteQueen;
	ImageIcon imageRedQueen;
	
	JLabel red;
	JLabel white;
	JLabel redQueen;
	JLabel whiteQueen;
	
	ImageIcon empty;
	
	public Pieces() {
		
		empty = new ImageIcon("");
	
		imageWhite = new ImageIcon("white.png");
		
		imageRedQueen = new ImageIcon("Red_king.png");
		
		imageRed = new ImageIcon("red.png");
		
		imageWhiteQueen = new ImageIcon("white_king.png");
	}
	
	public ImageIcon getRedQueen() {
		return this.imageRedQueen;
	}
	
	public ImageIcon getRed() {
		return this.imageRed;
	}
	
	public ImageIcon getWhiteQueen() {
		return this.imageWhiteQueen;
	}
	
	public ImageIcon getWhite() {
		return this.imageWhite;
	}
	
	public ImageIcon getEmpty() {
		return this.empty;
	}
}
