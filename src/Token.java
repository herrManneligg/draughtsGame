import javax.swing.ImageIcon;

interface Token {
	boolean isNormalMove(SquareButton prevPosition, SquareButton nextPosition);
	boolean isJumpMove(SquareButton prevPosition, SquareButton nextPosition);
	int getColour();
	int rowDirection();
	ImageIcon getTokenIcon();
}
