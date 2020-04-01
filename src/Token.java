import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

interface Token {
	int getColour();
	int rowDirection();
	int getType();
	ImageIcon getTokenIcon();
	List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, View gameView);
	SquareButton hasAKill(SquareButton squareButton);
	HashMap<SquareButton, SquareButton> getKillerMovemenets();
}
