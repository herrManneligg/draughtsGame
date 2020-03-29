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
		} else if (this.colour == 2) {
			this.menIcon = model.getWhite();
		}
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
		return this.menIcon;
	}
	
}
