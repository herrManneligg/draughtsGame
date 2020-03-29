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
		if (this.colour == 1) {
			kingIcon = model.getRedKing();
		} else if (this.colour == 3) {
			kingIcon = model.getWhiteQueen();
		}
	}

	public ImageIcon getKingIcon() {
		return kingIcon;
	}

	@Override
	public boolean legalMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canKill() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void delete() {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public ImageIcon getTokenIcon() {
		return this.kingIcon;
	}
}
