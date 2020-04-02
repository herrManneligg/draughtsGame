import java.io.Serializable;

public class MovementUpdate implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7901044153140876531L;
	private int[] prevMovementCoords = new int[2];
	private int[] nextMovementCoords = new int[2];
	private String senderName;

//	public MovementUpdate(String senderName, SquareButton prevMovement, SquareButton nextMovement) {
//		this.prevMovement = prevMovement;
//		this.nextMovement = nextMovement;
//		this.senderName = senderName;
//	}
	
	public MovementUpdate(SquareButton prevMovement, SquareButton nextMovement) {
		this.prevMovementCoords[0] = prevMovement.row;
		this.prevMovementCoords[1] = prevMovement.column;
		
		this.nextMovementCoords[0] = nextMovement.row;
		this.nextMovementCoords[1] = nextMovement.column;
//		this.senderName = senderName;
	}

	public int[] getPrevSquare() {
		return prevMovementCoords;
	}

	public int[] getNextSquare() {
		return nextMovementCoords;
	}


	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String toString() {
		return this.senderName + ": (" + this.prevMovementCoords[0] + ", " + this.prevMovementCoords[1] + ")" + " -> (" + nextMovementCoords[0] + ", " + nextMovementCoords[1] + ")";
	}
}
