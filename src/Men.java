import javax.swing.ImageIcon;

public class Men implements Token{
	
	Model model;
	ImageIcon menIcon;
	int colour;

	public Men(int type) {

		this.colour = type;
		this.model = new Model();
		setImage();
	}
	
	public void setImage() {
		if (this.colour == 0) {
			this.menIcon = model.getRed();
		} else if (this.colour == 1) {
			this.menIcon = model.getWhite();
		}
	}
	
	public int getColour() {
		return this.colour == 0 ? 0 : 1;
	}
	
	public int getType() {
		return this.colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	@Override
	public boolean isNormalMove(SquareButton prevPosition, SquareButton nextPosition) {
		return (nextPosition.getRow() == rowDirection() + prevPosition.getRow() && (Math.abs(prevPosition.getColumn() - nextPosition.getColumn())) == 1) ? true : false;
	}

	@Override
	public boolean isJumpMove(SquareButton prevPosition, SquareButton nextPosition) {
		return (nextPosition.getRow() == rowDirection()*2 + prevPosition.getRow() && (Math.abs(prevPosition.getColumn() - nextPosition.getColumn())) == 2) ? true : false;
	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.menIcon;
	}

	public int rowDirection() {
		return this.colour == 0 ? 1 : -1;
	}
}
