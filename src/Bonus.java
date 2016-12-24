import java.awt.Graphics;
import java.awt.image.BufferedImage;

import MG2D.geometrie.Couleur;
import MG2D.geometrie.Point;
import MG2D.geometrie.Texture;

public class Bonus extends Texture{

	@Override
	public void afficher(Graphics g) {
		// TODO Auto-generated method stub
		super.afficher(g);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public BufferedImage getImg() {
		// TODO Auto-generated method stub
		return super.getImg();
	}

	@Override
	public boolean getTransparent() {
		// TODO Auto-generated method stub
		return super.getTransparent();
	}

	@Override
	public void setA(Point aa) {
		// TODO Auto-generated method stub
		super.setA(aa);
	}

	@Override
	public void setB(Point bb) {
		// TODO Auto-generated method stub
		super.setB(bb);
	}

	@Override
	public void setImg(BufferedImage img) {
		// TODO Auto-generated method stub
		super.setImg(img);
	}

	@Override
	public void setImg(String chemin) {
		// TODO Auto-generated method stub
		super.setImg(chemin);
	}

	@Override
	public void setTransparent(boolean transparent) {
		// TODO Auto-generated method stub
		super.setTransparent(transparent);
	}

	public Bonus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bonus(Couleur couleur, String chemin, Point a, int larg, int haut) {
		super(couleur, chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}

	public Bonus(Couleur couleur, String chemin, Point a) {
		super(couleur, chemin, a);
		// TODO Auto-generated constructor stub
	}

	public Bonus(String chemin, Point a, int larg, int haut) {
		super(chemin, a, larg, haut);
		// TODO Auto-generated constructor stub
	}

	public Bonus(String chemin, Point a) {
		super(chemin, a);
		// TODO Auto-generated constructor stub
	}

	public Bonus(Texture t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

}
