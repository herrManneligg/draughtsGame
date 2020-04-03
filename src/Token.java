import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

interface Token {
	int getType();
	int getColour();
	int rowDirection();
	
	ImageIcon getTokenIcon();
	SquareButton hasAKill(SquareButton squareButton);
	HashMap<SquareButton, SquareButton> getKillerMovemenets();
	List<SquareButton> checkPosibleMoves(SquareButton pieceLocation, Client gameView);
}
