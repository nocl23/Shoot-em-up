import MG2D.*;
import MG2D.geometrie.*;

public class Ennemi extends Texture {

	private boolean paroi = false;
	
	public boolean getParoi() {
		return paroi;
	}

	public void setParoi(boolean paroi) {
		this.paroi = paroi;
	}

	public Ennemi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ennemi(Couleur couleur, String chemin, Point a, int larg, int haut) {
		super(couleur, chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}

	public Ennemi(Couleur couleur, String chemin, Point a) {
		super(couleur, chemin, a);
		// TODO Auto-generated constructor stub
	}

	public Ennemi(String chemin, Point a, int larg, int haut) {
		super(chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}

	public Ennemi(String chemin, Point a) {
		super(chemin, a);
		// TODO Auto-generated constructor stub
	}

	public Ennemi(Texture t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

}
