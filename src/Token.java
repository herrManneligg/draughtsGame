import javax.swing.ImageIcon;

interface Token {
	boolean legalMove();
	boolean canKill();
//	void delete();
	ImageIcon getTokenIcon();
}
