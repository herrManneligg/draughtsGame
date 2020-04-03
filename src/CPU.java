import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class CPU extends Player {

	Client gameView;
	Controller control;
	TreeMap<SquareButton, List<SquareButton>> CPUmoves;
	TreeMap<SquareButton, HashMap<SquareButton, SquareButton>> killCPUmoves;

	public CPU(String player, int colour, Client gameView, Controller c) {
		super(player, colour);
		this.gameView = gameView;
		this.control = c;
		CPUmoves = new TreeMap<>();
		killCPUmoves = new TreeMap<>();
	}

	public void cpuMove() {
		for (SquareButton positions : gameView.blackButtons) {
			if (positions.getToken().getColour() == this.playerToken) {
				this.CPUmoves.put(positions, positions.getToken().checkPosibleMoves(positions, gameView));
				if (!positions.getToken().getKillerMovemenets().isEmpty()) {
					this.killCPUmoves.put(positions, positions.getToken().getKillerMovemenets());
				}
			}
		}
		
		if (!killCPUmoves.isEmpty()) {
			
			SquareButton position = killCPUmoves.firstKey();
			
			HashMap<SquareButton, SquareButton> killPosAndVictim = killCPUmoves.get(position);
			Iterator<SquareButton> iterator = killPosAndVictim.values().iterator();
			SquareButton nextPosition = null;
			
			if(iterator.hasNext()){
				nextPosition = iterator.next();
			}
			SquareButton killedToken = killPosAndVictim.get(nextPosition);
			
			this.control.move(position, nextPosition);
			this.control.remove(killedToken);
			
		} else {
			
			SquareButton prev = this.CPUmoves.firstKey();
			SquareButton nextPos = this.CPUmoves.get(prev).get(0);
			
			this.control.move(prev, nextPos);
		}
		
		this.control.passRound();
	}
}
