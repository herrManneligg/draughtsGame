import java.io.Serializable;

public class MovementUpdate implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7901044153140876531L;
	private SquareButton prevMovement;
	private SquareButton nextMovement;
	private String senderName;

//	public MovementUpdate(String senderName, SquareButton prevMovement, SquareButton nextMovement) {
//		this.prevMovement = prevMovement;
//		this.nextMovement = nextMovement;
//		this.senderName = senderName;
//	}
	
	public MovementUpdate(SquareButton prevMovement, SquareButton nextMovement) {
		this.prevMovement = prevMovement;
		this.nextMovement = nextMovement;
//		this.senderName = senderName;
	}

	public SquareButton getPrevMovement() {
		return prevMovement;
	}

	public void setPrevMovement(SquareButton prevMovement) {
		this.prevMovement = prevMovement;
	}

	public SquareButton getNextMovement() {
		return nextMovement;
	}

	public void setNextMovement(SquareButton nextMovement) {
		this.nextMovement = nextMovement;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String toString() {
		return this.senderName + ": (" + this.prevMovement.row + ", " + this.prevMovement.column + ")" + " -> (" + nextMovement.row + ", " + nextMovement.column + ")";
	}
}
