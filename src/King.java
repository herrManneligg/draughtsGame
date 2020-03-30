import javax.swing.ImageIcon;

public class King implements Token {

	Model model;
	ImageIcon kingIcon;
	int colour;

	public King(int type) {
		this.colour = type;
		this.model = new Model();
		setImage();
	}
	
	public void setImage() {
		if (this.colour == 2) {
			kingIcon = model.getRedKing();
		} else if (this.colour == 3) {
			kingIcon = model.getWhiteQueen();
		}
	}

	public ImageIcon getKingIcon() {
		return kingIcon;
	}

	@Override
	public boolean isNormalMove(SquareButton prevPosition, SquareButton nextPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isJumpMove(SquareButton prevPosition, SquareButton nextPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.kingIcon;
	}

	@Override
	public int getColour() {
		return colour;
	}

	@Override
	public int rowDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

}
