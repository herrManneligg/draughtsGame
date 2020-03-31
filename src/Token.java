import java.util.List;

import javax.swing.ImageIcon;

interface Token {
	boolean isNormalMove(SquareButton prevPosition, SquareButton nextPosition);
	boolean isJumpMove(SquareButton prevPosition, SquareButton nextPosition);
	int getColour();
	int rowDirection();
	int getType();
	ImageIcon getTokenIcon();
	List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, View gameView);
	SquareButton hasAKill(SquareButton squareButton);
}
